package com.ecsdepot.investing.application;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateCompanies
{
	private List<CompanyDO> companiesList = new ArrayList<CompanyDO>(); 
	/**
	 * @return the companiesList
	 */
	public List<CompanyDO> getCompaniesList()
	{
		return companiesList;
	}

	/**
	 * @param companiesList the companiesList to set
	 */
	public void setCompaniesList(List<CompanyDO> companiesList)
	{
		this.companiesList = companiesList;
	}

	public CreateCompanies()
	{
		CompanyDO company = new CompanyDO();
		company.setCompanyName("Ford");
		company.setDate(Calendar.getInstance().getTime().toString());
		company.setShares("3900.0");
		company.setTicker("f");
//		company.setStockPrice(BigDecimal.valueOf(11.14));
//		company.setTotalOutstandingSharesAnnuals(getTotalSharesOutstandingList());
//		company.setNetIncomeAnnuals(getNetIncomeList());
//		company.setEarningsPerShare(BigDecimal.valueOf(1.4));
//		company.setEquityAnnuals(getEquityList());
//		company.setOperatingCashAnnuals(getOperatingCashList());
		companiesList.add(company);

	}

	private List<Annuals> getOperatingCashList()
	{
		List<Annuals> annualsList = new ArrayList<Annuals>();
		Annuals annual = new Annuals();
		annual.setDataYear(BigDecimal.valueOf(2013));
		annual.setValue(BigDecimal.valueOf(3847.0));
		annual.setType(InvestConstants.OPERATING_CASH);

		Annuals annual1 = new Annuals();
		annual1.setDataYear(BigDecimal.valueOf(2014));
		annual1.setValue(BigDecimal.valueOf(7044.0));
		annual1.setType(InvestConstants.OPERATING_CASH);

		Annuals annual2 = new Annuals();
		annual2.setDataYear(BigDecimal.valueOf(2015));
		annual2.setValue(BigDecimal.valueOf(8974.0));
		annual2.setType(InvestConstants.OPERATING_CASH);

		Annuals annual3 = new Annuals();
		annual3.setDataYear(BigDecimal.valueOf(2016));
		annual3.setValue(BigDecimal.valueOf(12800.0));
		annual3.setType(InvestConstants.OPERATING_CASH);

		annualsList.add(annual);
		annualsList.add(annual1);
		annualsList.add(annual2);
		annualsList.add(annual3);
		return annualsList;
		
	}

	/**
	 * Book value is also Equity found on the Balance Sheet. 
	 * @return
	 */
	private List<Annuals> getEquityList()
	{
		List<Annuals> annualsList = new ArrayList<Annuals>();
		Annuals annual = new Annuals();
		annual.setDataYear(BigDecimal.valueOf(2013));
		annual.setValue(BigDecimal.valueOf(26112.0));
		annual.setType(InvestConstants.EQUITY);

		Annuals annual1 = new Annuals();
		annual1.setDataYear(BigDecimal.valueOf(2014));
		annual1.setValue(BigDecimal.valueOf(24438.0));
		annual1.setType(InvestConstants.EQUITY);

		Annuals annual2 = new Annuals();
		annual2.setDataYear(BigDecimal.valueOf(2015));
		annual2.setValue(BigDecimal.valueOf(28642.0));
		annual2.setType(InvestConstants.EQUITY);

		Annuals annual3 = new Annuals();
		annual3.setDataYear(BigDecimal.valueOf(2016));
		annual3.setValue(BigDecimal.valueOf(29170));
		annual3.setType(InvestConstants.EQUITY);

		annualsList.add(annual);
		annualsList.add(annual1);
		annualsList.add(annual2);
		annualsList.add(annual3);
		return annualsList;
	}

	private List<Annuals> getTotalSharesOutstandingList()
	{
		List<Annuals> annualsList = new ArrayList<Annuals>();
		Annuals annual = new Annuals();
		annual.setDataYear(BigDecimal.valueOf(2013));
		annual.setValue(BigDecimal.valueOf(3984.0));
		annual.setType(InvestConstants.TOTAL_SHARES_OUTSTANDING);

		Annuals annual1 = new Annuals();
		annual1.setDataYear(BigDecimal.valueOf(2014));
		annual1.setValue(BigDecimal.valueOf(4009.0));
		annual1.setType(InvestConstants.TOTAL_SHARES_OUTSTANDING);

		Annuals annual2 = new Annuals();
		annual2.setDataYear(BigDecimal.valueOf(2015));
		annual2.setValue(BigDecimal.valueOf(4031.0));
		annual2.setType(InvestConstants.TOTAL_SHARES_OUTSTANDING);

		Annuals annual3 = new Annuals();
		annual3.setDataYear(BigDecimal.valueOf(2016));
		annual3.setValue(BigDecimal.valueOf(3931.0));
		annual3.setType(InvestConstants.TOTAL_SHARES_OUTSTANDING);

		annualsList.add(annual);
		annualsList.add(annual1);
		annualsList.add(annual2);
		annualsList.add(annual3);
		return annualsList;
	}

	private List<Annuals> getNetIncomeList()
	{
		List<Annuals> annualsList = new ArrayList<Annuals>();
		Annuals annual = new Annuals();
		annual.setDateCalculated(Calendar.getInstance().getTime());
		annual.setType(InvestConstants.NET_INCOME);
		annual.setValue(BigDecimal.valueOf(11953.0));
		annual.setDataYear(BigDecimal.valueOf(2013));
		annualsList.add(annual);
		
		Annuals annual2 = new Annuals();
		annual2.setDateCalculated(Calendar.getInstance().getTime());
		annual2.setType(InvestConstants.NET_INCOME);
		annual2.setValue(new BigDecimal(1231.0));
		BigDecimal val2 = new BigDecimal(2014);
		annual2.setDataYear(val2);
		
		Annuals annual3 = new Annuals();
		annual3.setDateCalculated(Calendar.getInstance().getTime());
		annual3.setType(InvestConstants.NET_INCOME);
		annual3.setValue(BigDecimal.valueOf(7373.0));
		annual3.setDataYear(BigDecimal.valueOf(2015));
		
		Annuals annual4 = new Annuals();
		annual4.setDateCalculated(Calendar.getInstance().getTime());
		annual4.setType(InvestConstants.NET_INCOME);
		annual4.setValue(BigDecimal.valueOf(4596.0));
		annual4.setDataYear(BigDecimal.valueOf(2016));
		
		annualsList.add(annual);
		annualsList.add(annual2);
		annualsList.add(annual3);
		annualsList.add(annual4);
		return annualsList;
	}

	
	public void captureData()
	{
		

	}
}
