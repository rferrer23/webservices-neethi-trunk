package org.apache.neethi.util;

import java.util.function.Function;

public class FunctionsUtils {
	
	private FunctionsUtils() {
		//utility class
	}
	
	
	public static <T,E> E applyIfNotNull(T value, Function<T,E> fun, E defaultValue) {
		if(value !=null) {
			return fun.apply(value);
		}
		return defaultValue;
	}

}
