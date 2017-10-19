package com.ecsdepot.investing.options;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.MapType;

import com.ecsdepot.investing.application.InvestConstants;
import com.ecsdepot.investing.intrinio.IntrinioCalls;
import com.ecsdepot.investing.intrinio.TagConstants;
import com.ecsdepot.investment.calculators.InvestingCalculators;

public class OptionsCalculations
{

	public static void main(String[] args)
	{
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
		try
		{
			IntrinioCalls ics = new IntrinioCalls();

			String result = getResults();

			Map<String, String> resultsMap;
			ObjectMapper mapper;

			MapType type;

			mapper = new ObjectMapper();
			resultsMap = mapper.readValue(result, HashMap.class);

			for (String key : resultsMap.keySet())
			{
				System.out.println("KEY: " + key.toString());
				System.out.println("RESULT: " + String.valueOf(resultsMap.get(key)));
			}
			List<OptionData> optionsList = new ArrayList<OptionData>();
			if (resultsMap.containsKey(TagConstants.S_DATA))
			{

				mapper = new ObjectMapper();
				final JsonNode rootNode = mapper.readValue(result, JsonNode.class);
				JsonNode dataNode = rootNode.get(TagConstants.S_DATA);
				optionsList = mapper.readValue(dataNode,
						mapper.getTypeFactory().constructCollectionType(List.class, OptionData.class));

			}
			OptionDO optionDo = new OptionDO();
			InvestingCalculators investCalc = new InvestingCalculators();
			investCalc.getDaysToExpiration(optionDo.getExpiration());
			optionDo.setResistence("1080");
			BigDecimal minMax = calculateMinMaxStrike(InvestConstants.BCS, optionDo.getResistence());
			for (OptionData optionData : optionsList)
			{
				System.out.println("Date from option: " + optionData.getDate());
				optionDo.setDelta(optionData.getDate());
				optionDo.setExpiration(optionData.getExpiration());				
				optionDo.setStrikeMinMax(minMax.toPlainString());
				//if(optionData.getDelta())
			}
			

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static BigDecimal calculateMinMaxStrike(String tradeType, String value)
	{
		BigDecimal minMax = InvestConstants.ZERO_VALUE;
		if (tradeType.equals(InvestConstants.BCS) || tradeType.equals(InvestConstants.ROC))
		{
			minMax = new BigDecimal(value).add(InvestingCalculators.getPercentOfValue(value, ".03"));
		}
		if (tradeType.equals(InvestConstants.BPS) || tradeType.equals(InvestConstants.ROP))
		{
			minMax = new BigDecimal(value).subtract(InvestingCalculators.getPercentOfValue(value, ".03"));
		}
		return minMax;
	}

	private static String getResults()
	{
		return "{\"result_count\": 208,\"api_call_credits\": 1,\"data\": [{\"date\": \"2017-02-14\",\"expiration\": \"2017-02-17\",\"strike\": 136,\"type\": \"call\",\"close\": 0.43,\"close_bid\": 0.42,\"close_ask\": 0.44,\"volume\": 19349,\"volume_bid\": 7075,\"volume_ask\": 6815,\"trades\": 915,\"open_interest\": 8538,\"open_interest_change\": 3074,\"next_day_open_interest\": 12629,\"implied_volatility\": 0.169001,\"implied_volatility_change\": -0.009107,\"delta\": 0.321807}]}";
	}

}
