/**
 * 
 */
package com.ecsdepot.investing.application;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

import com.ecsdepot.investing.utilities.ConversionUtility;

/**
 * @author
 *
 */
public class Annuals
{

	private BigDecimal value;
	private BigDecimal dataYear;
	private Date dateCalculated;
	private String type;

	public Annuals()
	{
		super();
	}

	/**
	 * 
	 * @param value
	 * @param dataYear
	 * @param dateCalculated
	 * @param type
	 *            - The name of the item. i.e., BVPS, EPS, EPS_GR etc. ...
	 */
	public Annuals(final BigDecimal value, final BigDecimal dataYear, final Date dateCalculated, final String type)
	{
		this();
		this.value = value;
		this.dataYear = dataYear;
		this.dateCalculated = dateCalculated;
		this.type = type;
	}

	public Annuals(final String value, final String dataYear, final Date dateCalculated, final String type)
	{
		this(Double.valueOf(value), Double.valueOf(dataYear), dateCalculated, type);
	}

	public Annuals(final int value, final int dataYear, final Date dateCalculated, final String type)
	{
		this(BigDecimal.valueOf(value), BigDecimal.valueOf(dataYear), dateCalculated, type);
	}

	public Annuals(final double value, final double dataYear, final Date dateCalculated, final String type)
	{
		this(BigDecimal.valueOf(value), BigDecimal.valueOf(dataYear), dateCalculated, type);
	}

	/**
	 * @return the value
	 */
	public BigDecimal getValue()
	{
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(final BigDecimal value)
	{
		this.value = value;
	}

	/**
	 * @return the dateCalculated
	 */
	public Date getDateCalculated()
	{
		return dateCalculated;
	}

	/**
	 * @param dateCalculated
	 *            the dateCalculated to set
	 */
	public void setDateCalculated(final Date dateCalculated)
	{
		this.dateCalculated = dateCalculated;
	}

	/**
	 * @return the dataYear
	 */
	public BigDecimal getDataYear()
	{
		return dataYear;
	}

	/**
	 * @param dataYear
	 *            the dataYear to set
	 */
	public void setDataYear(final BigDecimal dataYear)
	{
		this.dataYear = dataYear;
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
	public void setType(final String type)
	{
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Annuals [value=" + value + ", dateCalculated=" + dateCalculated + ", dataYear=" + dataYear + ", type="
				+ type + "]";
	}

	public static Comparator<Annuals> yearComparator = new Comparator<Annuals>()
	{

		@Override
		public int compare(final Annuals object1, final Annuals object2)
		{
			BigDecimal val1 = object1.getDataYear();
			BigDecimal val2 = object2.getDataYear();
			if (val1 == null)
			{
				val1 = InvestConstants.ZERO_VALUE;
			}
			if (val2 == null)
			{
				val2 = InvestConstants.ZERO_VALUE;
			}
			Date date1 = ConversionUtility.getInstance().convertBigDecimalToDate(val1);
			Date date2 = ConversionUtility.getInstance().convertBigDecimalToDate(val2);

			return date1.compareTo(date2);
		}
	};

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		boolean isEqual = false;
		if (obj == null)
			return isEqual;
		if (obj == this)
			return isEqual;
		if (!(obj instanceof Annuals))
			return isEqual;
		Annuals annual = (Annuals) obj;
		if (this.dataYear.equals(annual.dataYear) && this.value.equals(annual.getValue())
				&& this.type.equals(annual.getType()))
		{
			isEqual = true;
		}
		return isEqual;
	}

}
