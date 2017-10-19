package com.ecsdepot.investing.application;

import java.math.BigDecimal;

public class InvestConstants
{

	public static final BigDecimal ZERO_VALUE = new BigDecimal(0);
	public static final String BVPS = "Book Value Per Share";
	public static final String EPS = "Earnings Per Share";
	public static final String EQUITY = "Book Value or Equity";
	public static final String SALES = "Sales";
	public static final String NET_INCOME = "Net Income";
	public static final String TOTAL_SHARES_OUTSTANDING = "Total Shares Outstanding";
	public static final String ROE = "Return On Equity";
	public static final String ROIC = "Return on Investment Capitol";
	public static final String NOPAT = "Net Operating Profit After Taxes";
	public static final String S_DATE_FORMAT = "yyyy-dd-mm";
	public static final String S_NULL = "null";
	public static final String TOTAL_DEBT = "Total Debt";

	// Growth Rates
	public static final String BVPS_GROWTH_RATE = "Book Value Per Share Growth Rate";
	public static final String EPS_GROWTH_RATE = "EPS Growth Rate";
	public static final String OPERATING_CASH = "OPERATING CASH FLOW";
	public static final String FCF_PS = "Free Cash Flow Per Share";
	public static final String SYMBOL = "symbol";
	public static final String EQUALS = "=";
	public static final String BVPS_GROWTH_RATE_TO_CURRENT = "BVPS Growth Rate to Current";
	public static final String ROIC_GROWTH_TO_CURRENT = "ROIC Growth to Current";
	public static final String ROIC_GROWTH_RATE = "ROIC Growth Rate";
	public static final String FCF_PS_GROWTH_RATE = "Free Cash Flow Per Share Growth Rate";
	public static final String FCF_PS_GR_TO_CURRENT = "Free Cash Flow PS Growth Rate to Current";
	public static final String ROE_GROWTH_RATE = "Return On Equity";
	public static final String EPS_GROWTH_RATE_TO_CURRENT = "EPS Growth Rate to Current";
	public static final String ROE_GROWTH_RATE_TO_CURRENT = "ROE Growth Rate to Current";

	// data source field names
	public static final String NAME = "name";
	public static final String LAST_PRICE = "lastPrice";
	public static final String CHANGE = "change";
	public static final String CHANGE_PERCENT = "changepercent";
	public static final String MSDATE = "msdate";
	public static final String MARKET_CAP = "marketcap";
	public static final String VOLUME = "volume";
	public static final String CHANGE_YTD = "changeytd";
	public static final String CHANGE_PERCENT_YTD = "changepercentytd";
	public static final String HIGH = "high";
	public static final String LOW = "low";
	public static final String OPEN = "open";

	// options constants
	public static final String BCS = "Bear Call Spread";
	public static final String ROC = "Rule One Call";
	public static final String BPS = "Bear Put Spread";
	public static final String ROP = "Rule One Put";
	public static final String IRON_CONDOR = "Iron Condor";
}
