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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class handles looking up service providers on the class path.
 * It implements the system described in:
 *
 * <a href='http://docs.oracle.com/javase/6/docs/technotes/guides/jar/jar.html#Service%20Provider'>JAR
 * File Specification Under Service Provider</a>. Note that this
 * interface is very similar to the one they describe which seems to
 * be missing in the JDK.
 *
 */
public final class Service {

    private static final String ENCODING_UTF_8 = "UTF-8";
	// Remember providers we have looked up before.
    static Map<String, List<?>> instanceMap = new HashMap<>();
    
    private Service() {
        //not constructed
    }
    
    @SuppressWarnings("unchecked")
    private static <T> List<T> cast(List<?> p) {
        return (List<T>)p;
    }

    /**
     * Returns an iterator where each element should implement the
     * interface (or subclass the baseclass) described by cls.  The
     * Classes are found by searching the classpath for service files
     * named: 'META-INF/services/&lt;fully qualified classname&gt; that list
     * fully qualified classnames of classes that implement the
     * service files classes interface.  These classes must have
     * default constructors.
     *
     * @param cls The class/interface to search for providers of.
     * @return The list of providers that implement the cls
     */
    public static synchronized <T> List<? extends T> providers(Class<T> cls) {

        String serviceFile = "META-INF/services/" + cls.getName();

        List<T> l = cast(instanceMap.get(serviceFile));
        if (l != null) {
            return l;
        }

        l = new ArrayList<>();
        instanceMap.put(serviceFile, l);

        ClassLoader cl = null;
        try {
            cl = cls.getClassLoader();
        } catch (SecurityException se) {
            // Ooops! can't get his class loader.
        }
        // Can always request your own class loader. But it might be 'null'.
        if (cl == null) {
            cl = Service.class.getClassLoader();
        }
        if (cl == null) {
            cl = ClassLoader.getSystemClassLoader();
        }

        // No class loader so we can't find 'serviceFile'.
        if (cl == null) {
            return l;
        }

        Enumeration<URL> e;
        try {
            e = cl.getResources(serviceFile);
        } catch (IOException ioe) {
            return l;
        }

        while (e.hasMoreElements()) {
            InputStream is = null;
            readFile(cls, l, cl, e, is);
        }
        return l;
    }

	private static <T> void readFile(Class<T> cls, List<T> l, ClassLoader cl, Enumeration<URL> e, InputStream is) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(e.nextElement().openStream(), ENCODING_UTF_8))) {
		    String line = br.readLine();
		    while (line != null) {
		        line = processLine(cls, l, cl, br, line);
		    }
		} catch (Exception | LinkageError ex) {
		    // Just try the next file...
		}  finally {
		    try {
		        if (is != null) {
		            is.close();
		        }
		    } catch (IOException ex) {
		        //ignore
		    }
		}
	}

	private static <T> String processLine(Class<T> cls, List<T> l, ClassLoader cl, BufferedReader br, String line) throws IOException {
		try {
		    // First strip any comment...
		    int idx = line.indexOf('#');
		    if (idx != -1) {
		        line = line.substring(0, idx);
		    }

		    // Trim whitespace.
		    line = line.trim();

		    // If nothing left then loop around...
		    if (line.length() == 0) {
		        return br.readLine();
		        
		    }

		    // Try and load the class 
		    Object obj = cl.loadClass(line).getConstructor().newInstance();
		    // stick it into our vector...
		    l.add(cls.cast(obj));
		} catch (Exception ex) {
		    // Just try the next line
		}
		return br.readLine();
	}
    
}