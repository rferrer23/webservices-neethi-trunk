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

package org.apache.neethi.builders.converters;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.neethi.builders.converters.impl.NeethiIteratorXmlStreamReader;

/**
 * 
 */
public abstract class AbstractStaxConverter {

    public QName getQName(XMLStreamReader s) {
        if (s.getEventType() == XMLStreamReader.START_DOCUMENT) {
            try {
                s.nextTag();
            } catch (XMLStreamException e) {
                //ignore
            }
        }
        return new QName(s.getNamespaceURI(), s.getLocalName());
    }
    public Map<QName, String> getAttributes(XMLStreamReader s) {
        Map<QName, String> mp = new HashMap<>();
        for (int x = 0; x < s.getAttributeCount(); x++) {
            mp.put(new QName(s.getAttributeNamespace(x),
                             s.getAttributeLocalName(x)),
                   s.getAttributeValue(x));
        }
        for (int x = 0; x < s.getNamespaceCount(); x++) {
            String pfx = s.getNamespacePrefix(x);
            if (pfx == null) {
                mp.put(new QName(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, "xmlns"), s.getNamespaceURI(x));
            } else {
                mp.put(new QName(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, pfx, "xmlns"), s.getNamespaceURI(x));
            }
        }
        return mp;
    }
    public Iterator<XMLStreamReader> getChildren(final XMLStreamReader s) {
        final QName base = s.getName();
        try {
            int evt = s.getEventType();
            if (s.hasNext()) {
                evt = s.next();
            }
            while (s.hasNext() 
                && evt != XMLStreamReader.END_ELEMENT 
                && evt != XMLStreamReader.START_ELEMENT) {
                evt = s.next();
            }
        } catch (Exception ex) {
            return null;
        }
        return new NeethiIteratorXmlStreamReader(s, base);
    }
}
