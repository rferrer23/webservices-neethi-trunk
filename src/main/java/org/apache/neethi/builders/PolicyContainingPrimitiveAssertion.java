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

package org.apache.neethi.builders;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.neethi.policy.Assertion;
import org.apache.neethi.policy.Policy;
import org.apache.neethi.policy.PolicyComponent;
import org.apache.neethi.policy.PolicyContainingAssertion;
import org.apache.neethi.policy.impl.All;
import org.apache.neethi.policy.impl.ExactlyOne;

/**
 * Implementation of an assertion that required exactly one (possibly empty) child element
 * of type Policy (as does for examples the wsam:Addressing assertion).
 * 
 */
public class PolicyContainingPrimitiveAssertion
    extends PrimitiveAssertion 
    implements PolicyContainingAssertion {
    
    protected Policy nested;
    
    public PolicyContainingPrimitiveAssertion(QName name, 
                                              boolean optional,
                                              boolean ignorable, 
                                              Policy p) {
        super(name, optional, ignorable);
        this.nested = p;
    }
    public PolicyContainingPrimitiveAssertion(QName name, 
                                              boolean optional,
                                              boolean ignorable,
                                              Map<QName, String> atts,
                                              Policy p) {
        super(name, optional, ignorable, atts);
        this.nested = p;
    }

    public PolicyComponent normalize() {
        Policy normalisedNested 
            = nested.normalize(true);
        
        Policy p = new Policy(nested.getPolicyRegistry(), nested.getNamespace());
        ExactlyOne ea = new ExactlyOne();
        p.addPolicyComponent(ea);
        if (isOptional()) {
            ea.addPolicyComponent(new All());
        }
        // for all alternatives in normalized nested policy
        Iterator<List<Assertion>> alternatives = normalisedNested.getAlternatives();
        while (alternatives.hasNext()) {
            All all = new All();
            List<Assertion> alternative = alternatives.next();
            Policy n = new Policy(nested.getPolicyRegistry(), nested.getNamespace());
            Assertion a = clone(false, n);
            ExactlyOne nea = new ExactlyOne();
            n.addPolicyComponent(nea);
            All na = new All();
            nea.addPolicyComponent(na);
            na.addPolicyComponents(alternative);
            all.addPolicyComponent(a);
            ea.addPolicyComponent(all);            
        } 
        return p;      
    } 
    protected Assertion clone(boolean optional, Policy n) {
        return new PolicyContainingPrimitiveAssertion(name, optional, ignorable, attributes, n);
    }

    public boolean isEquivalent(PolicyComponent policyComponent) {
        if (this == policyComponent) {
            return true;
        }
        if (!super.isEquivalent(policyComponent)) {
            return false;
        }
        PolicyContainingPrimitiveAssertion other = (PolicyContainingPrimitiveAssertion)policyComponent;
        return getPolicy().isEquivalent(other.getPolicy());
    }
    
 
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nested == null) ? 0 : nested.hashCode());
		return result;
	}
    
    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof PolicyContainingPrimitiveAssertion)) {
          return false;
      }
      
      PolicyContainingPrimitiveAssertion pa = (PolicyContainingPrimitiveAssertion) obj;
      
      if (!super.equals(obj)) {
          return false;
      }
      
      return getPolicy().isEquivalent(pa.getPolicy());
    }

	public void setPolicy(Policy n) {
        nested = n;
    }
    public Policy getPolicy() {
        return nested;
    }
    @Override
    protected void writeContents(XMLStreamWriter writer) throws XMLStreamException {
        nested.serialize(writer);
    }
    @Override
    protected boolean hasContents() {
        return true;
    }

}
