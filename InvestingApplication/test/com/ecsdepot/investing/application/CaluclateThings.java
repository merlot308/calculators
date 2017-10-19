package com.ecsdepot.investing.application;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import com.ecsdepot.investment.calculators.Calculations;
import com.ecsdepot.investment.calculators.InvestingCalculators;

public class CaluclateThings
{

	public static void main(String[] args)
	{

		CreateCompanies createCompanies = new CreateCompanies();
		List<CompanyDO> companyList = createCompanies.getCompaniesList();
		for (CompanyDO company : companyList)
		{
			/*
			 * EPS == net income / # shares
			 */
//			company.setEpsAnnuals(calculateValuePerShare(company.getNetIncomeList(),
//					company.getTotalOutstandingSharesAnnuals(), InvestConstants.EPS));
//			company.setEpsGrowthRates(calculateGrowthRates(company.getEpsAnnuals(), InvestConstants.EPS_GROWTH_RATE));
//			company.setBvpsAnnuals(calculateValuePerShare(company.getEquityList(),
//					company.getTotalOutstandingSharesAnnuals(), InvestConstants.BVPS));
//			company.setBvpsGrowthRates(
//					calculateGrowthRates(company.getBvpsAnnuals(), InvestConstants.BVPS_GROWTH_RATE));
//
//			// TODO - look at the free cash flow and operating cash annuals
//			company.setFreeCashFlowPerShareAnnuals(calculateValuePerShare(company.getOperatingCashAnnuals(),
//					company.getTotalOutstandingSharesAnnuals(), InvestConstants.FCF_PS));
//			System.out.println(company.getFreeCashFlowPerSharesList());
		}
	}

	/**
	 * Calculates the growth rate of annuals for a given type.
	 * 
	 * @param sourceAnnuals
	 *            - The {@link Annuals} to calculate to growth rates.
	 * @param type
	 *            - {@link String} of the type of annuals to be calculated.
	 * @return - {@link List} of {@link Annuals} containing the calculated
	 *         growth rates
	 */
	public static List<Annuals> calculateGrowthRates(List<Annuals> sourceAnnuals, String type)
	{
		List<Annuals> growthRatesList = new ArrayList<Annuals>();
		for (Annuals sourceAnnual : sourceAnnuals)
		{
			Calculations calcs = new InvestingCalculators();
			BigDecimal startYear = sourceAnnual.getDataYear();
			BigDecimal endYear = sourceAnnuals.get((sourceAnnuals.size() - 1)).getDataYear();
			BigDecimal endValue = sourceAnnuals.get((sourceAnnuals.size() - 1)).getValue();
			BigDecimal startValue = sourceAnnual.getValue();
			int time = Math.abs(startYear.intValue() - endYear.intValue());
			double growthRate = calcs.growthRate(time, endValue, startValue);
			Annuals calculatedAnnual = new Annuals();
			try
			{
				calculatedAnnual = new Annuals(BigDecimal.valueOf(growthRate), endYear,
						Calendar.getInstance().getTime(), type);
			} catch (NumberFormatException nef)
			{
				calculatedAnnual = new Annuals(InvestConstants.ZERO_VALUE, endYear, Calendar.getInstance().getTime(),
						type);
			}
			growthRatesList.add(calculatedAnnual);
		}
		return growthRatesList;
	}

	/**
	 * Takes a {@link List} of {@link Annuals} and divides with another
	 * {@link List} of {@link Annuals} of total outstanding common stock or
	 * shares to give a Value Per Share. Returns a {@link List} of calculated
	 * {@link Annuals}.
	 * 
	 * @param valuesList
	 *            - {@link List} of {@link Annuals} to divide by the list of
	 *            shares for a value per share
	 * @param totalSharesList
	 *            - {@link List} of {@link Annuals} for outstanding shares for
	 *            each year
	 * @param type
	 *            - {@link String} of the Type of annuals being calculated.
	 * @return - {@link List} of {@link Annuals} containing calculated values
	 *         per share for each year
	 */
	public static List<Annuals> calculateValuePerShare(List<Annuals> valuesList, List<Annuals> totalSharesList,
			String type)
	{
		Collections.sort(valuesList, Annuals.yearComparator);
		Collections.sort(totalSharesList, Annuals.yearComparator);
		List<Annuals> calculatedAnnualsList = new ArrayList<Annuals>();
		for (Annuals sourceValues : valuesList)
		{
			BigDecimal startYear = sourceValues.getDataYear();

			for (Annuals sharesOustanding : totalSharesList)
			{
				if (sharesOustanding.getDataYear().equals(startYear))
				{
					BigDecimal newValue = sourceValues.getValue().divide(sharesOustanding.getValue(), 2,
							RoundingMode.HALF_UP);
					Annuals calculatedAnnual = new Annuals(newValue, startYear, Calendar.getInstance().getTime(), type);
					calculatedAnnualsList.add(calculatedAnnual);
				}
			}
			Collections.sort(calculatedAnnualsList, Annuals.yearComparator);
		}
		return calculatedAnnualsList;
	}

	/**
	 * This is how Phil calculates the EPS on the {@link ruleoneinvesting.com}
	 * web site.
	 * 
	 * @param company
	 * @param type
	 * @return
	 */
	public List<Annuals> philEPSCalculate(CompanyDO company, String type)
	{
//		List<Annuals> netIncomeAnnuals = company.getNetIncomeList();
//		Collections.sort(netIncomeAnnuals, Annuals.yearComparator);
//		List<Annuals> totalSharesList = company.getTotalOutstandingSharesAnnuals();
//		Collections.sort(totalSharesList, Annuals.yearComparator);
		List<Annuals> epsAnnuals = new ArrayList<Annuals>();
//		for (Annuals netIncome : netIncomeAnnuals)
//		{
//			BigDecimal netYear = netIncome.getDataYear();
//			BigDecimal currentShares = BigDecimal.valueOf(Double.valueOf(company.getShares()));
//			BigDecimal epsValue = netIncome.getValue().divide(currentShares, 2, RoundingMode.HALF_UP);
//			System.out.println("Cuurent EPS with todays shares: " + " Net Year" + netYear + ": " + epsValue);
//			// type == InvestConstants.EPS
//			Annuals eps = new Annuals(epsValue, netYear, Calendar.getInstance().getTime(), type);
//			epsAnnuals.add(eps);
//		}
//		Collections.sort(epsAnnuals, Annuals.yearComparator);
		return epsAnnuals;
	}
}
