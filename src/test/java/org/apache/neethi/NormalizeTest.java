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

package org.apache.neethi;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.apache.neethi.policy.Policy;
import org.apache.neethi.service.impl.PolicyBuilder;
import org.apache.neethi.util.PolicyComparator;

import org.junit.Test;

public class NormalizeTest extends PolicyTestCase {
    
    PolicyBuilder mgr;

    public NormalizeTest() {
    }

    @Test
    public void testDOM() throws Exception {
        doTest("samples", "normalized", 1);
    }
    @Test
    public void testStax() throws Exception {
        doTest("samples", "normalized", 2);
    }

    @Test
    public void testDOMW3C() throws Exception {
        registry.register("#Policy1",
                          getPolicy("w3tests" + File.separator + "Common/Protection.xml", 1));
        doTest("w3tests", "w3tests" + File.separator + "Normalized", 1);
    }

    
    public void doTest(String base, String normalized, int type) throws Exception {
        doTest(base, normalized, type, new ArrayList<String>());
    }
    public void doTest(String base, String normalized, int type,
                       List<String> excludes) throws Exception {
        File file = new File(testResourceDir + File.separator + normalized);
        for (String name : file.list()) {
            if (excludes.contains(name)) {
                continue;
            }
            if (name.startsWith(".")) {
                continue;
            }
            
            String r1 = base + File.separator + name;
            String r2 = normalized + File.separator + name;
            
            Policy p1 = getPolicy(r1, type);
            p1 = (Policy) p1.normalize(true);
            Policy p2 = getPolicy(r2, type);
            
            if (!PolicyComparator.compare(p1, p2)) {
                XMLStreamWriter writer;
                
                writer = XMLOutputFactory.newInstance().createXMLStreamWriter(System.out);
                p1.serialize(writer);
                writer.flush();
                
                System.out.println("\n ------------ \n");
                
                writer = XMLOutputFactory.newInstance().createXMLStreamWriter(System.out);
                p2.serialize(writer);
                writer.flush();
                
                fail(name + " normalize() FAILED");
            } else {
                XMLStreamWriter writer;
                
                writer = XMLOutputFactory.newInstance().createXMLStreamWriter(new StringWriter());
                p1.serialize(writer);
                writer = XMLOutputFactory.newInstance().createXMLStreamWriter(new StringWriter());
                p2.serialize(writer);
            }
            
            
        }
    }
}