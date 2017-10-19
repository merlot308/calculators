/**
 * 
 */
package com.ecsdepot.investing.application;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.util.TextUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.ecsdepot.investing.intrinio.IntrinioConstants;
import com.ecsdepot.investing.utilities.ConversionUtility;

/**
 * @author Justin
 *
 */
public class Companies
{

	private static Companies INSTANCE = new Companies();
	private static Logger LUGGER = Logger.getLogger(Companies.class);
	private Map<String, CompanyDO> companiesMap = new HashMap<String, CompanyDO>();

	protected Companies()
	{
	}

	public static Companies getInstance()
	{
		return INSTANCE;
	}

	/**
	 * Adds {@link CompanyDO} to map of companies if it is not already existing.
	 * 
	 * @param company
	 */
	public void addCompany(CompanyDO company)
	{
		if (!companiesMap.containsKey(company.getTicker()))
		{
			companiesMap.put(company.getTicker(), company);
		}
	}

	/**
	 * Creates a {@link CompanyDO} with the ticker provided and adds to the map.
	 * 
	 * @param ticker
	 */
	public void addCompany(String ticker)
	{
		if (!companiesMap.containsKey(ticker))
		{
			CompanyDO newCompany = new CompanyDO();
			newCompany.setTicker(ticker);
			companiesMap.put(ticker, newCompany);
		}
	}

	/**
	 * Uses the parms to request companies and the results to create a
	 * {@link CompanyDO} and add it to the {@link Map}.
	 * 
	 * @param parms
	 * @param results
	 */
	public void createCompanyFromFinancials(Map<String, String> parms, Map<String, String> results)
	{
		CompanyDO company = new CompanyDO();
		company.setTicker(parms.get(IntrinioConstants.S_IDENTIFIER));
		companiesMap.put(company.getTicker(), company);
	}

	/**
	 * 
	 * @return {@link Map} of companies
	 */
	public Map<String, CompanyDO> getCompaniesMap()
	{
		return companiesMap;
	}

	/**
	 * Allows replacing the map of companies.
	 * 
	 * @param companiesMap
	 */
	protected void setCompaniesMap(Map<String, CompanyDO> companiesMap)
	{
		this.companiesMap = companiesMap;
	}

	public void something(Map<String, String> something)
	{

	}

	public void addCompanyFromHistoricals(Map<String, String> parms, Map<String, String> results)
	{
		String ticker = parms.get(IntrinioConstants.S_TICKER);
		// convert IntrinioConstants to InvestConstant
		String type = ConstantsMap.getInstance().getConstantsMap().get(parms.get(IntrinioConstants.S_ITEM));

		if (!companiesMap.isEmpty() && companiesMap.containsKey(ticker))
		{

			if (companiesMap.get(ticker).getAnnuals().containsKey(type))
			{
				LUGGER.log(Level.DEBUG, "calling update annuals");
				updateCompanyFromHistoricals(parms, results);
			}
		} else
		{
			LUGGER.log(Level.DEBUG, "creating new annual");
			CompanyDO company = new CompanyDO(ticker);

			/* 
			 * for each key in the result, the key is the date and the value
			 * is the value of the item requested
			 */
			List<Annuals> resultAnnualList = new ArrayList<Annuals>();
			for (String key : results.keySet())
			{
				BigDecimal year = ConversionUtility.getInstance().convertDateToBigDecimal(key);
				String result = results.get(key);
				BigDecimal value = new BigDecimal(0.0);
				if (!TextUtils.isEmpty(result) && !result.equals(InvestConstants.S_NULL))
				{
					value = BigDecimal.valueOf(Double.valueOf(result));
				}

				Annuals resultAnnuals = createAnnuals(value.toString(), year.toString(), type);
				resultAnnualList.add(resultAnnuals);

			}
			// sort results
			resultAnnualList.sort(Annuals.yearComparator);
			// add annuals to company
			company.getAnnuals().put(type, resultAnnualList);
			// add company to map
			companiesMap.put(ticker, company);
		}

	}

	public void updateCompanyFromHistoricals(Map<String, String> parms, Map<String, String> results)
	{
		// get the ticker for the company and retrieve from companies map
		CompanyDO company = companiesMap.get(parms.get(IntrinioConstants.S_TICKER));
		// convert Intrinio constants to Common Constants - InvestingConstants
		String type = ConstantsMap.getInstance().getConstantsMap().get(parms.get(IntrinioConstants.S_ITEM));

		List<Annuals> resultAnnualList = new ArrayList<Annuals>();
		// for each key in the result, the key is the date and the value is the
		// value of the item requested
		for (String key : results.keySet())
		{

			BigDecimal year = ConversionUtility.getInstance().convertDateToBigDecimal(key);

			String result = results.get(key);
			LUGGER.log(Level.TRACE, "Key: " + key + " YEAR: " + year + " RESULT: " + result + " TYPE: " + type);
			BigDecimal value = new BigDecimal(0.0);
			if (!TextUtils.isEmpty(result) && !result.equals(InvestConstants.S_NULL))
			{
				value = BigDecimal.valueOf(Double.valueOf(result));
			}
			// create an annual of the result
			Annuals annual = new Annuals(value, year, Calendar.getInstance().getTime(), type);
			resultAnnualList.add(annual);
		}
		// place annuals to company
		List<Annuals> existingAnnuals = company.getAnnuals().get(type);
		if (existingAnnuals != null && !existingAnnuals.isEmpty())
		{
			company.getAnnuals().get(type).addAll(resultAnnualList);
		} else
		{
			company.getAnnuals().put(type, resultAnnualList);
		}
	}

