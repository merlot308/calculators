package com.ecsdepot.investing.intrinio;

public class IntrinioConstants
{
	public static final String S_HISTORICAL_DATA = "historical_data/";
	public static final String S_IDENTIFIER = "identifier";
	public static final String S_ITEM = "item";
	public static final String S_AND_AMPERSAND = "&";
	public static final String S_EQUALS = "=";
	public static final String S_DATA_POINT = "data_point";
	public static final String S_CLOSE_PRICE = "close_price";
	public static final String S_PAGE_SIZE = "page_size";
	public static final String S_QUESTION_MARK = "?";
	public static final String S_FINANCIALS = "financials/";
	public static final String S_TICKER = "ticker";

	// https://api.intrinio.com/historical_data?identifier={symbol}&item={tag}

	/*
	 * identifier - the ticker or identifier for the data point item - the
	 * requested standardized tag or series ID date - the date associated with
	 * the value of the data tag value - the value of the Intrinio tag of the
	 * financial data point
	 */

	public static final String S_DATE = "date";
	public static final String S_VALUE = "value";
	public static final String S_FREQUENCY = "frequency";
	public static final String DAILY_FREQ = "daily";
	public static final String WKLY_FREQ = "weekly";
	public static final String MTHLY_FREQ = "monthly";
	public static final String QTRLY_FREQ = "quarterly";
	public static final String YRLY_FREQ = "yearly";

	// Fundamentals
	// https://api.intrinio.com/fundamentals/standardized?identifier={symbol}&statement={statement}
	public static final String S_FUNDAMENTALS = "fundamentals";
	public static final String S_STANDARDIZED = "standardized";
	public static final String S_STATEMENT = "statement";

	// income_statement | balance_sheet | cash_flow_statement | calculations
	public static final String S_INCOME_STATEMENT = "income_statement";
	public static final String S_BALANCE_SHEET = "balance_sheet";
	public static final String S_CASH_FLOW_STATEMENT = "cash_flow_statement";
	public static final String S_CALCULATIONS = "calculations";

	// statement types
	// FY | QTR | TTM | YTD
	public static final String S_FY = "FY";
	public static final String S_QTR = "QTR";
	public static final String S_TTM = "TTM";
	public static final String S_YTD = "YTD";

	// fundamental returns
	/*
	 * fiscal_year - the fiscal year of the fundamental 
	 * fiscal_period - the fiscal period of the fundamental 
	 * end_date - the last date of the fundamental 
	 * start_date - (not available on the balance sheet) the first date of the fundamental
	 */
	public static final String S_FISCAL_YEAR = "fiscal_year";
	public static final String S_FISCAL_PERIOD = "fiscal_period";
	public static final String S_END_DATE = "end_date";
	public static final String S_START_DATE = "start_date";

	// Data point tags
	public static final String BASIC_EPS = "basiceps";
	public static final String BOOK_VALUE_PER_SHARE = "bookvaluepershare";
	public static final String COMMON_STOCK = "commonequity";
	public static final String COMPANY_NAME = "name";
	public static final String COMPANY_WEBSITE = "company_url";
	public static final String DEBT_TO_EQUITY = "debttoequity";
	public static final String DEBT_TO_NOPAT = "debttonopat";
	public static final String EARNING_PER_SHARE_GROWTH = "epsgrowth";
	public static final String FREE_CASHFLOW_FIRM_GROWTH = "fcffgrowth";
	public static final String NET_CASH_OPERATING_ACTIVITIES = "netcashfromoperatingactivities";
	public static final String NET_OPERATING_PROFIT_AFTER_TAX = "nopat";
	public static final String PRICE_TO_EARNINGS = "pricetoearnings";
	public static final String RETURN_ON_EQUITY = "roe";
	public static final String RETURN_ON_INVESTED_CAPITOL = "roic";
	public static final String TOTAL_OPERATING_EXPENSES = "totaloperatingexpenses";
	public static final String TOTAL_OPERATING_INCOME = "totaloperatingincome";
	public static final String S_TYPE = "type";
	public static final String COMMA = ",";
	public static final String S_DEBT = "debt";
	public static final String EQUITY = null;
	public static final String SALES = "totalrevenue";
	// needed for equity -
	public static final String TOTAL_CURRENT_ASSETS = "totalcurrentassets";
	public static final String LONG_TERM_ASSETS = "";
	public static final String DEPRECIATION = "accumulateddepreciation";

}
