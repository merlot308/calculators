package com.ecsdepot.investing.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class InvestingProperties
{

	private static final String CLASS_NAME = InvestingProperties.class.getName();
	private Logger logger = Logger.getLogger(CLASS_NAME);
	private Properties props;
	private String propsFile;
	private String quotesURI;
	private String lookupURI;
	private String intrinioUser;
	private String intrinioPass;
	private String intrinioBaseURL;
	private String allCompaniesFile;
	private String watchListFile;
	private String meetsAllConditionsFile;
	private String notMeetAllConditionsFile;
	private String processedCompaniesFile;
	private String dataFile;

	public InvestingProperties()
	{
		super();
		props = new Properties();
	}

	public InvestingProperties(String file)
	{
		this();
		this.propsFile = file;
		props = loadInvestingProperties(file);
	}

	/**
	 * @return the lookupURI
	 */
	public String getLookupURI()
	{
		return lookupURI;
	}

	/**
	 * @param lookupURI
	 *            the lookupURI to set
	 */
	public void setLookupURI(String lookupURI)
	{
		this.lookupURI = lookupURI;
	}

	/**
	 * @return the propsFile
	 */
	public String getPropsFile()
	{
		return propsFile;
	}

	/**
	 * @param propsFile
	 *            the propsFile to set
	 */
	public void setPropsFile(String propsFile)
	{
		this.propsFile = propsFile;
	}

	/**
	 * @return the quotesURI
	 */
	public String getQuotesURI()
	{
		return quotesURI;
	}

	/**
	 * @param quotesURI
	 *            the quotesURI to set
	 */
	public void setQuotesURI(String quotesURI)
	{
		this.quotesURI = quotesURI;
	}

	/**
	 * @return the intrinioUser
	 */
	public String getIntrinioUser()
	{
		return intrinioUser;
	}

	/**
	 * @param intrinioUser
	 *            the intrinioUser to set
	 */
	public void setIntrinioUser(String intrinioUser)
	{
		this.intrinioUser = intrinioUser;
	}

	/**
	 * @return the intrinioPass
	 */
	public String getIntrinioPass()
	{
		return intrinioPass;
	}

	/**
	 * @param intrinioPass
	 *            the intrinioPass to set
	 */
	public void setIntrinioPass(String intrinioPass)
	{
		this.intrinioPass = intrinioPass;
	}

	/**
	 * @return the intrinioBaseURL
	 */
	public String getIntrinioBaseURL()
	{
		return intrinioBaseURL;
	}

	/**
	 * @param intrinioBaseURL
	 *            the intrinioBaseURL to set
	 */
	public void setIntrinioBaseURL(String intrinioBaseURL)
	{
		this.intrinioBaseURL = intrinioBaseURL;
	}

	/**
	 * @return the props
	 */
	public Properties getProps()
	{
		return props;
	}

	/**
	 * @param props
	 *            the props to set
	 */
	public void setProps(Properties props)
	{
		this.props = props;
	}

	/**
	 * @return the allCompaniesFile
	 */
	public String getAllCompaniesFile()
	{
		return allCompaniesFile;
	}

	/**
	 * @param allCompaniesFile
	 *            the allCompaniesFile to set
	 */
	public void setAllCompaniesFile(String allCompaniesFile)
	{
		this.allCompaniesFile = allCompaniesFile;
	}

	/**
	 * @return the watchListFile
	 */
	public String getWatchListFile()
	{
		return watchListFile;
	}

	/**
	 * @param watchListFile
	 *            the watchListFile to set
	 */
	public void setWatchListFile(String watchListFile)
	{
		this.watchListFile = watchListFile;
	}

	/**
	 * @return the meetsAllConditionsFile
	 */
	public String getMeetsAllConditionsFile()
	{
		return meetsAllConditionsFile;
	}

	/**
	 * @param meetsAllConditionsFile
	 *            the meetsAllConditionsFile to set
	 */
	public void setMeetsAllConditionsFile(String meetsAllConditionsFile)
	{
		this.meetsAllConditionsFile = meetsAllConditionsFile;
	}

	/**
	 * @return the notMeetAllConditionsFile
	 */
	public String getNotMeetAllConditionsFile()
	{
		return notMeetAllConditionsFile;
	}

	/**
	 * @param notMeetAllConditionsFile
	 *            the notMeetAllConditionsFile to set
	 */
	public void setNotMeetAllConditionsFile(String notMeetAllConditionsFile)
	{
		this.notMeetAllConditionsFile = notMeetAllConditionsFile;
	}

	/**
	 * @return the processedCompaniesFile
	 */
	public String getProcessedCompaniesFile()
	{
		return processedCompaniesFile;
	}

	/**
	 * @param processedCompaniesFile
	 *            the processedCompaniesFile to set
	 */
	public void setProcessedCompaniesFile(String processedCompaniesFile)
	{
		this.processedCompaniesFile = processedCompaniesFile;
	}

	/**
	 * @return the dataFile
	 */
	public String getDataFile()
	{
		return dataFile;
	}

	/**
	 * @param dataFile
	 *            the dataFile to set
	 */
	public void setDataFile(String dataFile)
	{
		this.dataFile = dataFile;
	}

	private Properties loadInvestingProperties(String file)
	{
		InputStream propsInputStream = null;
		try
		{
			propsInputStream = new FileInputStream(new File(file));
			props.load(propsInputStream);
			setQuotesURI(props.getProperty("getQuote_DataSource"));
			setLookupURI(props.getProperty("lookupCompany_DataSource"));
			setIntrinioUser(props.getProperty("intrinio_username"));
			setIntrinioPass(props.getProperty("intrinio_password"));
			setIntrinioBaseURL(props.getProperty("intrinio_baseURL"));
			setAllCompaniesFile(props.getProperty("allCompaniesFile"));
			setWatchListFile(props.getProperty("watchListFile"));
			setMeetsAllConditionsFile(props.getProperty("meetsAllConditionsFile"));
			setNotMeetAllConditionsFile(props.getProperty("notMeetAllConditionsFile"));
			setProcessedCompaniesFile(props.getProperty("processedCompaniesFile"));
			setDataFile(props.getProperty("dataFile"));

		} catch (IOException e)
		{
			logger.fatal("Cannot load the given properties file in: " + CLASS_NAME + ". Properties may not exist", e);
		} finally
		{
			if (propsInputStream != null)
			{
				try
				{
					propsInputStream.close();
				} catch (IOException e)
				{
					logger.error("Can't close properties input stream in: " + CLASS_NAME, e);
				}
			}
		}
		return props;
	}
}
