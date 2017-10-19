package com.ecsdepot.investing.intrinio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class testingstuff
{
	private static final String CLASS_NAME = testingstuff.class.getName();
	private static Logger LUGGER = Logger.getLogger(CLASS_NAME);

	public static void main(String[] args) throws ParseException
	{

		// String requst =
		// "https://api.intrinio.com/financials/standardized?identifier=F&statement=balance_sheet&fiscal_period=FY&fiscal_year=2012";
		// LUGGER.log(Level.INFO,date);

		Date formattedDate = Calendar.getInstance().getTime();

		LUGGER.log(Level.INFO, DateTimeFormatter.ofPattern("YYYY-MM-DD").parse("2012-01-29"));
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
		formattedDate = formatter.parse("2012-01-29");

		LUGGER.log(Level.INFO, formattedDate);

	}

}
