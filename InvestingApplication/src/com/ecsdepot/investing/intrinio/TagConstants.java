/**
 * 
 */
package com.ecsdepot.investing.intrinio;

/**
 * @author Justin
 *
 */
public class TagConstants
{
	// JSON TAGS FOR NODES
	public static final String S_TAG = "tag";
	public static final String S_DATA = "data";
	public static final String S_DATE = "date";
	public static final String S_ITEM = "item";
	public static final String S_IDENTIFIER = "identifier";
	public static final String S_VALUE = "value";

	// Return values from Balance Sheet
	public static final String ACQUISITIONS = "acquisitions";
	public static final String CASH_INCOME_TAX_PAID = "cashincometaxespaid";
	public static final String DEPRECIATION_EXPENSE = "depreciationexpense";
	public static final String INCREASE_DECREASE_OPERATING_CAPITOL = "increasedecreaseinoperatingcapital";
	public static final String ISSUE_COMMON_EQUITY = "issuanceofcommonequity";
	public static final String ISSUE_DEBT = "issuanceofdebt";
	public static final String NET_CASH_CONT_FINANCING_ACT = "netcashfromcontinuingfinancingactivities";
	public static final String NET_CASH_CONT_INVESTING_ACT = "netcashfromcontinuinginvestingactivities";
	public static final String NET_CASH_FINANCING_ACT = "netcashfromfinancingactivities";
	public static final String NET_CASH_FROM_CONT_OPER_ACT = "netcashfromcontinuingoperatingactivities";
	public static final String NET_CASH_INVEST_ACT = "netcashfrominvestingactivities";
	public static final String NET_CASH_OPER_ACT = "netcashfromoperatingactivities";
	public static final String NET_CHANGE_CASH = "netchangeincash";
	public static final String NET_INCOME = "netincome";
	public static final String NET_INCOME_CONTINUING = "netincomecontinuing";
	public static final String NON_CASH_ADJ_TO_NET_INCOME = "noncashadjustmentstonetincome";
	public static final String OTHER_FINANCING_ACT = "otherfinancingactivitiesnet";
	public static final String OTHER_INVEST_ACT = "otherinvestingactivitiesnet";
	public static final String PAY_DIVIDENDS = "paymentofdividends";
	public static final String PUR_INVESTMENTS = "purchaseofinvestments";
	public static final String PURCHASE_PROP_EQUIP = "purchaseofplantpropertyandequipment";
	public static final String REPURCHASE_COMMON_EQUITY = "repurchaseofcommonequity";
	public static final String SALE_OF_INVESTMENTS = "saleofinvestments";

	// BALANCE SHEET RETURNS
	public static final String CASH_AND_EQUITY = "cashandequivalents";
	public static final String SHORT_TERM_INVESTMENTS = "shortterminvestments";
	public static final String NOT_RECEIVABLE = "notereceivable";
	public static final String ACCOUNTS_RECEIVABLE = "accountsreceivable";
	public static final String NET_INVENTORY = "netinventory";
	public static final String TOTAL_CUR_ASSETS = "totalcurrentassets";
	public static final String NET_PPE = "netppe";
	public static final String LONG_TERM_INVESTMENTS = "longterminvestments";
	public static final String OTHER_NON_CURRENT_ASSETS = "othernoncurrentassets";
	public static final String TOTAL_NON_CURRENT_ASSETS = "totalnoncurrentassets";
	public static final String TOTAL_ASSETS = "totalassets";
	public static final String ACCOUNTS_PAYABLE = "accountspayable";
	public static final String TOTAL_CURRENT_LIABILITIES = "totalcurrentliabilities";
	public static final String LONG_TERM_DEBT = "longtermdebt";
	public static final String TOTAL_NON_CURRENT_LIABILITIES = "totalnoncurrentliabilities";
	public static final String TOTAL_LIABILITIES = "totalliabilities";
	public static final String REDEEMABLE_NON_CONTROLLING_INTEREST = "redeemablenoncontrollinginterest";
	public static final String COMMON_EQUITY = "commonequity";
	public static final String RETAINED_EARNINGS = "retainedearnings";
	public static final String TREASURY_STOCK = "treasurystock";
	public static final String ACCUMULATED_OTHER_COMPREHENSIVE_INCOME_DIV_LOSS = "aoci";
	public static final String TOTAL_COMMON_EQUITY = "totalcommonequity";
	public static final String TOTAL_EQUITY = "totalequity";
	public static final String NON_CONTROLLING_INTERESTS = "noncontrollinginterests";
	public static final String TOTAL_EQUITY_AND_NON_CONTROLLING_INTERESTS = "totalequityandnoncontrollinginterests";
	public static final String TOTAL_LIABILITIES_AND_EQUITY = "totalliabilitiesandequity";
	public static final String NON_CONCURRENT_DEFERRED_TAX_ASSETS = "noncurrentdeferredtaxassets";
	public static final String CURRENT_DEFERRED_REVENUE = "currentdeferredrevenue";
	public static final String NON_CURRENT_DEFERRED_TAX_LIABILITIES = "noncurrentdeferredtaxliabilities";

