package org.apache.neethi.logger;

import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.MessageSupplier;
import org.apache.logging.log4j.util.Supplier;

public interface LoggerWrapper {

	<T> void setClass(Class<T> clazz);

	void debug(CharSequence arg0);

	void debug(Object arg0);

	void debug(String arg0);

	void debug(Supplier<?> arg0);

	void debug(CharSequence arg0, Throwable arg1);

	void debug(Object arg0, Throwable arg1);

	void debug(String arg0, Object... arg1);

	void debug(String arg0, Supplier<?>... arg1);

	void debug(String arg0, Throwable arg1);

	void debug(Supplier<?> arg0, Throwable arg1);

	void debug(String arg0, Object arg1);

	void error(CharSequence arg0);

	void error(Object arg0);

	void error(String arg0);

	void error(Supplier<?> arg0);

	void error(CharSequence arg0, Throwable arg1);

	void error(Object arg0, Throwable arg1);

	void error(String arg0, Object... arg1);

	void error(String arg0, Supplier<?>... arg1);

	void error(String arg0, Throwable arg1);

	void error(Supplier<?> arg0, Throwable arg1);

	void error(String arg0, Object arg1);

	void fatal(CharSequence arg0);

	void fatal(Object arg0);

	void fatal(String arg0);

	void fatal(Supplier<?> arg0);

	void fatal(CharSequence arg0, Throwable arg1);

	void fatal(Object arg0, Throwable arg1);

	void fatal(String arg0, Object... arg1);

	void fatal(String arg0, Supplier<?>... arg1);

	void fatal(String arg0, Throwable arg1);

	void fatal(Supplier<?> arg0, Throwable arg1);

	void fatal(String arg0, Object arg1);

	void info(CharSequence arg0);

	void info(Object arg0);

	void info(String arg0);

	void info(Supplier<?> arg0);

	void info(Message arg0, Throwable arg1);

	void info(MessageSupplier arg0, Throwable arg1);

	void info(CharSequence arg0, Throwable arg1);

	void info(Object arg0, Throwable arg1);

	void info(String arg0, Object... arg1);

	void info(String arg0, Supplier<?>... arg1);

	void info(String arg0, Throwable arg1);

	void info(Supplier<?> arg0, Throwable arg1);

	void info(String arg0, Object arg1);

	void trace(CharSequence arg0);

	void trace(Object arg0);

	void trace(String arg0);

	void trace(Supplier<?> arg0);

	void trace(MessageSupplier arg0, Throwable arg1);

	void trace(CharSequence arg0, Throwable arg1);

	void trace(Object arg0, Throwable arg1);

	void trace(String arg0, Object... arg1);

	void trace(String arg0, Supplier<?>... arg1);

	void trace(String arg0, Throwable arg1);

	void trace(Supplier<?> arg0, Throwable arg1);

	void trace(String arg0, Object arg1);

	void warn(CharSequence arg0);

	void warn(Object arg0);

	void warn(String arg0);

	void warn(Supplier<?> arg0);

	void warn(CharSequence arg0, Throwable arg1);

	void warn(Object arg0, Throwable arg1);

	void warn(String arg0, Object... arg1);

	void warn(String arg0, Supplier<?>... arg1);

	void warn(String arg0, Throwable arg1);

	void warn(Supplier<?> arg0, Throwable arg1);

	void warn(String arg0, Object arg1);

}