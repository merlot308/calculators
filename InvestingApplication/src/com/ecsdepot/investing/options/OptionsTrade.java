/**
 * 
 */
package com.ecsdepot.investing.options;

import java.math.BigDecimal;

/**
 * @author Justin
 *
 */
public class OptionsTrade
{
	// remove the ones that are results of calculations
	private String account;
	private String bankroll;
	private String ticker;
	private String currPrice;
	private String priceAtEval;
	private String date;
	private String expDate;
	private BigDecimal contracts;
	private BigDecimal floor;
	private BigDecimal ceiling;
	
	// these values are for the optionDO
	private BigDecimal lowStrike;
	private BigDecimal highStrike;
	private BigDecimal ask;
	private BigDecimal bid;
	private BigDecimal probability;
	private BigDecimal premium;
	
	
	// this is trade analysis
	private String placed;
	private String filled;
	private String result;
	private String dateOfResult;
	private BigDecimal finalCreditDebit;
	private BigDecimal finalRorc;
	private BigDecimal finalArorc;
	private BigDecimal openTrades;
	private BigDecimal closedTrades;
	private BigDecimal wins;
	private BigDecimal losses;
	private BigDecimal winPercentage;
	private BigDecimal baseCommission;
	private BigDecimal finalProfitLoss;
	private BigDecimal totalNetResult;
	private BigDecimal yrToDateRorc;
	private BigDecimal yrToDateArorc;
	private String notes;

	
	private BigDecimal spread;
	private BigDecimal earlyExitPrice;
	private BigDecimal netDebitCredit;
	private BigDecimal breakEven;
	private BigDecimal maxProfit;
	private BigDecimal maxLoss;
	private BigDecimal riskCapital;
	private BigDecimal delta;
	private BigDecimal percentInOutMoney;
	private BigDecimal daysToExp;
	private BigDecimal returnOnRiskCapital;
	private BigDecimal annualReturnOnRiskCapital;
	private BigDecimal maxRiskCapitalPercent;
	private BigDecimal averageLoss;
	private BigDecimal kellyR;
	private BigDecimal kellyPercent;
	private BigDecimal maxBet;
	private BigDecimal theoreticalShares;
	private BigDecimal theoreticalContracts;
	
	
	
	/**
	 * 
	 */
	public OptionsTrade()
	{
		// TODO Auto-generated constructor stub
	}

	
	
	
}
