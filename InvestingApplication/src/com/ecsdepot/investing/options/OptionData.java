/**
 * 
 */
package com.ecsdepot.investing.options;

/**
 * @author Justin
 *
 */
public class OptionData
{
	private String date;
	private String expiration;
	private String strike;
	//put or call
	private String type;
	private String close;
	private String close_bid;
	private String close_ask;
	private String volume;
	private String volume_bid;
	private String volume_ask;
	private String trades;
	private String open_interest;
	private String open_interest_change;
	private String next_day_open_interest;
	private String implied_volatility;
	private String implied_volatility_change;
	private String delta;

	/**
	 * 
	 */
	public OptionData()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the date
	 */
	public String getDate()
	{
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date)
	{
		this.date = date;
	}

	/**
	 * @return the expiration
	 */
	public String getExpiration()
	{
		return expiration;
	}

	/**
	 * @param expiration
	 *            the expiration to set
	 */
	public void setExpiration(String expiration)
	{
		this.expiration = expiration;
	}

	/**
	 * @return the strike
	 */
	public String getStrike()
	{
		return strike;
	}

	/**
	 * @param strike
	 *            the strike to set
	 */
	public void setStrike(String strike)
	{
		this.strike = strike;
	}

	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * @return the close
	 */
	public String getClose()
	{
		return close;
	}

	/**
	 * @param close
	 *            the close to set
	 */
	public void setClose(String close)
	{
		this.close = close;
	}

	/**
	 * @return the close_bid
	 */
	public String getClose_bid()
	{
		return close_bid;
	}

	/**
	 * @param close_bid
	 *            the close_bid to set
	 */
	public void setClose_bid(String close_bid)
	{
		this.close_bid = close_bid;
	}

	/**
	 * @return the close_ask
	 */
	public String getClose_ask()
	{
		return close_ask;
	}

	/**
	 * @param close_ask
	 *            the close_ask to set
	 */
	public void setClose_ask(String close_ask)
	{
		this.close_ask = close_ask;
	}

	/**
	 * @return the volume
	 */
	public String getVolume()
	{
		return volume;
	}

	/**
	 * @param volume
	 *            the volume to set
	 */
	public void setVolume(String volume)
	{
		this.volume = volume;
	}

	/**
	 * @return the volume_bid
	 */
	public String getVolume_bid()
	{
		return volume_bid;
	}

	/**
	 * @param volume_bid
	 *            the volume_bid to set
	 */
	public void setVolume_bid(String volume_bid)
	{
		this.volume_bid = volume_bid;
	}

	/**
	 * @return the volume_ask
	 */
	public String getVolume_ask()
	{
		return volume_ask;
	}

	/**
	 * @param volume_ask
	 *            the volume_ask to set
	 */
	public void setVolume_ask(String volume_ask)
	{
		this.volume_ask = volume_ask;
	}

	/**
	 * @return the trades
	 */
	public String getTrades()
	{
		return trades;
	}

	/**
	 * @param trades
	 *            the trades to set
	 */
	public void setTrades(String trades)
	{
		this.trades = trades;
	}

	/**
	 * @return the open_interest
	 */
	public String getOpen_interest()
	{
		return open_interest;
	}

	/**
	 * @param open_interest
	 *            the open_interest to set
	 */
	public void setOpen_interest(String open_interest)
	{
		this.open_interest = open_interest;
	}

	/**
	 * @return the open_interest_change
	 */
	public String getOpen_interest_change()
	{
		return open_interest_change;
	}

	/**
	 * @param open_interest_change
	 *            the open_interest_change to set
	 */
	public void setOpen_interest_change(String open_interest_change)
	{
		this.open_interest_change = open_interest_change;
	}

	/**
	 * @return the next_day_open_interest
	 */
	public String getNext_day_open_interest()
	{
		return next_day_open_interest;
	}

	/**
	 * @param next_day_open_interest
	 *            the next_day_open_interest to set
	 */
	public void setNext_day_open_interest(String next_day_open_interest)
	{
		this.next_day_open_interest = next_day_open_interest;
	}

	/**
	 * @return the implied_volatility
	 */
	public String getImplied_volatility()
	{
		return implied_volatility;
	}

	/**
	 * @param implied_volatility
	 *            the implied_volatility to set
	 */
	public void setImplied_volatility(String implied_volatility)
	{
		this.implied_volatility = implied_volatility;
	}

	/**
	 * @return the implied_volatility_change
	 */
	public String getImplied_volatility_change()
	{
		return implied_volatility_change;
	}

	/**
	 * @param implied_volatility_change
	 *            the implied_volatility_change to set
	 */
	public void setImplied_volatility_change(String implied_volatility_change)
	{
		this.implied_volatility_change = implied_volatility_change;
	}

	/**
	 * @return the delta
	 */
	public String getDelta()
	{
		return delta;
	}

	/**
	 * @param delta
	 *            the delta to set
	 */
	public void setDelta(String delta)
	{
		this.delta = delta;
	}

}
