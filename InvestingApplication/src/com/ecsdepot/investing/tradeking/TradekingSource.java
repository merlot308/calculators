package com.ecsdepot.investing.tradeking;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ecsdepot.investing.application.CompanyDO;
import com.ecsdepot.investing.application.InvestConstants;
import com.ecsdepot.investing.utilities.RestCallUtility;

public class TradekingSource
{
	public void authorizeWithTradeKing()
	{
		
	}

	/**
	 * @param jsonString
	 * @param fieldNames
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws JsonParseException
	 */
	private static CompanyDO createCompanyInfoDO(String jsonString)
			throws ParseException, JsonParseException, IOException
	{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(jsonString);
		JSONObject jobj = (JSONObject) obj;
		CompanyDO company = new CompanyDO();
		List<String> fieldNames = RestCallUtility.getFieldNamesList(jsonString);
		for (String s : fieldNames)
		{
			if (InvestConstants.NAME.equalsIgnoreCase(s))
			{
				// System.out.println(jobj.get(s).toString());
				company.setCompanyName(jobj.get(s).toString());
			}
			if (InvestConstants.SYMBOL.equalsIgnoreCase(s))
			{
				company.setTicker(jobj.get(s).toString());
				// System.out.println(company.getTicker());
			}
			if (InvestConstants.LAST_PRICE.equalsIgnoreCase(s))
			{
				// create way to set current price
			}
			if (InvestConstants.CHANGE.equalsIgnoreCase(s))
			{
				// create way to set the change
			}
			if (InvestConstants.CHANGE_PERCENT.equalsIgnoreCase(s))
			{
				// create way to set
			}
			if (InvestConstants.MSDATE.equalsIgnoreCase(s))
			{
				// set
			}
			if (InvestConstants.MARKET_CAP.equalsIgnoreCase(s))
			{
				// set
			}
			if (InvestConstants.VOLUME.equalsIgnoreCase(s))
			{
				// set
			}
			if (InvestConstants.CHANGE_YTD.equalsIgnoreCase(s))
			{
				// set
			}
			if (InvestConstants.CHANGE_PERCENT_YTD.equalsIgnoreCase(s))
			{
				// set
			}
			if (InvestConstants.HIGH.equalsIgnoreCase(s))
			{
				// set
			}
			if (InvestConstants.LOW.equalsIgnoreCase(s))
			{
				// set
			}
			if (InvestConstants.OPEN.equalsIgnoreCase(s))
			{
				// set
			}
		}
		return company;
	}
}
