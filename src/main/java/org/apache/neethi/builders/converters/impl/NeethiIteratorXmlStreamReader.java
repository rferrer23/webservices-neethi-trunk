package org.apache.neethi.builders.converters.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;

public class NeethiIteratorXmlStreamReader implements Iterator<XMLStreamReader> {

	boolean first = true;
	
	final XMLStreamReader reader;
	
	final QName base;
	
	public NeethiIteratorXmlStreamReader(XMLStreamReader xmlStreamReader,QName name) {
		reader=xmlStreamReader;
		base=name;
	}
	
	@Override
	public boolean hasNext() {
        if (first) {
            first = false;
            return reader.getEventType() != XMLStreamReader.END_ELEMENT;
        }
        try {
            int evt = reader.next();
            while (reader.hasNext() 
                && evt != XMLStreamReader.END_ELEMENT 
                && evt != XMLStreamReader.START_ELEMENT) {
                evt = reader.next();
            }
            if (evt == XMLStreamReader.END_ELEMENT
                && !reader.getName().equals(base)) {
                evt = reader.next();
                while (reader.hasNext() 
                    && evt != XMLStreamReader.END_ELEMENT 
                    && evt != XMLStreamReader.START_ELEMENT) {
                    evt = reader.next();
                }
            }
            return evt == XMLStreamReader.START_ELEMENT;
        } catch (Exception ex) {
            return false;
        }
        
    }

	@Override
	public XMLStreamReader next() {
		if(reader==null) {
			throw new NoSuchElementException();
		}
		return reader;
	}

}