	/**
	 * @param companies
	 */
	public void calculateBigFiveRates(CompanyDO company)
	{
		Map<String, String> yrToYrConsMap = ConstantsMap.getInstance().getBigFiveYrToYrGrowRateCons();
		Map<String, String> yrToCurrConsMap = ConstantsMap.getInstance().getBigFiveToCurrentGrowRateCons();
		// do growth rate calculations
		LUGGER.log(Level.DEBUG, "Calculating Big Five: ");
		Map<String, List<Annuals>> annualsMap = company.getAnnuals();
		Map<String, List<Annuals>> calculatedAnnuals = new HashMap<String, List<Annuals>>();
		for (String type : annualsMap.keySet())
		{
			LUGGER.log(Level.DEBUG, "TYPE: " + type);
			String typeYrToYr = yrToYrConsMap.get(type);
			LUGGER.log(Level.TRACE, "Type yr to yr: " + typeYrToYr);
			if (!TextUtils.isEmpty(typeYrToYr))
			{
				List<Annuals> yrToYrRates = calculateYearToYearCompanyGrowthRates(company, type,
						yrToYrConsMap.get(type));
				calculatedAnnuals.put(yrToYrConsMap.get(type), yrToYrRates);
			}

			String typeToCalcToCurr = yrToCurrConsMap.get(type);
			if (!TextUtils.isEmpty(typeToCalcToCurr))
			{
				LUGGER.log(Level.TRACE, "Type to Current: " + typeToCalcToCurr);
				List<Annuals> yrToCurrRates = calculateCompanyGrowthRates(company, type, typeToCalcToCurr);
				calculatedAnnuals.put(yrToCurrConsMap.get(type), yrToCurrRates);
			}

		}
		for (String calcType : calculatedAnnuals.keySet())
		{
			company.getAnnuals().put(calcType, calculatedAnnuals.get(calcType));
		}
	}

	/**
	 * @param company
	 * @return
	 */
	private List<Annuals> calculateYearToYearCompanyGrowthRates(CompanyDO company, String typeToGet,
			String typeCalculated)
	{
		List<Annuals> annualList = company.getAnnuals().get(typeToGet);
		// get existing type to be calculated
		List<Annuals> typeCalculatedList = company.getAnnuals().get(typeCalculated);
		if (typeCalculatedList == null)
		{
			typeCalculatedList = new ArrayList<Annuals>();
		}
		if (annualList != null && !annualList.isEmpty())
		{
			annualList.sort(Annuals.yearComparator);
			for (int i = 0; i < annualList.size(); i++)
			{
				// prevent index out of range
				if (!(i + 1 >= annualList.size()) && annualList.get(i) != null)
				{
					Date date = Calendar.getInstance().getTime();
					Annuals starting = annualList.get(i);
					Annuals ending = annualList.get(i + 1); // should be i+1
					double growthRate = GrowthRates.getInstance().calculateGrowthRate(starting, ending);
					BigDecimal bdGrowthRate = ConversionUtility.getInstance().convertValueToBigDecimal(growthRate);
					Annuals annual = new Annuals(bdGrowthRate, ending.getDataYear(), date, typeCalculated);
					if (!typeCalculatedList.contains(annual))
					{
						typeCalculatedList.add(annual);
					}
				}
			}
		}
		if (!typeCalculatedList.isEmpty())
		{
			typeCalculatedList.sort(Annuals.yearComparator);
		}
		return typeCalculatedList;

	}

	/**
	 * @param company
	 * @return
	 */
	private List<Annuals> calculateCompanyGrowthRates(CompanyDO company, String typeToGet, String typeCalculated)
	{

		List<Annuals> annualList = company.getAnnuals().get(typeToGet);
		// get existing type to be calculated
		List<Annuals> bvpsGrowthRates = company.getAnnuals().get(typeCalculated);
		if (bvpsGrowthRates == null)
		{
			bvpsGrowthRates = new ArrayList<Annuals>();
		}
		if (annualList != null && !annualList.isEmpty())
		{
			annualList.sort(Annuals.yearComparator);
			// get current year numbers
			Annuals lastAnnual = annualList.get(annualList.size() - 1);
			for (int i = 0; i < annualList.size(); i++)
			{
				// prevent index out of range
				if (!(i + 1 >= annualList.size()) && annualList.get(i) != null)
				{
					Date date = Calendar.getInstance().getTime();
					Annuals starting = annualList.get(i);
					BigDecimal numYears = lastAnnual.getDataYear().subtract(starting.getDataYear());
					double growthRate = GrowthRates.getInstance().calculateGrowthRate(starting, lastAnnual);
					BigDecimal bdGrowthRate = ConversionUtility.getInstance().convertValueToBigDecimal(growthRate);
					Annuals annual = new Annuals(bdGrowthRate, numYears, date, typeCalculated);
					if (!bvpsGrowthRates.contains(annual))
					{
						bvpsGrowthRates.add(annual);
					}
				}
			}
		}
		if (!bvpsGrowthRates.isEmpty())
		{
			bvpsGrowthRates.sort(Annuals.yearComparator);
		}
		return bvpsGrowthRates;

	}

