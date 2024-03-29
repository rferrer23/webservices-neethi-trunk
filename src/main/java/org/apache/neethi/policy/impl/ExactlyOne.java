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

package org.apache.neethi.policy.impl;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.neethi.Constants;
import org.apache.neethi.policy.PolicyComponent;
import org.apache.neethi.policy.PolicyOperator;
import org.apache.neethi.service.AbstractPolicyOperator;

/**
 * ExactlyOne PolicyOperator requires exactly one of its PolicyComponents to be
 * met.
 * 
 */
public class ExactlyOne extends AbstractPolicyOperator {
    public ExactlyOne() {
        super();
    }
    public ExactlyOne(PolicyOperator parent) {
        super(parent);
    }

    public void serialize(XMLStreamWriter writer) throws XMLStreamException {
        String namespace = Constants.findPolicyNamespace(writer);
        String prefix = writer.getPrefix(namespace);

        if (prefix == null) {
            writer.writeStartElement(Constants.ATTR_WSP,
                    Constants.ELEM_EXACTLYONE, namespace);
            writer.writeNamespace(Constants.ATTR_WSP,
                    namespace);
            writer.setPrefix(Constants.ATTR_WSP, namespace);
        } else {
            writer.writeStartElement(namespace,
                    Constants.ELEM_EXACTLYONE);
        }

        for (PolicyComponent policyComponent : getPolicyComponents()) {
            policyComponent.serialize(writer);
        }

        writer.writeEndElement();
    }

    /**
     * Returns Constants.TYPE_EXACTLYONE;
     */
    public final short getPolicyType() {
        return Constants.TYPE_EXACTLYONE;
    }
}
