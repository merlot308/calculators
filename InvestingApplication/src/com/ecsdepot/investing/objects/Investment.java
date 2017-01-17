/**
 * 
 */
package com.ecsdepot.investing.objects;

import java.util.Map;

/**
 * @author Justin
 *
 */
public class Investment {

	// this is the ticker or name of the object.
	private String ticker;
	// this includes the 10 year of data that is needed for each object.
	private Map annuals;
	// this is the rate of return or calculated values for an object.
	private double rateOfReturn;
	// this is the annualized rate of return
	private double annualizedRoR;
	// this is the number of outstanding shares
	private double outstandingShares;

	/**
	 * @return the ticker
	 */
	public String getTicker() {
		return ticker;
	}

	/**
	 * @param ticker
	 *            the ticker to set
	 */
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	/**
	 * @return the annuals
	 */
	public Map getAnnuals() {
		return annuals;
	}

	/**
	 * @param annuals
	 *            the annuals to set
	 */
	public void setAnnuals(Map annuals) {
		this.annuals = annuals;
	}

	/**
	 * @return the rateOfReturn
	 */
	public double getRateOfReturn() {
		return rateOfReturn;
	}

	/**
	 * @param rateOfReturn
	 *            the rateOfReturn to set
	 */
	public void setRateOfReturn(double rateOfReturn) {
		this.rateOfReturn = rateOfReturn;
	}

	/**
	 * @return the annualizedRoR
	 */
	public double getAnnualizedRoR() {
		return annualizedRoR;
	}

	/**
	 * @param annualizedRoR
	 *            the annualizedRoR to set
	 */
	public void setAnnualizedRoR(double annualizedRoR) {
		this.annualizedRoR = annualizedRoR;
	}

	/**
	 * @return the outstandingShares
	 */
	public double getOutstandingShares() {
		return outstandingShares;
	}

	/**
	 * @param outstandingShares
	 *            the outstandingShares to set
	 */
	public void setOutstandingShares(double outstandingShares) {
		this.outstandingShares = outstandingShares;
	}

}