	/**
	 * @param type
	 * @param annualList
	 * @param resultAnnualList
	 * @param year
	 * @param value
	 * @return
	 */
	private List<Annuals> getResultsAnnualList(String type, List<Annuals> annualList, BigDecimal year, BigDecimal value)
	{
		List<Annuals> resultAnnualList = new ArrayList<Annuals>();
		if (annualList != null && !annualList.isEmpty())
		{
			for (Annuals annual : annualList)
			{
				if (!type.equalsIgnoreCase(annual.getType()) && !value.equals(annual.getValue()))
				{
					LUGGER.log(Level.TRACE, "Adding year: " + year + "TYPE: " + annual.getType());
					Annuals resultAnnuals = createAnnuals(value.toString(), annual.getDataYear().toString(), type);
					resultAnnualList.add(resultAnnuals);

				} else
				{
					resultAnnualList.add(annual);
				}
			}
		} else
		{
			Annuals resultAnnuals = createAnnuals(value.toString(), year.toString(), type);
			resultAnnualList.add(resultAnnuals);
		}
		return resultAnnualList;
	}

	/**
	 * @param results
	 * @param company
	 * @param type
	 * @return
	 */
	private Annuals createAnnuals(String value, String year, String type)
	{

		Date createDate = Calendar.getInstance().getTime();
		Annuals newAnnual = new Annuals(value, year, createDate, type);
		LUGGER.log(Level.TRACE, "New Annual: " + newAnnual);
		return newAnnual;

	}

	public void addCompanyFromDataPoints(Map<String, String> parms, Map<String, String> results)
	{
		// get the ticker for the company and retrieve from companies map
		String ticker = parms.get(IntrinioConstants.S_TICKER);
		LUGGER.log(Level.DEBUG, "TICKER ADDING COMPANY FROM DATAPOINT: " + ticker);
		String type = "";
		LUGGER.log(Level.TRACE, "creating new annual");
		CompanyDO company = new CompanyDO(ticker);

		/* 
		 * for each key in the result, the key is the date and the value
		 * is the value of the item requested
		 */
		List<Annuals> resultAnnualList = new ArrayList<Annuals>();
		LUGGER.log(Level.TRACE, "RESULTS KEYSET" + results.keySet());
		for (String key : results.keySet())
		{

			int year = Calendar.getInstance().get(Calendar.YEAR);
			BigDecimal yearR = BigDecimal.valueOf(year);
			String result = results.get(key);
			LUGGER.log(Level.TRACE, "For key: " + key + " the result is :" + result);
			BigDecimal value = new BigDecimal(0.0);
			if (!TextUtils.isEmpty(result) && !result.equals(InvestConstants.S_NULL))
			{
				value = BigDecimal.valueOf(Double.valueOf(result));
			}

			type = ConstantsMap.getInstance().getConstantsMap().get(key);
			LUGGER.log(Level.TRACE, "TYPE: " + type);
			Annuals resultAnnuals = createAnnuals(value.toString(), yearR.toString(), type);
			resultAnnualList.add(resultAnnuals);
			// add annuals to company
			company.getAnnuals().put(type, resultAnnualList);
		}
		// add company to map
		companiesMap.put(ticker, company);
	}

	public void updateFromDataPoints(Map<String, String> parms, Map<String, String> results)
	{ // get the ticker for the company and retrieve from companies map
		CompanyDO company = companiesMap.get(parms.get(IntrinioConstants.S_TICKER));
		// convert Intrinio constants to Common Constants - InvestingConstants
		String type = ConstantsMap.getInstance().getConstantsMap().get(parms.get(IntrinioConstants.S_ITEM));

		// get the annuals
		List<Annuals> annualList = company.getAnnuals().get(type);
		List<Annuals> resultAnnualList = new ArrayList<Annuals>();
		// for each key in the result, the key is the date and the value is the
		// value of the item requested
		for (String key : results.keySet())
		{
			BigDecimal year = ConversionUtility.getInstance().convertDateToBigDecimal(key);
			String result = results.get(key);
			BigDecimal value = new BigDecimal(0.0);
			if (!TextUtils.isEmpty(result) && !result.equals(InvestConstants.S_NULL))
			{
				value = BigDecimal.valueOf(Double.valueOf(result));
			}
			resultAnnualList = getResultsAnnualList(type, annualList, year, value);
		}
		company.getAnnuals().put(type, resultAnnualList);
	}
}
