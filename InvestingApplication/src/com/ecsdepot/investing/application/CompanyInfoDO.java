/**
 * 
 */
package com.ecsdepot.investing.application;


/**
 * @author Justin
 *
 */
public class CompanyInfoDO {

	private String ticker;
	private String companyName;
	private String date;
	private String stockPrice;
	private String cashFromOperatingActivity;
	private String purchaseFromPropertyEquipment;
	private String shares;
	private String earningsPerShare;
	private String freeCashFlowPerShare;
	private String historicalHighPE;
	private String historicalLowPE;
	private String futureGrowthRate;
	private String futurePE;
	private String stickerPrice;
	private String marginOfSafety;
	private String paybackTimePrice;
	private String futureValue;
	/**
	 * @return the ticker
	 */
	public String getTicker() {
		return ticker;
	}
	/**
	 * @param ticker the ticker to set
	 */
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param string the date to set
	 */
	public void setDate(String string) {
		this.date = string;
	}
	/**
	 * @return the stockPrice
	 */
	public String getStockPrice() {
		return stockPrice;
	}
	/**
	 * @param stockPrice the stockPrice to set
	 */
	public void setStockPrice(String stockPrice) {
		this.stockPrice = stockPrice;
	}
	/**
	 * @return the cashFromOperatingActivity
	 */
	public String getCashFromOperatingActivity() {
		return cashFromOperatingActivity;
	}
	/**
	 * @param cashFromOperatingActivity the cashFromOperatingActivity to set
	 */
	public void setCashFromOperatingActivity(String cashFromOperatingActivity) {
		this.cashFromOperatingActivity = cashFromOperatingActivity;
	}
	/**
	 * @return the purchaseFromPropertyEquipment
	 */
	public String getPurchaseFromPropertyEquipment() {
		return purchaseFromPropertyEquipment;
	}
	/**
	 * @param purchaseFromPropertyEquipment the purchaseFromPropertyEquipment to set
	 */
	public void setPurchaseFromPropertyEquipment(
			String purchaseFromPropertyEquipment) {
		this.purchaseFromPropertyEquipment = purchaseFromPropertyEquipment;
	}
	/**
	 * @return the shares
	 */
	public String getShares() {
		return shares;
	}
	/**
	 * @param shares the shares to set
	 */
	public void setShares(String shares) {
		this.shares = shares;
	}
	/**
	 * @return the earningsPerShare
	 */
	public String getEarningsPerShare() {
		return earningsPerShare;
	}
	/**
	 * @param earningsPerShare the earningsPerShare to set
	 */
	public void setEarningsPerShare(String earningsPerShare) {
		this.earningsPerShare = earningsPerShare;
	}
	/**
	 * @return the freeCashFlowPerShare
	 */
	public String getFreeCashFlowPerShare() {
		return freeCashFlowPerShare;
	}
	/**
	 * @param freeCashFlowPerShare the freeCashFlowPerShare to set
	 */
	public void setFreeCashFlowPerShare(String freeCashFlowPerShare) {
		this.freeCashFlowPerShare = freeCashFlowPerShare;
	}
	/**
	 * @return the historicalHighPE
	 */
	public String getHistoricalHighPE() {
		return historicalHighPE;
	}
	/**
	 * @param historicalHighPE the historicalHighPE to set
	 */
	public void setHistoricalHighPE(String historicalHighPE) {
		this.historicalHighPE = historicalHighPE;
	}
	/**
	 * @return the historicalLowPE
	 */
	public String getHistoricalLowPE() {
		return historicalLowPE;
	}
	/**
	 * @param historicalLowPE the historicalLowPE to set
	 */
	public void setHistoricalLowPE(String historicalLowPE) {
		this.historicalLowPE = historicalLowPE;
	}
	/**
	 * @return the futureGrowthRate
	 */
	public String getFutureGrowthRate() {
		return futureGrowthRate;
	}
	/**
	 * @param futureGrowthRate the futureGrowthRate to set
	 */
	public void setFutureGrowthRate(String futureGrowthRate) {
		this.futureGrowthRate = futureGrowthRate;
	}
	/**
	 * @return the futurePE
	 */
	public String getFuturePE() {
		return futurePE;
	}
	/**
	 * @param futurePE the futurePE to set
	 */
	public void setFuturePE(String futurePE) {
		this.futurePE = futurePE;
	}
	/**
	 * @return the stickerPrice
	 */
	public String getStickerPrice() {
		return stickerPrice;
	}
	/**
	 * @param stickerPrice the stickerPrice to set
	 */
	public void setStickerPrice(String stickerPrice) {
		this.stickerPrice = stickerPrice;
	}
	/**
	 * @return the marginOfSafety
	 */
	public String getMarginOfSafety() {
		return marginOfSafety;
	}
	/**
	 * @param marginOfSafety the marginOfSafety to set
	 */
	public void setMarginOfSafety(String marginOfSafety) {
		this.marginOfSafety = marginOfSafety;
	}
	/**
	 * @return the paybackTimePrice
	 */
	public String getPaybackTimePrice() {
		return paybackTimePrice;
	}
	/**
	 * @param paybackTimePrice the paybackTimePrice to set
	 */
	public void setPaybackTimePrice(String paybackTimePrice) {
		this.paybackTimePrice = paybackTimePrice;
	}
	/**
	 * @return the futureValue
	 */
	public String getFutureValue() {
		return futureValue;
	}
	/**
	 * @param futureValue the futureValue to set
	 */
	public void setFutureValue(String futureValue) {
		this.futureValue = futureValue;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompanyInfoDO [ticker=" + ticker + "\n companyName="
				+ companyName + "\n date=" + date + "\n stockPrice="
				+ stockPrice + "\n cashFromOperatingActivity="
				+ cashFromOperatingActivity
				+ "\n purchaseFromPropertyEquipment="
				+ purchaseFromPropertyEquipment + "\n shares=" + shares
				+ "\n earningsPerShare=" + earningsPerShare
				+ "\n freeCashFlowPerShare=" + freeCashFlowPerShare
				+ "\n historicalHighPE=" + historicalHighPE
				+ "\n historicalLowPE=" + historicalLowPE
				+ "\n futureGrowthRate=" + futureGrowthRate + "\n futurePE="
				+ futurePE + "\n stickerPrice=" + stickerPrice
				+ "\n marginOfSafety=" + marginOfSafety
				+ "\n paybackTimePrice=" + paybackTimePrice
				+ "\n futureValue=" + futureValue + "]";
	}

	






	
	
}