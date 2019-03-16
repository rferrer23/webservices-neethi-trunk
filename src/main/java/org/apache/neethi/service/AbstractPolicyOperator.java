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

package org.apache.neethi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.neethi.Constants;
import org.apache.neethi.exception.PolicyComponentEmptyException;
import org.apache.neethi.policy.Assertion;
import org.apache.neethi.policy.Policy;
import org.apache.neethi.policy.PolicyComponent;
import org.apache.neethi.policy.PolicyOperator;
import org.apache.neethi.policy.impl.All;
import org.apache.neethi.policy.impl.ExactlyOne;
import org.apache.neethi.policy.impl.PolicyReference;
import org.apache.neethi.util.PolicyComparator;

/**
 * AbstractPolicyOperator provides an implementation of few functions of 
 * PolicyOperator interface that other PolicyOperators can use.
 */
public abstract class AbstractPolicyOperator implements PolicyOperator {
    protected List<PolicyComponent> policyComponents = new ArrayList<>();
    
    public AbstractPolicyOperator() {
        
    }
    
    public AbstractPolicyOperator(PolicyOperator parent) {
        parent.addPolicyComponent(this);
    }

    public void addPolicyComponent(PolicyComponent component) {
        if (component == null) {
            throw new IllegalArgumentException("Component must not be null");
        }
        policyComponents.add(component);
    }

    public void addPolicyComponents(List<? extends PolicyComponent> components) {
        policyComponents.addAll(components);
    }

    public List<PolicyComponent> getPolicyComponents() {
        return policyComponents;
    }

    public PolicyComponent getFirstPolicyComponent() {
        if (policyComponents.isEmpty()) {
            return null;
        }
        return policyComponents.get(0);
    }

    public boolean isEmpty() {
        return policyComponents.isEmpty();
    }
    
    public boolean isEquivalent(PolicyComponent policyComponent) {
        return PolicyComparator.compare(this, policyComponent);
    }
    
    protected static Policy normalize(Policy policy, PolicyRegistry reg, boolean deep) {
        Policy result = new Policy(reg, policy.getNamespace());
        
        String policyName = policy.getName();
        if (policyName != null) {
            result.setName(policyName);
        }
        
        String id = policy.getId();
        if (id != null) {
            result.setId(id);
        }
        
        
        result.addPolicyComponent(normalizeOperator(policy, policy, reg, deep));
        return result;
    }
    
    private static PolicyComponent normalizeOperator(Policy policy, 
                                                     PolicyOperator operator, 
                                                     PolicyRegistry reg,
                                                     boolean deep) {
                        
        short type = operator.getPolicyType();
                
        
        if (operator.isEmpty()) {
            ExactlyOne exactlyOne = new ExactlyOne();
            
            if (Constants.TYPE_EXACTLYONE != type) {
                exactlyOne.addPolicyComponent(new All());
            }
            return exactlyOne;
        }
                
        List<PolicyComponent> childComponentsList = new ArrayList<>();
        for (PolicyComponent policyComponent : operator.getPolicyComponents()) {
            if (policyComponent.getPolicyType() == Constants.TYPE_ASSERTION) {
                normalizeAssertion(deep, childComponentsList, policyComponent);
            } else if (policyComponent.getPolicyType() == Constants.TYPE_POLICY_REF) {
                normalizePolicyRef(policy, reg, deep, childComponentsList, policyComponent);
         
            } else if (policyComponent.getPolicyType() == Constants.TYPE_POLICY) {
                All all = new All();
                all.addPolicyComponents(((Policy) policyComponent).getPolicyComponents());
                childComponentsList.add(AbstractPolicyOperator.normalizeOperator(policy, all, reg, deep));
                
            } else {
                childComponentsList.add(AbstractPolicyOperator
                                            .normalizeOperator(policy,
                                                               (PolicyOperator)policyComponent, reg, deep));
            }            
        }
        
        return computeResultantComponent(childComponentsList, type);
    }

	private static PolicyComponent normalizePolicyRef(Policy policy, PolicyRegistry reg, boolean deep,
			List<PolicyComponent> childComponentsList, PolicyComponent policyComponent) {
		String uri = ((PolicyReference) policyComponent).getURI();
		policyComponent = reg == null ? null : reg.lookup(uri);
		if (policyComponent == null && uri.charAt(0) == '#') {
		    String id = uri.substring(1);

		    policyComponent = discoveredPolicyComponent(policy, reg, id);
		}
		if (policyComponent == null) {
		    throw new PolicyComponentEmptyException(uri + " can't be resolved");
		}
		
		All all = new All();
		all.addPolicyComponents(((Policy) policyComponent).getPolicyComponents());
		childComponentsList.add(AbstractPolicyOperator.normalizeOperator(policy, all, reg, deep));
		return policyComponent;
	}

