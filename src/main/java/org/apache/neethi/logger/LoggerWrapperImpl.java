package org.apache.neethi.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.MessageSupplier;
import org.apache.logging.log4j.util.Supplier;

public class LoggerWrapperImpl implements LoggerWrapper {
	
	
	private Logger logger;

	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#setClass(java.lang.Class)
	 */
	public <T> void setClass(Class<T> clazz) {
		logger = LogManager.getLogger(clazz);
	}


	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#debug(java.lang.CharSequence)
	 */
	public void debug(CharSequence arg0) {
		logger.debug(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#debug(java.lang.Object)
	 */
	public void debug(Object arg0) {
		logger.debug(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#debug(java.lang.String)
	 */
	public void debug(String arg0) {
		logger.debug(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#debug(org.apache.logging.log4j.util.Supplier)
	 */
	public void debug(Supplier<?> arg0) {
		logger.debug(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#debug(java.lang.CharSequence, java.lang.Throwable)
	 */
	public void debug(CharSequence arg0, Throwable arg1) {
		logger.debug(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#debug(java.lang.Object, java.lang.Throwable)
	 */
	public void debug(Object arg0, Throwable arg1) {
		logger.debug(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#debug(java.lang.String, java.lang.Object)
	 */
	public void debug(String arg0, Object... arg1) {
		logger.debug(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#debug(java.lang.String, org.apache.logging.log4j.util.Supplier)
	 */
	public void debug(String arg0, Supplier<?>... arg1) {
		logger.debug(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#debug(java.lang.String, java.lang.Throwable)
	 */
	public void debug(String arg0, Throwable arg1) {
		logger.debug(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#debug(org.apache.logging.log4j.util.Supplier, java.lang.Throwable)
	 */
	public void debug(Supplier<?> arg0, Throwable arg1) {
		logger.debug(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#debug(java.lang.String, java.lang.Object)
	 */
	public void debug(String arg0, Object arg1) {
		logger.debug(arg0,arg1);
	}

	
	

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#error(java.lang.CharSequence)
	 */
	public void error(CharSequence arg0) {
		logger.error(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#error(java.lang.Object)
	 */
	public void error(Object arg0) {
		logger.error(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#error(java.lang.String)
	 */
	public void error(String arg0) {
		logger.error(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#error(org.apache.logging.log4j.util.Supplier)
	 */
	public void error(Supplier<?> arg0) {
		logger.error(arg0);
	}


	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#error(java.lang.CharSequence, java.lang.Throwable)
	 */
	public void error(CharSequence arg0, Throwable arg1) {
		logger.error(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#error(java.lang.Object, java.lang.Throwable)
	 */
	public void error(Object arg0, Throwable arg1) {
		logger.error(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#error(java.lang.String, java.lang.Object)
	 */
	public void error(String arg0, Object... arg1) {
		logger.error(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#error(java.lang.String, org.apache.logging.log4j.util.Supplier)
	 */
	public void error(String arg0, Supplier<?>... arg1) {
		logger.error(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#error(java.lang.String, java.lang.Throwable)
	 */
	public void error(String arg0, Throwable arg1) {
		logger.error(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#error(org.apache.logging.log4j.util.Supplier, java.lang.Throwable)
	 */
	public void error(Supplier<?> arg0, Throwable arg1) {
		logger.error(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#error(java.lang.String, java.lang.Object)
	 */
	public void error(String arg0, Object arg1) {
		logger.error(arg0,arg1);
	}

	
	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#fatal(java.lang.CharSequence)
	 */
	public void fatal(CharSequence arg0) {
		logger.fatal(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#fatal(java.lang.Object)
	 */
	public void fatal(Object arg0) {
		logger.fatal(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#fatal(java.lang.String)
	 */
	public void fatal(String arg0) {
		logger.fatal(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#fatal(org.apache.logging.log4j.util.Supplier)
	 */
	public void fatal(Supplier<?> arg0) {
		logger.fatal(arg0);
	}



	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#fatal(java.lang.CharSequence, java.lang.Throwable)
	 */
	public void fatal(CharSequence arg0, Throwable arg1) {
		logger.fatal(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#fatal(java.lang.Object, java.lang.Throwable)
	 */
	public void fatal(Object arg0, Throwable arg1) {
		logger.fatal(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#fatal(java.lang.String, java.lang.Object)
	 */
	public void fatal(String arg0, Object... arg1) {
		logger.fatal(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#fatal(java.lang.String, org.apache.logging.log4j.util.Supplier)
	 */
	public void fatal(String arg0, Supplier<?>... arg1) {
		logger.fatal(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#fatal(java.lang.String, java.lang.Throwable)
	 */
	public void fatal(String arg0, Throwable arg1) {
		logger.fatal(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#fatal(org.apache.logging.log4j.util.Supplier, java.lang.Throwable)
	 */
	public void fatal(Supplier<?> arg0, Throwable arg1) {
		logger.fatal(arg0,arg1);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#fatal(java.lang.String, java.lang.Object)
	 */
	public void fatal(String arg0, Object arg1) {
		logger.fatal(arg0,arg1);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#info(java.lang.CharSequence)
	 */
	public void info(CharSequence arg0) {
		logger.info(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#info(java.lang.Object)
	 */
	public void info(Object arg0) {
		logger.info(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#info(java.lang.String)
	 */
	public void info(String arg0) {
		logger.info(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#info(org.apache.logging.log4j.util.Supplier)
	 */
	public void info(Supplier<?> arg0) {
		logger.info(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#info(org.apache.logging.log4j.message.Message, java.lang.Throwable)
	 */
	public void info(Message arg0, Throwable arg1) {
		logger.info(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#info(org.apache.logging.log4j.util.MessageSupplier, java.lang.Throwable)
	 */
	public void info(MessageSupplier arg0, Throwable arg1) {
		logger.info(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#info(java.lang.CharSequence, java.lang.Throwable)
	 */
	public void info(CharSequence arg0, Throwable arg1) {
		logger.info(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#info(java.lang.Object, java.lang.Throwable)
	 */
	public void info(Object arg0, Throwable arg1) {
		logger.info(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#info(java.lang.String, java.lang.Object)
	 */
	public void info(String arg0, Object... arg1) {
		logger.info(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#info(java.lang.String, org.apache.logging.log4j.util.Supplier)
	 */
	public void info(String arg0, Supplier<?>... arg1) {
		logger.info(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#info(java.lang.String, java.lang.Throwable)
	 */
	public void info(String arg0, Throwable arg1) {
		logger.info(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#info(org.apache.logging.log4j.util.Supplier, java.lang.Throwable)
	 */
	public void info(Supplier<?> arg0, Throwable arg1) {
		logger.info(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#info(java.lang.String, java.lang.Object)
	 */
	public void info(String arg0, Object arg1) {
		logger.info(arg0,arg1);
	}

	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#trace(java.lang.CharSequence)
	 */
	public void trace(CharSequence arg0) {
		logger.trace(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#trace(java.lang.Object)
	 */
	public void trace(Object arg0) {
		logger.trace(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#trace(java.lang.String)
	 */
	public void trace(String arg0) {
		logger.trace(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#trace(org.apache.logging.log4j.util.Supplier)
	 */
	public void trace(Supplier<?> arg0) {
		logger.trace(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#trace(org.apache.logging.log4j.util.MessageSupplier, java.lang.Throwable)
	 */
	public void trace(MessageSupplier arg0, Throwable arg1) {
		logger.trace(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#trace(java.lang.CharSequence, java.lang.Throwable)
	 */
	public void trace(CharSequence arg0, Throwable arg1) {
		logger.trace(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#trace(java.lang.Object, java.lang.Throwable)
	 */
	public void trace(Object arg0, Throwable arg1) {
		logger.trace(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#trace(java.lang.String, java.lang.Object)
	 */
	public void trace(String arg0, Object... arg1) {
		logger.trace(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#trace(java.lang.String, org.apache.logging.log4j.util.Supplier)
	 */
	public void trace(String arg0, Supplier<?>... arg1) {
		logger.trace(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#trace(java.lang.String, java.lang.Throwable)
	 */
	public void trace(String arg0, Throwable arg1) {
		logger.trace(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#trace(org.apache.logging.log4j.util.Supplier, java.lang.Throwable)
	 */
	public void trace(Supplier<?> arg0, Throwable arg1) {
		logger.trace(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#trace(java.lang.String, java.lang.Object)
	 */
	public void trace(String arg0, Object arg1) {
		logger.trace(arg0,arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#warn(java.lang.CharSequence)
	 */
	public void warn(CharSequence arg0) {
		logger.warn(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#warn(java.lang.Object)
	 */
	public void warn(Object arg0) {
		logger.warn(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#warn(java.lang.String)
	 */
	public void warn(String arg0) {
		logger.warn(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#warn(org.apache.logging.log4j.util.Supplier)
	 */
	public void warn(Supplier<?> arg0) {
		logger.warn(arg0);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#warn(java.lang.CharSequence, java.lang.Throwable)
	 */
	public void warn(CharSequence arg0, Throwable arg1) {
		logger.warn(arg0, arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#warn(java.lang.Object, java.lang.Throwable)
	 */
	public void warn(Object arg0, Throwable arg1) {
		logger.warn(arg0, arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#warn(java.lang.String, java.lang.Object)
	 */
	public void warn(String arg0, Object... arg1) {
		logger.warn(arg0, arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#warn(java.lang.String, org.apache.logging.log4j.util.Supplier)
	 */
	public void warn(String arg0, Supplier<?>... arg1) {
		logger.warn(arg0, arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#warn(java.lang.String, java.lang.Throwable)
	 */
	public void warn(String arg0, Throwable arg1) {
		logger.warn(arg0, arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#warn(org.apache.logging.log4j.util.Supplier, java.lang.Throwable)
	 */
	public void warn(Supplier<?> arg0, Throwable arg1) {
		logger.warn(arg0, arg1);
	}

	
	/* (non-Javadoc)
	 * @see org.apache.neethi.logger.LoggerWrapper#warn(java.lang.String, java.lang.Object)
	 */
	public void warn(String arg0, Object arg1) {
		logger.warn(arg0, arg1);
	}


	
	

}
