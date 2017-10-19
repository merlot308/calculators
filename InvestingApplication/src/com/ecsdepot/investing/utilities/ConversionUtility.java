/**
 * 
 */
package com.ecsdepot.investing.utilities;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.util.TextUtils;

import com.ecsdepot.investing.application.InvestConstants;

/**
 * @author Justin
 *
 */
public class ConversionUtility
{
	private static ConversionUtility INSTANCE = new ConversionUtility();

	private ConversionUtility()
	{
		super();
	}

	public static ConversionUtility getInstance()
	{
		return INSTANCE;
	}

	public BigDecimal convertDateToBigDecimal(String date)
	{
		Date date1 = DateFormat.getInstance().formatDate(InvestConstants.S_DATE_FORMAT, date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		return BigDecimal.valueOf(Integer.valueOf(year));
	}

	public BigDecimal convertValueToBigDecimal(Double value)
	{
		BigDecimal bigDecimal = new BigDecimal(0.0);
		try
		{
			if (!Double.isNaN(value))
			{
				bigDecimal = BigDecimal.valueOf(value);
			}

		} catch (NumberFormatException e)
		{
			// TODO logging needed
			// log that there is a number format exception as fatalerror
		}
		return bigDecimal;
	}

	public Date convertBigDecimalToDate(BigDecimal value)
	{
		Calendar calender = Calendar.getInstance();
		calender.clear();
		calender.set(value.intValue(), Calendar.JANUARY, 1);
		return calender.getTime();

	}
}
