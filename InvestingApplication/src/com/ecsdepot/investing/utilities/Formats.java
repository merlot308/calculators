package com.ecsdepot.investing.utilities;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Formats
{
	
	

	private NumberFormat decimalFormat = DecimalFormat.getNumberInstance();

	private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
	
	public static String percentFormat(BigDecimal num)
	{
		NumberFormat percentFormat = NumberFormat.getPercentInstance();
		percentFormat.setMinimumFractionDigits(2);
		return percentFormat.format(num.doubleValue());
	}
	public static String percentFormat(double num)
	{
		NumberFormat percentFormat = NumberFormat.getPercentInstance();
		percentFormat.setMinimumFractionDigits(2);
		return percentFormat.format(num);
	}
}
