package com.ecsdepot.investing.intrinio;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ecsdepot.investing.exceptions.ValueInvestingException;
import com.ecsdepot.investing.utilities.InvestingProperties;

/**
 * 
 * @author Justin
 *
 */
public class IntrinioCalls
{
	private static final String CLASS_NAME = IntrinioCalls.class.getName();
	private static Logger LUGGER = Logger.getLogger(CLASS_NAME);
	// name of the investing properties file.
	private static final String INVESTING_PROPERTIES = "investing.properties";
	// local instance of properties file
	private InvestingProperties investingProps;

	/**
	 * Constructor for the calls.
	 */
	public IntrinioCalls()
	{
		super();
		this.investingProps = new InvestingProperties(INVESTING_PROPERTIES); // $NON-NLS-1$
	}

	/**
	 * Builds the URL string for the stock, statement, and year and returns the
	 * correct URL to the caller.
	 * 
	 * @param identifier
	 * @param statement
	 * @param fiscalYear
	 * @return
	 */
	public String buildStandardizedFinancialsDataRequest(Map<String, String> parameters)
	{

		// https://api.intrinio.com/financials/standardized?identifier=YUM
		// &statement=income_statement&fiscal_period=Q2&fiscal_year=2015
		final StringBuilder urlBuilder = new StringBuilder(investingProps.getIntrinioBaseURL());
		urlBuilder.append(IntrinioConstants.S_FINANCIALS).append(IntrinioConstants.S_STANDARDIZED)
				.append(IntrinioConstants.S_QUESTION_MARK);
		int keys = 0;
		for (String key : parameters.keySet())
		{
			urlBuilder.append(key).append(IntrinioConstants.S_EQUALS).append(parameters.get(key));
			if (keys < (parameters.size() - 1))
			{
				urlBuilder.append(IntrinioConstants.S_AND_AMPERSAND);
				keys++;
			}
		}

		return urlBuilder.toString();
	}

	/**
	 * Builds a URL for requesting Historical data from Intrinio using the
	 * parameters provided. See
	 * <a href="http://docs.intrinio.com/#historical-data">
	 * http://docs.intrinio.com/#historical-data </a> for applicable parameters
	 * 
	 * @return
	 */
	public String buildHistoricalDataRequest(Map<String, String> parameters)
	{
		// https://api.intrinio.com/historical_data?ticker=AAPL&item=pricetoearnings

		final StringBuilder urlBuilder = new StringBuilder(investingProps.getIntrinioBaseURL());
		urlBuilder.append(IntrinioConstants.S_HISTORICAL_DATA).append(IntrinioConstants.S_QUESTION_MARK);
		int keys = 0;
		for (String key : parameters.keySet())
		{
			urlBuilder.append(key).append(IntrinioConstants.S_EQUALS).append(parameters.get(key));
			if (keys < (parameters.size() - 1))
			{
				urlBuilder.append(IntrinioConstants.S_AND_AMPERSAND);
				keys++;
			}
		}

		return urlBuilder.toString();
	}

	/**
	 * Builds a URL for requesting data from Intrinio using the parameters
	 * provided. See <a href="http://docs.intrinio.com/#historical-data">
	 * http://docs.intrinio.com/#historical-data </a> for applicable parameters
	 * 
	 * @return
	 */
	public String buildDataPointsRequest(Map<String, String> parameters)
	{
		// https://api.intrinio.com/data_point?identifier=AAPL&item=name

		final StringBuilder urlBuilder = new StringBuilder(investingProps.getIntrinioBaseURL());
		urlBuilder.append(IntrinioConstants.S_DATA_POINT).append(IntrinioConstants.S_QUESTION_MARK);
		int keys = 0;
		for (String key : parameters.keySet())
		{
			urlBuilder.append(key).append(IntrinioConstants.S_EQUALS).append(parameters.get(key));
			if (keys < (parameters.size() - 1))
			{
				urlBuilder.append(IntrinioConstants.S_AND_AMPERSAND);
				keys++;
			}
		}

		return urlBuilder.toString();
	}

	public String buildDataPointsRequest(Map<String, String> parms, List<String> itemsList)
	{
		StringBuilder items = new StringBuilder();
		int index = 0;
		for (String itemNeeded : itemsList)
		{
			items.append(itemNeeded);
			if (index < itemsList.size() - 1)
			{
				items.append(IntrinioConstants.COMMA);
				index++;
			}
		}
		parms.put(IntrinioConstants.S_ITEM, items.toString());
		return (buildDataPointsRequest(parms));
	}

	/**
	 * @param result
	 * @return
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonProcessingException
	 */
	public JsonNode getRootNode(final String result) throws IOException
	{
		JsonFactory jsonFactory = new JsonFactory();
		final JsonParser jsonParser = jsonFactory.createJsonParser(result);
		jsonParser.configure(Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
		final ObjectMapper mapper = new ObjectMapper(jsonFactory);
		return mapper.readTree(jsonParser);

	}

	/**
	 * Reusable call which takes the URL and calls Intrinio Rest Service.
	 * Returns a {@link StringBuilder} of the {@link HttpResponse} as JSON.
	 * 
	 * @param request
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String requestDataFromIntrinio(final String request) throws ClientProtocolException, IOException
	{
		final CredentialsProvider provider = new BasicCredentialsProvider();
		final UsernamePasswordCredentials userCreds = new UsernamePasswordCredentials(investingProps.getIntrinioUser(),
				investingProps.getIntrinioPass());
		provider.setCredentials(AuthScope.ANY, userCreds);
		final HttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
		final HttpGet httpGet = new HttpGet(request);
		final HttpResponse httpResponse = httpClient.execute(httpGet);
		final HttpEntity entity = httpResponse.getEntity();
		return EntityUtils.toString(entity);

	}

	/**
	 * Parses the JsonNode and maps the results to a {@link Map}. object.
	 * 
	 * @param root
	 * @return
	 * @throws ValueInvestingException
	 */
	public Map<String, String> getResultsMap(final JsonNode root, final String nodePath) throws ValueInvestingException
	{
		Map<String, String> valuesMap = new HashMap<String, String>();
		// path of root node should be data
		final JsonNode dataNode = root.path(TagConstants.S_DATA);
		if (!dataNode.isArray())
		{
			LUGGER.log(Level.DEBUG, "Data Node is not an Array, Adding single item"); //$NON-NLS-1$
			String item = root.path(TagConstants.S_ITEM).asText();
			String value = root.path(TagConstants.S_VALUE).asText();
			LUGGER.log(Level.TRACE, "ATTEMPT::: " + item + " ::: " + value);
			valuesMap.put(item, value);
		}
		// parse for values
		for (final JsonNode node : dataNode)
		{

			final JsonNode tagNode = node.path(nodePath);
			String value = node.path(TagConstants.S_VALUE).asText();
			if (value == null)
			{
				value = "0.0";
			}
			LUGGER.log(Level.TRACE,
					"GETTING RESULT MAP IN " + CLASS_NAME + " : " + value + " FOR TAGNODE: " + tagNode.asText());

			LUGGER.log(Level.TRACE, "Tag NODE IN IN " + CLASS_NAME + " : " + tagNode.asText() + " value: " + value);
			valuesMap.put(tagNode.asText(), value);

		}
		LUGGER.log(Level.TRACE, "VALUES MAP IN " + CLASS_NAME + " : " + valuesMap);
		return valuesMap;
	}
}
