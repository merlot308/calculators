/**
 * 
 */
package com.ecsdepot.investing.intrinio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;

import com.ecsdepot.investing.application.Companies;
import com.ecsdepot.investing.application.CompanyDO;
import com.ecsdepot.investing.application.StoreData;
import com.ecsdepot.investing.exceptions.ValueInvestingException;
import com.ecsdepot.investing.utilities.InvestingProperties;

/**
 * @author Justin
 *
 */
public class IntrinioClientGetCurrentData
{
	private final static String CLASS_NAME = IntrinioClientGetCurrentData.class.getName();
	private static Logger LUGGER = Logger.getLogger(CLASS_NAME);

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			IntrinioCalls ics = new IntrinioCalls();
			// https://api.intrinio.com/data_point?identifier=AAPL&item=name
			// can do up to 150 items or 150 tickers per item or combination

			Map<String, String> parms = new HashMap<String, String>();

			// use pre-defined data points to get companies
			Companies companies = getDataUsingDataPoints(ics, parms);
			for (String comp : companies.getCompaniesMap().keySet())
			{
				CompanyDO company = companies.getCompaniesMap().get(comp);
				companies.calculateBigFiveRates(company);
			}
			InvestingProperties props = new InvestingProperties("investing.properties");
			StoreData.getInstance().writeData(companies, props.getDataFile());

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Gather company data and add a {@link CompanyDO} to {@link Companies}
	 * based on data points
	 * 
	 * @param ics
	 * @param parms
	 * @return
	 * @throws ValueInvestingException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonProcessingException
	 */
	private static Companies getDataUsingDataPoints(IntrinioCalls ics, Map<String, String> parms)
			throws ValueInvestingException
	{
		
		List<String> itemsList = new ArrayList<String>();
		itemsList.add(IntrinioConstants.BOOK_VALUE_PER_SHARE);
		itemsList.add(IntrinioConstants.EARNING_PER_SHARE_GROWTH);
		itemsList.add(IntrinioConstants.BASIC_EPS);
		itemsList.add(IntrinioConstants.RETURN_ON_EQUITY);
		itemsList.add(IntrinioConstants.RETURN_ON_INVESTED_CAPITOL);
		itemsList.add(IntrinioConstants.SALES);
		// for ROIC you need NOPAT, Equity, and Debt
		itemsList.add(IntrinioConstants.NET_OPERATING_PROFIT_AFTER_TAX);
		itemsList.add(IntrinioConstants.S_DEBT);
		// itemsList.add(IntrinioConstants.EQUITY);
		
		
		List<String> tickerList = getAllTickers();
		Companies companies = Companies.getInstance();
		try
		{
			int requestControl = 0;
			for (String ticker : tickerList)
			{
				parms.put(IntrinioConstants.S_IDENTIFIER, ticker);

				String request = ics.buildDataPointsRequest(parms, itemsList);

				// make requests from parms and add to Companies
				if (requestControl < 3)
				{
					System.out.println("REQUEST:::: " + request);
					//String result = ics.requestDataFromIntrinio(request);
					 String result = financialsReturnExample();
					JsonNode root = ics.getRootNode(result);
					Map<String, String> resultMap = ics.getResultsMap(root, TagConstants.S_ITEM);
					companies.addCompanyFromDataPoints(parms, resultMap);
				}
				requestControl++;
			}
		} catch (IOException ioe)
		{
			throw new ValueInvestingException("Unable to add companies using data points", ioe);
		}
		return companies;
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
		System.out.println("All Tickers list size: " + tickerList.size());
		return tickerList;
	}

