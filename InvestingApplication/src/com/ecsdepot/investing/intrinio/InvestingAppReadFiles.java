/**
 * 
 */
package com.ecsdepot.investing.intrinio;

import java.io.File;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ecsdepot.investing.application.AnalyzedCompanies;
import com.ecsdepot.investing.application.Annuals;
import com.ecsdepot.investing.application.Companies;
import com.ecsdepot.investing.application.CompanyDO;
import com.ecsdepot.investing.application.ConstantsMap;
import com.ecsdepot.investing.application.StoreData;
import com.ecsdepot.investing.exceptions.ValueInvestingException;
import com.ecsdepot.investing.utilities.Formats;
import com.ecsdepot.investing.utilities.InvestingProperties;

/**
 * @author Justin
 *
 */
public class InvestingAppReadFiles
{
	private final static String CLAZZ = InvestingAppReadFiles.class.getName();
	private static Logger LUGGER = Logger.getLogger(CLAZZ);

	/**
	 * @param args
	 * @throws ValueInvestingException
	 */
	public static void main(String[] args) throws ValueInvestingException
	{
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
		InvestingProperties properties = new InvestingProperties("investing.properties");
		// String file = "company2.avro";
		String file = properties.getDataFile();
		LUGGER.log(Level.TRACE, "File being Read " + file);
		File dataFile = new File(file);

		readAvroDataFile(dataFile);

	}

	/**
	 * @throws ValueInvestingException
	 */
	protected static void readAvroDataFile(File dataFile) throws ValueInvestingException
	{
		// Testing File -
		if (!dataFile.exists())
		{
			throw new ValueInvestingException("Data File does not exist! Get Data first!");
		}

		Companies companies = StoreData.getInstance().readData(dataFile);
		// get company map
		Map<String, CompanyDO> companyMap = companies.getCompaniesMap();
		AnalyzedCompanies analised = AnalyzedCompanies.getInstance();
		// for all companies
		for (String key : companyMap.keySet())
		{
			CompanyDO comp = companyMap.get(key);
			LUGGER.log(Level.DEBUG, "Got Company: " + key);
			companies.calculateBigFiveRates(companyMap.get(key));
			analised.analyzeGrowthRates(comp);
			for (String key2 : comp.getAnnuals().keySet())
			{
				LUGGER.log(Level.DEBUG, "Key when Reading: " + key2 + " Values: " + comp.getAnnuals().get(key2));
				if (ConstantsMap.getInstance().getBigFiveToCurrentGrowRateCons().containsValue(key2))
				{
					for (Annuals annual : comp.getAnnuals().get(key2))
					{
						LUGGER.log(Level.INFO,
								"For Company: " + comp.getTicker() + " the growth rate of " + key2 + " is "
										+ Formats.percentFormat(annual.getValue()) + " for " + annual.getDataYear()
										+ " years");
					}
				}
			}
		}
		for (String anKey : analised.getMeetConditionsMap().keySet())
		{
			LUGGER.log(Level.INFO, "Company meets:" + analised.getMeetConditionsMap().get(anKey).getTicker());
		}
		for (String fiveKey : analised.getMeetFiveYearMap().keySet())
		{
			LUGGER.log(Level.INFO, "Five Year Meets: " + analised.getMeetFiveYearMap().get(fiveKey).getTicker());
		}
	}

}
