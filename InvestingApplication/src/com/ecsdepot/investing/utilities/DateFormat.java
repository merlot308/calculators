/**
 * 
 */
package com.ecsdepot.investing.utilities;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.FastDateFormat;

/**
 * @author Justin
 *
 */
public final class DateFormat
{
	private Map<String, FastDateFormat> dateFormats = new HashMap<String, FastDateFormat>();
	private static DateFormat INSTANCE = new DateFormat();

	private DateFormat()
	{

	}

	public static DateFormat getInstance()
	{
		return INSTANCE;
	}

	public Date formatDate(String format, String dateString)
	{
		Date formattedDate = null;
		try
		{
			if (!dateFormats.containsKey(format))
			{
				dateFormats.put(format, FastDateFormat.getInstance("yyyy-mm-dd"));
			}
			formattedDate = dateFormats.get(format).parse(dateString);
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formattedDate;
	}
}