	// INCOME STATEMENT RETURN
	public static final String OPERATING_REVENUE = "operatingrevenue";
	public static final String TOTAL_REVENUE = "totalrevenue";
	public static final String OPERATING_COST_OF_REVENUE = "operatingcostofrevenue";
	public static final String OTHER_COST_OF_REVENUE = "othercostofrevenue";
	public static final String TOTAL_COST_OF_REVENUE = "totalcostofrevenue";
	public static final String TOTAL_GROSS_PROFIT = "totalgrossprofit";
	public static final String SGA_EXPENSE = "sgaexpense";
	public static final String TOTAL_OPERATE_EXPENSE = "totaloperatingexpenses";
	public static final String TOTAL_OPERATE_INCOME = "totaloperatingincome";
	public static final String TOTAL_INTEREST_EXPENSE = "totalinterestexpense";
	public static final String TOTAL_INTEREST_INCOME = "totalinterestincome";
	public static final String OTHER_INCOME = "otherincome";
	public static final String TOTAL_OTHER_INCOME = "totalotherincome";
	public static final String TOTAL_PRE_TAX_INCOME = "totalpretaxincome";
	public static final String INCOME_TAX_EXPENSE = "incometaxexpense";
	public static final String NET_INCOME_TO_CONTROLLING_INTEREST = "netincometononcontrollinginterest";
	public static final String NET_INCOME_TO_COMMON = "netincometocommon";
	public static final String WEIGHTED_AVG_BASIC_SHARES = "weightedavebasicsharesos";
	public static final String EARNING_PER_SHARE = "basiceps";
	public static final String WEIGHTED_AVG_DILUTED_SHARES_OUTSTAND = "weightedavedilutedsharesos";
	public static final String DILUTED_EPS = "dilutedeps";
	public static final String WEIGHTED_AVG_BASIC_DILUTED_SHARES_OUTSTAND = "weightedavebasicdilutedsharesos";
	public static final String BASIC_DILUTED_EPS = "basicdilutedeps";
	public static final String CASH_DIVIDEND_PER_SHARE = "cashdividendspershare";
	public static final String IMPAIRMENT_EXPENSE = "impairmentexpense";

	// DATA POINT TAGS
	public static final String BOOK_VALUE_PER_SHARE = "bookvaluepershare";
	public static final String COMMON_STOCK = "commonequity";
	public static final String COMPANY_NAME = "name";
	public static final String COMPANY_WEBSITE = "company_url";
	public static final String DEBT_TO_EQUITY = "debttoequity";
	public static final String DEBT_TO_NOPAT = "debttonopat";
	public static final String EARNING_PER_SHARE_GROWTH = "epsgrowth";
	public static final String FREE_CASHFLOW_FIRM_GROWTH = "fcffgrowth";
	public static final String NET_CASH_OPERATING_ACTIVITIES = "netcashfromoperatingactivities";
	public static final String NET_OPERATING_PROFIT_AFTER_TAX = "NOPAT";
	public static final String PRICE_TO_EARNINGS = "pricetoearnings";
	public static final String RETURN_ON_EQUITY = "roe";
	public static final String RETURN_ON_INVESTED_CAPITOL = "roic";
	public static final String TOTAL_OPERATING_EXPENSES = "totaloperatingexpenses";
	public static final String TOTAL_OPERATING_INCOME = "totaloperatingincome";

}
