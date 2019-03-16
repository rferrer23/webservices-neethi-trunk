/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.neethi.builders.xml;

import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.Comment;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.dom.DOMSource;

import org.apache.neethi.Constants;
import org.apache.neethi.builders.PrimitiveAssertion;
import org.apache.neethi.exception.XMLPrimitiveException;
import org.apache.neethi.policy.Assertion;
import org.apache.neethi.policy.Policy;
import org.apache.neethi.policy.PolicyComponent;
import org.apache.neethi.policy.impl.All;
import org.apache.neethi.policy.impl.ExactlyOne;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

/**
 * XmlPrimitiveAssertion wraps an Element s.t. any unknown elements can be
 * treated an assertions if there is no AssertionBuilder that can build an
 * assertion from that Element.
 * 
 */
public class XmlPrimitiveAssertion extends PrimitiveAssertion implements Assertion {
    protected Element element;

    /**
     * Constructs a XmlPrimitiveAssertion from an Element.
     * 
     * @param element
     *            the Element from which the XmlAssertion is constructed
     */
    public XmlPrimitiveAssertion(Element element) {
        super(new QName(element.getNamespaceURI(), element.getLocalName()),
              XMLPrimitiveAssertionBuilder.isOptional(element), 
              XMLPrimitiveAssertionBuilder.isIgnorable(element));
        this.element = element;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof XmlPrimitiveAssertion) {
			XmlPrimitiveAssertion other = (XmlPrimitiveAssertion) obj;
			return isEquivalent(other);
		}
		return false;
	}

	/**
     * Sets the wrapped Element.
     * 
     * @param el the Element to be set as wrapped
     */
    public void setValue(Element el) {
        this.element = el;
    }

    /**
     * Returns the wrapped Element.
     * 
     * @return the wrapped Element
     */
    public Element getValue() {
        return element;
    }


    /**
     * Returns the partial normalized version of the wrapped Element, that is
     * assumed to be an assertion.
     */
    public PolicyComponent normalize() {
        if (optional) {
            Policy policy = new Policy();
            ExactlyOne exactlyOne = new ExactlyOne();

            All all = new All();
            Element el = (Element)this.element.cloneNode(true);
            Attr attr = el.getAttributeNodeNS(Constants.URI_POLICY_13_NS, Constants.ATTR_OPTIONAL);
            if (attr != null) {
                el.removeAttributeNode(attr);
            }
            attr = el.getAttributeNodeNS(Constants.URI_POLICY_15_NS, Constants.ATTR_OPTIONAL);
            if (attr != null) {
                el.removeAttributeNode(attr);
            }
            all.addPolicyComponent(new XmlPrimitiveAssertion(el));
            exactlyOne.addPolicyComponent(all);

            exactlyOne.addPolicyComponent(new All());
            policy.addPolicyComponent(exactlyOne);

            return policy;
        }

        return this;
    }


    public void serialize(XMLStreamWriter writer) throws XMLStreamException {
        if (element != null) {
            copyEvents(XMLInputFactory.newInstance().createXMLEventReader(new DOMSource(element)), writer);
        } else {
            throw new XMLPrimitiveException("Wrapped Element is not set");
        }
    }

    /**
     * Returns Constants.TYPE_ASSERTION
     */
    public final short getPolicyType() {
        return Constants.TYPE_ASSERTION;
    }

    public boolean isEquivalent(PolicyComponent policyComponent) {
        if (policyComponent.getPolicyType() != Constants.TYPE_ASSERTION) {
            return false;
        }

        return getName().equals(((Assertion) policyComponent).getName());
    }
    
    private void copyEvents(XMLEventReader reader, XMLStreamWriter writer) 
        throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            
            switch (event.getEventType()) {
            case XMLEvent.ATTRIBUTE: 
                copyAtt(writer, event);
                break;
            case XMLEvent.START_DOCUMENT:
            case XMLEvent.END_DOCUMENT:
                //not doing this as we're in a partial write mode
                break;
            case XMLEvent.END_ELEMENT:
                writer.writeEndElement();
                break;
            case XMLEvent.NAMESPACE: 
                copyNamespace(writer, event);
                break;   
            case XMLEvent.START_ELEMENT: 
                copyStartElement(writer, event);
                break;
            case XMLEvent.CHARACTERS: 
                copyCharacters(writer, event);
                break;           
            case XMLEvent.CDATA:
                writer.writeCData(event.asCharacters().getData());
                break;
            
            case XMLEvent.COMMENT:
                writer.writeComment(((Comment) event).getText());
                break;
            default:
            }
        }
    }

	private void copyAtt(XMLStreamWriter writer, XMLEvent event) throws XMLStreamException {
		Attribute attr = (Attribute) event;
		QName name = attr.getName();
		writer.writeAttribute(name.getPrefix(), name.getNamespaceURI(),
		                       name.getLocalPart(), attr.getValue());
	}

	private void copyNamespace(XMLStreamWriter writer, XMLEvent event) throws XMLStreamException {
		Namespace ns = (Namespace) event;
		writer.writeNamespace(ns.getPrefix(), ns.getNamespaceURI());
	}

	private void copyCharacters(XMLStreamWriter writer, XMLEvent event) throws XMLStreamException {
		Characters ch = event.asCharacters();
		String text = ch.getData();
		if (ch.isCData()) {
		    writer.writeCData(text);
		} else {
		    writer.writeCharacters(text);
		}
	}

	private void copyStartElement(XMLStreamWriter writer, XMLEvent event) throws XMLStreamException {
		StartElement se = event.asStartElement();
		QName n = se.getName();
		writer.writeStartElement(n.getPrefix(), n.getLocalPart(),
		                          n.getNamespaceURI());
		Iterator<?> it = se.getNamespaces();
		while (it.hasNext()) {
		    Namespace ns = (Namespace) it.next();
		    writer.writeNamespace(ns.getPrefix(), ns.getNamespaceURI());
		}
		it = se.getAttributes();
		while (it.hasNext()) {
		    Attribute attr = (Attribute) it.next();
		    QName name = attr.getName();
		    writer.writeAttribute(name.getPrefix(), name.getNamespaceURI(),
		                           name.getLocalPart(), attr.getValue());
		}
		if (!n.getNamespaceURI().equals(writer.getNamespaceContext().getNamespaceURI(n.getPrefix()))) {
		    writer.writeNamespace(n.getPrefix(), n.getNamespaceURI());
		}
	}
}
