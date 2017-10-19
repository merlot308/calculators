/**
 * 
 */
package com.ecsdepot.investing.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Justin
 *
 */
public class CompanyDO
{
	private String ticker;
	private String companyName;
	private String date;
	private String shares;
	private String netIncome;
	private String netCashFromOperatingActivities;
	private String purchaseOfPlantPropertyAndEquipment;
	private Map<String, List<Annuals>> annuals;

	public CompanyDO()
	{
		super();
		annuals = new HashMap<String, List<Annuals>>();
	}

	public CompanyDO(final String ticker)
	{
		this();
		this.ticker = ticker;
	}

	public String getTicker()
	{
		return ticker;
	}

	public void setTicker(final String ticker)
	{
		this.ticker = ticker;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName()
	{
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	/**
	 * @return the date
	 */
	public String getDate()
	{
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date)
	{
		this.date = date;
	}

	/**
	 * @return the shares
	 */
	public String getShares()
	{
		return shares;
	}

	/**
	 * @param shares
	 *            the shares to set
	 */
	public void setShares(String shares)
	{
		this.shares = shares;
	}

	/**
	 * @return the netIncome
	 */
	public String getNetIncome()
	{
		return netIncome;
	}

	/**
	 * @param netIncome
	 *            the netIncome to set
	 */
	public void setNetIncome(String netIncome)
	{
		this.netIncome = netIncome;
	}

	/**
	 * @return the netCashFromOperatingActivities
	 */
	public String getNetCashFromOperatingActivities()
	{
		return netCashFromOperatingActivities;
	}

	/**
	 * @param netCashFromOperatingActivities
	 *            the netCashFromOperatingActivities to set
	 */
	public void setNetCashFromOperatingActivities(String netCashFromOperatingActivities)
	{
		this.netCashFromOperatingActivities = netCashFromOperatingActivities;
	}

	/**
	 * @return the purchaseOfPlantPropertyAndEquipment
	 */
	public String getPurchaseOfPlantPropertyAndEquipment()
	{
		return purchaseOfPlantPropertyAndEquipment;
	}

	/**
	 * @param purchaseOfPlantPropertyAndEquipment
	 *            the purchaseOfPlantPropertyAndEquipment to set
	 */
	public void setPurchaseOfPlantPropertyAndEquipment(String purchaseOfPlantPropertyAndEquipment)
	{
		this.purchaseOfPlantPropertyAndEquipment = purchaseOfPlantPropertyAndEquipment;
	}

	/**
	 * @return the annuals
	 */
	public Map<String, List<Annuals>> getAnnuals()
	{
		return annuals;
	}

	/**
	 * @param annuals
	 *            the annuals to set, i.e., BVPS, EPS, EPS_GR
	 */
	public void setAnnuals(Map<String, List<Annuals>> annuals)
	{
		this.annuals = annuals;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CompanyDO [ticker=" + ticker + ", companyName=" + companyName + ", date=" + date + ", shares=" + shares
				+ ", netIncome=" + netIncome + ", netCashFromOperatingActivities=" + netCashFromOperatingActivities
				+ ", purchaseOfPlantPropertyAndEquipment=" + purchaseOfPlantPropertyAndEquipment + ", annuals="
				+ annuals + "]";
	}

	
}
