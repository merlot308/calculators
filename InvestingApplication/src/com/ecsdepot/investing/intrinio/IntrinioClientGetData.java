/**
 * 
 */
package com.ecsdepot.investing.intrinio;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.codehaus.jackson.JsonNode;

import com.ecsdepot.investing.application.Annuals;
import com.ecsdepot.investing.application.Companies;
import com.ecsdepot.investing.application.StoreData;
import com.ecsdepot.investing.exceptions.ValueInvestingException;
import com.ecsdepot.investing.utilities.InvestingProperties;

/**
 * @author Justin
 *
 */
public class IntrinioClientGetData
{
	private final static String CLASS_NAME = IntrinioClientGetData.class.getName();
	private static Logger LUGGER = Logger.getLogger(CLASS_NAME);

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);

		List<String> requestList = new ArrayList<String>();
		List<String> resultList = new ArrayList<String>();
		try
		{
			IntrinioCalls ics = new IntrinioCalls();

			Map<String, String> historicalParms = new HashMap<String, String>();
			historicalParms.put(IntrinioConstants.S_FREQUENCY, IntrinioConstants.YRLY_FREQ);

			List<String> tickerList = getAllTickers();

			List<String> itemsList = new ArrayList<String>();
			// itemsList.add(IntrinioConstants.BOOK_VALUE_PER_SHARE);
			// itemsList.add(IntrinioConstants.EARNING_PER_SHARE_GROWTH);
			// itemsList.add(IntrinioConstants.BASIC_EPS);
			// itemsList.add(IntrinioConstants.RETURN_ON_EQUITY);
			itemsList.add(IntrinioConstants.RETURN_ON_INVESTED_CAPITOL);
			// itemsList.add(IntrinioConstants.SALES);
			// for ROIC you need NOPAT, Equity, and Debt
			// itemsList.add(IntrinioConstants.NET_OPERATING_PROFIT_AFTER_TAX);
			// itemsList.add(IntrinioConstants.S_DEBT);

			Companies companies = Companies.getInstance();
			int requestControl = 0;
			for (String ticker : tickerList)
			{
				if (requestControl < 2)
				{
					historicalParms.put(IntrinioConstants.S_TICKER, ticker);

					// do datapoint req
					String dataPointsRequest = ics.buildDataPointsRequest(historicalParms, itemsList);
					String dataPointResults = ics.requestDataFromIntrinio(dataPointsRequest);
					// String dataPointResults = dataPointsReturnExample();
					JsonNode rootDataPointsNode = ics.getRootNode(dataPointResults);
					Map<String, String> resultsMap = ics.getResultsMap(rootDataPointsNode, TagConstants.S_ITEM);
					LUGGER.log(Level.DEBUG, "RESULTS MAP SIZE: IN MAIN: " + resultsMap.size());
					companies.addCompanyFromDataPoints(historicalParms, resultsMap);
					// for list of items, get item and build req
					LUGGER.log(Level.TRACE, "DATA POINT REQUEST:: " + dataPointResults);
					for (String item : itemsList)
					{
						historicalParms.put(IntrinioConstants.S_ITEM, item);
						String request = ics.buildHistoricalDataRequest(historicalParms);
						requestList.add(request);
						LUGGER.log(Level.TRACE, "HISTORICAL REQUEST:: " + request);
						// make requests from parms and add to Companies
						String result = ics.requestDataFromIntrinio(request);
						// String result = financialsReturnExample();
						resultList.add(result);
						JsonNode root = ics.getRootNode(result);
						Map<String, String> resultMap = ics.getResultsMap(root, TagConstants.S_DATE);
						companies.addCompanyFromHistoricals(historicalParms, resultMap);

					}
					requestControl++;
					companies.calculateBigFiveRates(companies.getCompaniesMap().get(ticker));
				}
			}

			// do growth rate calculations

			printAnnualsFromCompanies(companies);

			InvestingProperties props = new InvestingProperties("investing.properties");

			//StoreData.getInstance().writeData(companies, props.getDataFile());
			//StoreData.getInstance().simpleFileWrite(companies);

		} catch (Exception e)
		{
			LUGGER.log(Level.ERROR, "Unable to process historical Data completely", e);
			StoreData.getInstance().writeResults(resultList);

		}
	}

	/**
	 * @param companies
	 */
	protected static void printAnnualsFromCompanies(Companies companies)
	{
		LUGGER.log(Level.TRACE, "Printing annuals: ");
		for (String key : companies.getCompaniesMap().keySet())
		{
			for (String key2 : companies.getCompaniesMap().get(key).getAnnuals().keySet())
			{
				List<Annuals> annuals = companies.getCompaniesMap().get(key).getAnnuals().get(key2);
				for (Annuals ann : annuals)
				{
					LUGGER.log(Level.DEBUG, "COMPANY: " + companies.getCompaniesMap().get(key).getTicker());
					LUGGER.log(Level.DEBUG, "ANNUAL TYPE: " + ann.getType() + " ANNUAL YEAR: " + ann.getDataYear()
							+ " ANNUAL VALUE: " + ann.getValue());
				}

			}
		}
	}

	/**
	 * @throws ValueInvestingException
	 * 
	 */
	private static List<String> getAllTickers() throws ValueInvestingException
	{
		final File allCompanies;
		List<String> tickerList = new ArrayList<String>();
		try
		{
			InvestingProperties props = new InvestingProperties("investing.properties");
			allCompanies = new File(props.getAllCompaniesFile());
			LineIterator line = FileUtils.lineIterator(allCompanies);
			while (line != null && line.hasNext())
			{
				String readLine = line.next();
				List<String> csvList = Arrays.asList(readLine.split(","));
				tickerList.addAll(csvList);
			}
		} catch (Exception e)
		{
			throw new ValueInvestingException("Not able to build All Ticker List", e);
		}
		LUGGER.log(Level.TRACE, "All Tickers list size: " + tickerList.size());
		return tickerList;
	}

	private static String financialsReturnExample()
	{
		/*
		//historical requset with start/end dates
		//return "{\"data\":[{\"date\":\"2015-12-31\",\"value\":7.2164},{\"date\":\"2014-12-31\",\"value\":6.2367},{\"date\":\"2013-12-31\",\"value\":6.6257},{\"date\":\"2012-12-31\",\"value\":4.1801}],\"identifier\":\"F\",\"item\":\"bookvaluepershare\",\"result_count\":4,\"page_size\":100,\"current_page\":1,\"total_pages\":1,\"api_call_credits\":1}";
		 */

		// historical request no dates
		return "{\"data\":[{\"date\":\"2016-12-31\",\"value\":7.3421},{\"date\":\"2015-12-31\",\"value\":7.2164},{\"date\":\"2014-12-31\",\"value\":6.2367},{\"date\":\"2013-12-31\",\"value\":6.6257},{\"date\":\"2012-12-31\",\"value\":4.1801},{\"date\":\"2011-12-31\",\"value\":3.6728},{\"date\":\"2010-12-31\",\"value\":-0.1703},{\"date\":\"2009-12-31\",\"value\":-2.4752},{\"date\":\"2008-12-31\",\"value\":-6.9199},{\"date\":\"2007-12-31\",\"value\":null}],\"identifier\":\"F\",\"item\":\"bookvaluepershare\",\"result_count\":10,\"page_size\":100,\"current_page\":1,\"total_pages\":1,\"api_call_credits\":1}";

		// Data Point Request
		// return
		// "{\"data\":[{\"identifier\":\"MNST\",\"item\":\"bookvaluepershare\",\"value\":5.5512},{\"identifier\":\"MNST\",\"item\":\"epsgrowth\",\"value\":0.0},{\"identifier\":\"MNST\",\"item\":\"basiceps\",\"value\":0},{\"identifier\":\"MNST\",\"item\":\"roe\",\"value\":0.175126},{\"identifier\":\"MNST\",\"item\":\"roic\",\"value\":0.311268},{\"identifier\":\"MNST\",\"item\":\"totalrevenue\",\"value\":3049393000.0},{\"identifier\":\"MNST\",\"item\":\"nopat\",\"value\":716416466.4},{\"identifier\":\"MNST\",\"item\":\"debt\",\"value\":0.0}],\"result_count\":8,\"api_call_credits\":8}";
		/*
		//balance sheet request
		//return "{\"data\":[{\"tag\":\"cashandequivalents\",\"value\":15659000000.0},{\"tag\":\"shortterminvestments\",\"value\":20284000000.0},{\"tag\":\"notereceivable\",\"value\":86294000000.0},{\"tag\":\"accountsreceivable\",\"value\":10878000000.0},{\"tag\":\"netinventory\",\"value\":7362000000.0},{\"tag\":\"totalcurrentassets\",\"value\":140477000000.0},{\"tag\":\"netppe\",\"value\":24942000000.0},{\"tag\":\"longterminvestments\",\"value\":3246000000.0},{\"tag\":\"othernoncurrentassets\",\"value\":5556000000.0},{\"tag\":\"totalnoncurrentassets\",\"value\":23987000000.0},{\"tag\":\"totalassets\",\"value\":189406000000.0},{\"tag\":\"accountspayable\",\"value\":19308000000.0},{\"tag\":\"totalcurrentliabilities\",\"value\":67567000000.0},{\"tag\":\"longtermdebt\",\"value\":105058000000.0},{\"tag\":\"totalnoncurrentliabilities\",\"value\":105528000000.0},{\"tag\":\"totalliabilities\",\"value\":173095000000.0},{\"tag\":\"redeemablenoncontrollinginterest\",\"value\":322000000.0},{\"tag\":\"commonequity\",\"value\":21016000000.0},{\"tag\":\"retainedearnings\",\"value\":18077000000.0},{\"tag\":\"treasurystock\",\"value\":-292000000.0},{\"tag\":\"aoci\",\"value\":-22854000000.0},{\"tag\":\"totalcommonequity\",\"value\":15947000000.0},{\"tag\":\"totalequity\",\"value\":15947000000.0},{\"tag\":\"noncontrollinginterests\",\"value\":42000000.0},{\"tag\":\"totalequityandnoncontrollinginterests\",\"value\":15989000000.0},{\"tag\":\"totalliabilitiesandequity\",\"value\":189406000000.0},{\"tag\":\"noncurrentdeferredtaxassets\",\"value\":15185000000.0},{\"tag\":\"currentdeferredrevenue\",\"value\":48259000000.0},{\"tag\":\"noncurrentdeferredtaxliabilities\",\"value\":470000000.0}],\"result_count\":29,\"page_size\":100,\"current_page\":1,\"total_pages\":1,\"api_call_credits\":1}";
		*/
	}

	private static String dataPointsReturnExample()
	{
		/*
		//historical requset with start/end dates
		//return "{\"data\":[{\"date\":\"2015-12-31\",\"value\":7.2164},{\"date\":\"2014-12-31\",\"value\":6.2367},{\"date\":\"2013-12-31\",\"value\":6.6257},{\"date\":\"2012-12-31\",\"value\":4.1801}],\"identifier\":\"F\",\"item\":\"bookvaluepershare\",\"result_count\":4,\"page_size\":100,\"current_page\":1,\"total_pages\":1,\"api_call_credits\":1}";
		 */
		return "{\"identifier\":\"A\",\"item\":\"roic\",\"value\":0.148138}";
		// historical request no dates
		// return
		// "{\"data\":[{\"identifier\":\"AAC\",\"item\":\"bookvaluepershare\",\"value\":7.2676},{\"identifier\":\"AAC\",\"item\":\"epsgrowth\",\"value\":-1.0625},{\"identifier\":\"AAC\",\"item\":\"basiceps\",\"value\":-0.03},{\"identifier\":\"AAC\",\"item\":\"roe\",\"value\":-0.03942},{\"identifier\":\"AAC\",\"item\":\"roic\",\"value\":-0.001744},{\"identifier\":\"AAC\",\"item\":\"totalrevenue\",\"value\":279770000.0},{\"identifier\":\"AAC\",\"item\":\"nopat\",\"value\":-524533.3},{\"identifier\":\"AAC\",\"item\":\"debt\",\"value\":189106000.0}],\"result_count\":8,\"api_call_credits\":8}";
		// Data Point Request
		// return
		// "{\"data\":[{\"identifier\":\"MNST\",\"item\":\"bookvaluepershare\",\"value\":5.5512},{\"identifier\":\"MNST\",\"item\":\"epsgrowth\",\"value\":0.0},{\"identifier\":\"MNST\",\"item\":\"basiceps\",\"value\":0},{\"identifier\":\"MNST\",\"item\":\"roe\",\"value\":0.175126},{\"identifier\":\"MNST\",\"item\":\"roic\",\"value\":0.311268},{\"identifier\":\"MNST\",\"item\":\"totalrevenue\",\"value\":3049393000.0},{\"identifier\":\"MNST\",\"item\":\"nopat\",\"value\":716416466.4},{\"identifier\":\"MNST\",\"item\":\"debt\",\"value\":0.0}],\"result_count\":8,\"api_call_credits\":8}";
		/*
		//balance sheet request
		//return "{\"data\":[{\"tag\":\"cashandequivalents\",\"value\":15659000000.0},{\"tag\":\"shortterminvestments\",\"value\":20284000000.0},{\"tag\":\"notereceivable\",\"value\":86294000000.0},{\"tag\":\"accountsreceivable\",\"value\":10878000000.0},{\"tag\":\"netinventory\",\"value\":7362000000.0},{\"tag\":\"totalcurrentassets\",\"value\":140477000000.0},{\"tag\":\"netppe\",\"value\":24942000000.0},{\"tag\":\"longterminvestments\",\"value\":3246000000.0},{\"tag\":\"othernoncurrentassets\",\"value\":5556000000.0},{\"tag\":\"totalnoncurrentassets\",\"value\":23987000000.0},{\"tag\":\"totalassets\",\"value\":189406000000.0},{\"tag\":\"accountspayable\",\"value\":19308000000.0},{\"tag\":\"totalcurrentliabilities\",\"value\":67567000000.0},{\"tag\":\"longtermdebt\",\"value\":105058000000.0},{\"tag\":\"totalnoncurrentliabilities\",\"value\":105528000000.0},{\"tag\":\"totalliabilities\",\"value\":173095000000.0},{\"tag\":\"redeemablenoncontrollinginterest\",\"value\":322000000.0},{\"tag\":\"commonequity\",\"value\":21016000000.0},{\"tag\":\"retainedearnings\",\"value\":18077000000.0},{\"tag\":\"treasurystock\",\"value\":-292000000.0},{\"tag\":\"aoci\",\"value\":-22854000000.0},{\"tag\":\"totalcommonequity\",\"value\":15947000000.0},{\"tag\":\"totalequity\",\"value\":15947000000.0},{\"tag\":\"noncontrollinginterests\",\"value\":42000000.0},{\"tag\":\"totalequityandnoncontrollinginterests\",\"value\":15989000000.0},{\"tag\":\"totalliabilitiesandequity\",\"value\":189406000000.0},{\"tag\":\"noncurrentdeferredtaxassets\",\"value\":15185000000.0},{\"tag\":\"currentdeferredrevenue\",\"value\":48259000000.0},{\"tag\":\"noncurrentdeferredtaxliabilities\",\"value\":470000000.0}],\"result_count\":29,\"page_size\":100,\"current_page\":1,\"total_pages\":1,\"api_call_credits\":1}";
		*/
	}

}
