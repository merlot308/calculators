/**
 * 
 */
package com.ecsdepot.investing.application;

import java.util.HashMap;
import java.util.Map;

import com.ecsdepot.investing.intrinio.IntrinioConstants;

/**
 * @author Justin
 *
 */
public class ConstantsMap
{
	private static ConstantsMap INSTANCE = new ConstantsMap();
	private Map<String, String> constantsMap = new HashMap<String, String>();
	private Map<String, String> yrToYrConstants = new HashMap<String, String>();
	private Map<String, String> bigFiveToCurrentConstants = new HashMap<String, String>();
	private Map<String, String> preCalcGrowthMap = new HashMap<String, String>();

	private ConstantsMap()
	{
		buildConstantsMap();
		buildBigFiveGrowthRateConstants();
		buildBigFiveToCurrentGrowthRateConstants();
		buildPreCalculatedGrowthMap();
	}

	public static ConstantsMap getInstance()
	{
		return INSTANCE;
	}

	public Map<String, String> getConstantsMap()
	{
		return this.constantsMap;
	}

	public void addConstantsToMap(String key, String value)
	{
		this.constantsMap.put(key, value);
	}

	private void buildConstantsMap()
	{
		constantsMap = new HashMap<String, String>();
		constantsMap.put(IntrinioConstants.BOOK_VALUE_PER_SHARE, InvestConstants.BVPS);
		constantsMap.put(IntrinioConstants.EARNING_PER_SHARE_GROWTH, InvestConstants.EPS_GROWTH_RATE);
		constantsMap.put(IntrinioConstants.BASIC_EPS, InvestConstants.EPS);
		constantsMap.put(IntrinioConstants.RETURN_ON_EQUITY, InvestConstants.ROE);
		constantsMap.put(IntrinioConstants.RETURN_ON_INVESTED_CAPITOL, InvestConstants.ROIC);
		constantsMap.put(IntrinioConstants.SALES, InvestConstants.SALES);
		// for ROIC you need NOPAT, Equity, and Debt
		constantsMap.put(IntrinioConstants.NET_OPERATING_PROFIT_AFTER_TAX, InvestConstants.NOPAT);
		constantsMap.put(IntrinioConstants.S_DEBT, InvestConstants.TOTAL_DEBT);
	}

	public Map<String, String> getPreCalcGrowthRatesMap()
	{
		return this.preCalcGrowthMap;
	}

	private void buildPreCalculatedGrowthMap()
	{
		preCalcGrowthMap.put(InvestConstants.EPS_GROWTH_RATE, InvestConstants.EPS_GROWTH_RATE);
	}

	public Map<String, String> getBigFiveYrToYrGrowRateCons()
	{
		return this.yrToYrConstants;
	}

	private void buildBigFiveGrowthRateConstants()
	{
		yrToYrConstants.put(InvestConstants.BVPS, InvestConstants.BVPS_GROWTH_RATE);
		yrToYrConstants.put(InvestConstants.ROIC, InvestConstants.ROIC_GROWTH_RATE);
		yrToYrConstants.put(InvestConstants.EPS, InvestConstants.EPS_GROWTH_RATE);
		yrToYrConstants.put(InvestConstants.FCF_PS, InvestConstants.FCF_PS_GROWTH_RATE);
		yrToYrConstants.put(InvestConstants.ROE, InvestConstants.ROE_GROWTH_RATE);
	}

	public Map<String, String> getBigFiveToCurrentGrowRateCons()
	{
		return this.bigFiveToCurrentConstants;
	}

	private void buildBigFiveToCurrentGrowthRateConstants()
	{
		bigFiveToCurrentConstants.put(InvestConstants.BVPS, InvestConstants.BVPS_GROWTH_RATE_TO_CURRENT);
		bigFiveToCurrentConstants.put(InvestConstants.ROIC, InvestConstants.ROIC_GROWTH_TO_CURRENT);
		bigFiveToCurrentConstants.put(InvestConstants.EPS, InvestConstants.EPS_GROWTH_RATE_TO_CURRENT);
		bigFiveToCurrentConstants.put(InvestConstants.FCF_PS, InvestConstants.FCF_PS_GR_TO_CURRENT);
		bigFiveToCurrentConstants.put(InvestConstants.ROE, InvestConstants.ROE_GROWTH_RATE_TO_CURRENT);
	}
}