	private static PolicyComponent discoveredPolicyComponent(Policy policy, PolicyRegistry reg, String id) {
		PolicyComponent policyComponent;
		policyComponent = reg == null ? null : reg.lookup(id);
		if (policyComponent == null) {
		    for (PolicyComponent p : policy.getPolicyComponents()) {
		        if (p instanceof Policy && id.equals(((Policy)p).getId())) {
		            policyComponent = p;
		        }
		    }
		}
		return policyComponent;
	}

	private static PolicyComponent normalizeAssertion(boolean deep, List<PolicyComponent> childComponentsList,
			PolicyComponent policyComponent) {
		if (deep) {
		    policyComponent = ((Assertion) policyComponent).normalize();                    
		}
		
		if (policyComponent.getPolicyType() == Constants.TYPE_POLICY) {
		    childComponentsList.add(((Policy) policyComponent).getFirstPolicyComponent());
		    
		} else  {
		    ExactlyOne exactlyOne = new ExactlyOne();
		    All all = new All();
		    
		    all.addPolicyComponent(policyComponent);
		    exactlyOne.addPolicyComponent(all);
		    childComponentsList.add(exactlyOne);
		}
		return policyComponent;
	}
    
    private static PolicyComponent computeResultantComponent(List<PolicyComponent> normalizedInnerComponets, 
                                                             short componentType) {
        
        ExactlyOne exactlyOne = new ExactlyOne();
        
        if (componentType == Constants.TYPE_EXACTLYONE) {            
            for (PolicyComponent comp : normalizedInnerComponets) {
                ExactlyOne innerExactlyOne = (ExactlyOne)comp;
                exactlyOne.addPolicyComponents(innerExactlyOne.getPolicyComponents());
            }
            
        } else if ((componentType == Constants.TYPE_POLICY) || (componentType == Constants.TYPE_ALL)) {
            // if the parent type is All then we have to get the cross product
            if (normalizedInnerComponets.size() > 1) {
                exactlyOne = computeAll(normalizedInnerComponets);

            } else {
                // i.e only one element exists in the list then we can safely
                // return that element this is ok even if it is an empty element
                exactlyOne = (ExactlyOne) normalizedInnerComponets.get(0);
            }
        }
        
        return exactlyOne;
    }

	private static ExactlyOne computeAll(List<PolicyComponent> normalizedInnerComponets) {
		ExactlyOne exactlyOne;
		// then we have to get the cross product with each other to process all elements
		Iterator<PolicyComponent> iter = normalizedInnerComponets.iterator();
		// first get the first element
		exactlyOne = (ExactlyOne) iter.next();
		// if this is empty, this is an not admissible policy and total result is equivalent to that
		if (!exactlyOne.isEmpty()) {
		    ExactlyOne currentExactlyOne;

		    while (iter.hasNext()) {
		        currentExactlyOne = (ExactlyOne) iter.next();
		        if (currentExactlyOne.isEmpty()) {
		            // if this is empty, this is an not admissible policy and total 
		            // result is equivalent to that
		            exactlyOne = currentExactlyOne;
		            break;
		        } else {
		            exactlyOne = getCrossProduct(exactlyOne, currentExactlyOne);
		        }
		    }

		}
		return exactlyOne;
	}
    
    private static ExactlyOne getCrossProduct(ExactlyOne exactlyOne1, ExactlyOne exactlyOne2) {
        ExactlyOne crossProduct = new ExactlyOne();
        All crossProductAll;

        All currentAll1;
        All currentAll2;

        for (PolicyComponent pc : exactlyOne1.getPolicyComponents()) {
            currentAll1 = (All)pc;

            for (PolicyComponent pc2 : exactlyOne2.getPolicyComponents()) {
                currentAll2 = (All)pc2;
                crossProductAll = new All();
                crossProductAll.addPolicyComponents(currentAll1.getPolicyComponents());
                crossProductAll.addPolicyComponents(currentAll2.getPolicyComponents());
                crossProduct.addPolicyComponent(crossProductAll);
            }
        }

        return crossProduct;
    }
}
