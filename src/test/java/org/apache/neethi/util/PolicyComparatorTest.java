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

package org.apache.neethi.util;

import org.apache.neethi.PolicyTestCase;
import org.apache.neethi.policy.Policy;
import org.junit.Test;

public class PolicyComparatorTest extends PolicyTestCase {

    @Test
    public void testPolicyComparison1() {
        Policy policy1 = new Policy();
        policy1.setName("Name_1");
        Policy policy2 = new Policy();
        policy2.setName("Name_2");

        boolean res = PolicyComparator.compare(policy1, policy2);
        assertEquals(false, res);
    }

}