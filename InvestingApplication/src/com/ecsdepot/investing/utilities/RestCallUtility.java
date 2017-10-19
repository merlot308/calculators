package com.ecsdepot.investing.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

/**
 * 
 * @author Justin
 *
 */
public final class RestCallUtility
{
	private RestCallUtility()
	{

	}

	/**
	 * @param quoteURIBuilder
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static String getURLResponse(final String quoteURI) throws MalformedURLException, IOException
	{
		final URL url = new URL(quoteURI);

		final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

		final StringBuilder temp2 = new StringBuilder();
		String temp = bufferedReader.readLine();
		while (null != temp)
		{
			temp2.append(temp);
			temp = bufferedReader.readLine();
		}
		return temp2.toString();
	}

	/**
	 * @param string
	 * @return
	 * @throws IOException
	 * @throws JsonParseException
	 */
	public static List<String> getFieldNamesList(final String string) throws IOException, JsonParseException
	{
		final JsonFactory jsonFactory = new JsonFactory();
		final JsonParser jsonParser = jsonFactory.createJsonParser(string);

		final List<String> fieldNames = new ArrayList<String>();
		while (!jsonParser.isClosed())
		{
			final JsonToken jsonToken = jsonParser.nextToken();
			if (JsonToken.FIELD_NAME.equals(jsonToken))
			{
				final String fieldName = jsonParser.getCurrentName();
				fieldNames.add(fieldName);
			}

		}
		return fieldNames;
	}
}