	private static String financialsReturnExample()
	{
		/*
		//historical requset with start/end dates
		//return "{\"data\":[{\"date\":\"2015-12-31\",\"value\":7.2164},{\"date\":\"2014-12-31\",\"value\":6.2367},{\"date\":\"2013-12-31\",\"value\":6.6257},{\"date\":\"2012-12-31\",\"value\":4.1801}],\"identifier\":\"F\",\"item\":\"bookvaluepershare\",\"result_count\":4,\"page_size\":100,\"current_page\":1,\"total_pages\":1,\"api_call_credits\":1}";
		 */

		/*
		// historical request no dates
		//return "{\"data\":[{\"date\":\"2016-12-31\",\"value\":7.3421},{\"date\":\"2015-12-31\",\"value\":7.2164},{\"date\":\"2014-12-31\",\"value\":6.2367},{\"date\":\"2013-12-31\",\"value\":6.6257},{\"date\":\"2012-12-31\",\"value\":4.1801},{\"date\":\"2011-12-31\",\"value\":3.6728},{\"date\":\"2010-12-31\",\"value\":-0.1703},{\"date\":\"2009-12-31\",\"value\":-2.4752},{\"date\":\"2008-12-31\",\"value\":-6.9199},{\"date\":\"2007-12-31\",\"value\":null}],\"identifier\":\"F\",\"item\":\"bookvaluepershare\",\"result_count\":10,\"page_size\":100,\"current_page\":1,\"total_pages\":1,\"api_call_credits\":1}";
		*/

		// Data Point Request
		return "{\"data\":[{\"identifier\":\"MNST\",\"item\":\"bookvaluepershare\",\"value\":5.5512},{\"identifier\":\"MNST\",\"item\":\"epsgrowth\",\"value\":0.0},{\"identifier\":\"MNST\",\"item\":\"basiceps\",\"value\":0},{\"identifier\":\"MNST\",\"item\":\"roe\",\"value\":0.175126},{\"identifier\":\"MNST\",\"item\":\"roic\",\"value\":0.311268},{\"identifier\":\"MNST\",\"item\":\"totalrevenue\",\"value\":3049393000.0},{\"identifier\":\"MNST\",\"item\":\"nopat\",\"value\":716416466.4},{\"identifier\":\"MNST\",\"item\":\"debt\",\"value\":0.0}],\"result_count\":8,\"api_call_credits\":8}";

		/*
		//balance sheet request
		//return "{\"data\":[{\"tag\":\"cashandequivalents\",\"value\":15659000000.0},{\"tag\":\"shortterminvestments\",\"value\":20284000000.0},{\"tag\":\"notereceivable\",\"value\":86294000000.0},{\"tag\":\"accountsreceivable\",\"value\":10878000000.0},{\"tag\":\"netinventory\",\"value\":7362000000.0},{\"tag\":\"totalcurrentassets\",\"value\":140477000000.0},{\"tag\":\"netppe\",\"value\":24942000000.0},{\"tag\":\"longterminvestments\",\"value\":3246000000.0},{\"tag\":\"othernoncurrentassets\",\"value\":5556000000.0},{\"tag\":\"totalnoncurrentassets\",\"value\":23987000000.0},{\"tag\":\"totalassets\",\"value\":189406000000.0},{\"tag\":\"accountspayable\",\"value\":19308000000.0},{\"tag\":\"totalcurrentliabilities\",\"value\":67567000000.0},{\"tag\":\"longtermdebt\",\"value\":105058000000.0},{\"tag\":\"totalnoncurrentliabilities\",\"value\":105528000000.0},{\"tag\":\"totalliabilities\",\"value\":173095000000.0},{\"tag\":\"redeemablenoncontrollinginterest\",\"value\":322000000.0},{\"tag\":\"commonequity\",\"value\":21016000000.0},{\"tag\":\"retainedearnings\",\"value\":18077000000.0},{\"tag\":\"treasurystock\",\"value\":-292000000.0},{\"tag\":\"aoci\",\"value\":-22854000000.0},{\"tag\":\"totalcommonequity\",\"value\":15947000000.0},{\"tag\":\"totalequity\",\"value\":15947000000.0},{\"tag\":\"noncontrollinginterests\",\"value\":42000000.0},{\"tag\":\"totalequityandnoncontrollinginterests\",\"value\":15989000000.0},{\"tag\":\"totalliabilitiesandequity\",\"value\":189406000000.0},{\"tag\":\"noncurrentdeferredtaxassets\",\"value\":15185000000.0},{\"tag\":\"currentdeferredrevenue\",\"value\":48259000000.0},{\"tag\":\"noncurrentdeferredtaxliabilities\",\"value\":470000000.0}],\"result_count\":29,\"page_size\":100,\"current_page\":1,\"total_pages\":1,\"api_call_credits\":1}";
		*/
	}
}
