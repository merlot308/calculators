/**
 * 
 */
package com.ecsdepot.investing.application;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author Justin
 *
 */
public class AnalyzedCompanies extends Companies
{
	private Map<String, CompanyDO> meetConditionsMap;
	private Map<String, CompanyDO> meetfiveYearMap;

	private static final AnalyzedCompanies INSTANCE = new AnalyzedCompanies();
	private static final String CLASS_NAME = AnalyzedCompanies.class.getName();
	private static final Logger LUGGER = Logger.getLogger(CLASS_NAME);

	protected AnalyzedCompanies()
	{
		meetConditionsMap = new HashMap<String, CompanyDO>();
		meetfiveYearMap = new HashMap<String, CompanyDO>();
	}

	public static AnalyzedCompanies getInstance()
	{
		return INSTANCE;
	}

	/**
	 * Checks growth rates, if they are at or above 10%, they are added to
	 * meetsConditionsMap if not they are added to doesNotMeetConditionsMap
	 * 
	 * @param comp
	 */
	public void analyzeGrowthRates(CompanyDO comp)
	{
		// get annuals
		Map<String, List<Annuals>> annuals = comp.getAnnuals();

		for (String annualKey : annuals.keySet())
		{
			List<Annuals> knownAnnuals = annuals.get(annualKey);
			if (knownAnnuals == null || knownAnnuals.isEmpty())
			{
				break;
			}
			List<Annuals> meetsConditionsAnnuals = findAnnualsMeetingCondition(knownAnnuals);
			if (meetsConditionsAnnuals.size() >= knownAnnuals.size())
			{
				meetConditionsMap.put(comp.getTicker(), comp);
			}
			findFiveYearMatch(comp, annualKey, meetsConditionsAnnuals);

		}

	}

	/**
	 * @param comp
	 * @param annualKey
	 * @param meetsConditionsAnnuals
	 */
	protected void findFiveYearMatch(CompanyDO comp, String annualKey, List<Annuals> meetsConditionsAnnuals)
	{
		if (ConstantsMap.getInstance().getBigFiveToCurrentGrowRateCons().containsValue(annualKey))
		{
			BigDecimal percentToBeat = new BigDecimal(".099");
			LUGGER.log(Level.TRACE, "Checking Five year Growth to Current for " + annualKey);
			for (Annuals meetsAnns : meetsConditionsAnnuals)
			{
				BigDecimal numYear = meetsAnns.getDataYear();
				LUGGER.log(Level.TRACE, "NUM YEAR: " + numYear + " VALUE:::: " + meetsAnns.getValue());
				if (numYear.intValue() == 5)
				{
					if (meetsAnns.getValue().compareTo(percentToBeat) > -1)
					{
						meetfiveYearMap.put(comp.getTicker(), comp);
					}
				}
			}
		}
	}

	/**
	 * @param annualList
	 * @param meetsConditionAnnuals
	 */
	protected List<Annuals> findAnnualsMeetingCondition(List<Annuals> annualList)
	{
		List<Annuals> meetsConditionAnnuals = new ArrayList<Annuals>();
		boolean meetsCondition = false;
		for (Annuals annual : annualList)
		{
			BigDecimal percentToBeat = new BigDecimal(".099");
			BigDecimal value = annual.getValue();
			// if value is greater than 10%
			int compare = value.compareTo(percentToBeat);
			if (compare < 1)
			{
				meetsCondition = false;
			} else
			{
				meetsCondition = true;
			}
			if (meetsCondition)
			{
				meetsConditionAnnuals.add(annual);
			}
		}
		return meetsConditionAnnuals;
	}

	/**
	 * @return the meetConditionsMap
	 */
	public Map<String, CompanyDO> getMeetConditionsMap()
	{
		return meetConditionsMap;
	}

	/**
	 * @param meetConditionsMap
	 *            the meetConditionsMap to set
	 */
	protected void setMeetConditionsMap(final Map<String, CompanyDO> meetConditionsMap)
	{
		this.meetConditionsMap = meetConditionsMap;
	}

	/**
	 * @return the meetfiveYearMap
	 */
	public Map<String, CompanyDO> getMeetFiveYearMap()
	{
		return meetfiveYearMap;
	}
}
