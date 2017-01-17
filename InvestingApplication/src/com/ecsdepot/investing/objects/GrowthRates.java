package com.ecsdepot.investing.objects;

import java.util.Map;

public class GrowthRates {

	private Map<Double, Double> annuals;
	private double currentEquity;
	private double outstandingShares;
	private Map<Double, Double> growthRate; 
	/**
	 * @return the annuals
	 */
	public Map<Double, Double> getAnnuals() {
		return annuals;
	}
	/**
	 * @param annuals the annuals to set
	 */
	public void setAnnuals(Map<Double, Double> annuals) {
		this.annuals = annuals;
	}
	public Map<Double, Double> getGrowthRate() {
		return growthRate;
	}
	public void setGrowthRate(Map<Double, Double> growthRate) {
		this.growthRate = growthRate;
	}
	/**
	 * @return the currentEquity
	 */
	public double getCurrentEquity() {
		return currentEquity;
	}
	/**
	 * @param currentEquity the currentEquity to set
	 */
	public void setCurrentEquity(double currentEquity) {
		this.currentEquity = currentEquity;
	}
	/**
	 * @return the outstandingShares
	 */
	public double getOutstandingShares() {
		return outstandingShares;
	}
	/**
	 * @param outstandingShares the outstandingShares to set
	 */
	public void setOutstandingShares(double outstandingShares) {
		this.outstandingShares = outstandingShares;
	}
	
	
	
	
}
