/**
 * 
 */
package com.ecsdepot.investing.application;

import com.ecsdepot.investment.calculators.Calculations;
import com.ecsdepot.investment.calculators.InvestingCalculators;

/**
 * @author Justin
 *
 */
public class GrowthRates
{
	private static GrowthRates INSTANCE = new GrowthRates();

	private GrowthRates()
	{
		super();
	}

	public static GrowthRates getInstance()
	{
		return INSTANCE;
	}

	public double calculateGrowthRate(Annuals startingAnnual, Annuals endingAnnual)
	{
		Calculations calculations = new InvestingCalculators();
		double time = endingAnnual.getDataYear().doubleValue() - startingAnnual.getDataYear().doubleValue();
		return calculations.growthRate(time, endingAnnual.getValue(), startingAnnual.getValue());
	}

}
