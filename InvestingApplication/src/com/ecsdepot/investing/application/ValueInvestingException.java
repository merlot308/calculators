/**
 * 
 */
package com.ecsdepot.investing.application;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author Justin
 *
 */
public class ValueInvestingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5169706220264837183L;
	
	public ValueInvestingException() {
		super();
		
	}

	public ValueInvestingException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

	public ValueInvestingException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	public ValueInvestingException(String arg0) {
		super(arg0);
		
	}

	public ValueInvestingException(Throwable arg0) {
		super(arg0);
		
	}
}
