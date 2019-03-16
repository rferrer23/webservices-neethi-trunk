package org.apache.neethi.logger;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class LoggerWrapperFactory {
	
	private static Map<String,Function<Class,LoggerWrapper>> registered = new HashMap<>();
	static {
		//set the another implementations here
	}
	private LoggerWrapperFactory() {
		// Static factory
	}
	public static <T> LoggerWrapper discoverLogger(Class<T> clazz) {
		LoggerWrapper logger = createDefaultLogger();
		logger.setClass(clazz);
		return logger;
		
	}
	public  static <T> LoggerWrapper discoverLogger(Class<T> clazz,String type) {
		if(registered.containsKey(type) ) {
			return registered.get(type).apply(clazz);
		}
		return discoverLogger(clazz);
		
	}
	
	
	private static LoggerWrapper createDefaultLogger() {
		return new LoggerWrapperImpl();
	}

}
