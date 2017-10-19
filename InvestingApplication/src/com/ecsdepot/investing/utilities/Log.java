/**
 * 
 */
package com.ecsdepot.investing.utilities;

import org.apache.log4j.Logger;

/**
 * @author Justin
 *
 */
public class Log
{
	private static final String CLAZZ = Log.class.getName();
	private Logger log_logger = Logger.getLogger(CLAZZ);
	private Logger logging; 
	public Log(String className)
	{
		super(); 
		this.setLogging(Logger.getLogger(className)); 
	}
	public static void logError(String message, Throwable throwable)
	{
		
	}
	public Logger getLogging()
	{
		return logging;
	}
	public void setLogging(Logger logging)
	{
		this.logging = logging;
	
	}
}
