/**
 * 
 */
package com.ecsdepot.investing.application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.FileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumWriter;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Justin
 *
 */
public final class StoreData
{

	private static final String CLASS_NAME = StoreData.class.getName();
	private static final StoreData INSTANCE = new StoreData();
	private transient final Schema companySchema;

	private static final Logger LUGGER = Logger.getLogger(CLASS_NAME);

	private StoreData()
	{
		companySchema = ReflectData.AllowNull.get().getSchema(CompanyDO.class);
	}

	/**
	 * Gets the instance of Store Data for use.
	 * 
	 * @return
	 */
	public static StoreData getInstance()
	{
		return INSTANCE;
	}

	/**
	 * Reads the data file specified and creates the instance of companies from
	 * this file.
	 * 
	 * @param file
	 *            Name of the file containing data.
	 * @return
	 */
	public Companies readData(File file)
	{
		ReflectData reflectData = new ReflectData();
		@SuppressWarnings("unchecked")
		DatumReader<CompanyDO> companyReader = reflectData.createDatumReader(companySchema);
		FileReader<CompanyDO> dataFileReader = null;
		Companies companies = Companies.getInstance();
		try
		{
			dataFileReader = new DataFileReader<CompanyDO>(file, companyReader);

			while (dataFileReader.hasNext())
			{
				CompanyDO comp = dataFileReader.next();
				companies.addCompany(comp);
			}
		} catch (IOException e)
		{
			LUGGER.log(Level.ERROR, "Reading companies from file failed", e);
		} finally
		{
			try
			{
				if (dataFileReader != null)
				{

					dataFileReader.close();
				}
			} catch (IOException e)
			{
				LUGGER.log(Level.ERROR, "Closing dataFileReader failed", e);
			}
		}
		return companies;
	}

	/**
	 * Writes the data to the file specified as Avro.
	 * 
	 * @param companies
	 *            The instance of {@link Companies} to write.
	 * @param fileName
	 *            Full path of the file to be written.
	 */
	public void writeData(Companies companies, String fileName)
	{
		DatumWriter<CompanyDO> companyWriter = new ReflectDatumWriter<CompanyDO>(companySchema);
		DataFileWriter<CompanyDO> fileWriter = new DataFileWriter<>(companyWriter);
		LUGGER.log(Level.TRACE, "Comp writer and file writer created");
		try
		{
			LUGGER.log(Level.TRACE, "Try to create File writer");
			LUGGER.log(Level.DEBUG, "fileName: " + fileName);
			fileWriter.create(companySchema, new File(fileName));

			for (String tick : companies.getCompaniesMap().keySet())
			{
				CompanyDO companydo = companies.getCompaniesMap().get(tick);
				fileWriter.append(companydo);
			}

		} catch (IOException e)
		{

			LUGGER.log(Level.ERROR, "Writing Data to file failed. File name is: " + fileName, e);

		} finally
		{
			try
			{
				fileWriter.close();
			} catch (IOException e)
			{
				LUGGER.log(Level.ERROR, "Unable to close fileWriter", e);
			}
		}
	}

	/**
	 * Used to dump the results list from Intrinio in the event of an error
	 * causing the application to crash.
	 * 
	 * @param resultList
	 */
	public void writeResults(List<String> resultList)
	{
		File file = null;
		FileWriter fileWriter = null;
		try
		{
			int counter = 0;
			for (String result : resultList)
			{

				String path = "C:\\Users\\Justi\\Desktop\\Results2\\result_" + counter;
				file = new File(path);
				fileWriter = new FileWriter(file);
				fileWriter.write(result);
				fileWriter.flush();
				counter++;
			}
		} catch (IOException e)
		{
			LUGGER.log(Level.ERROR, "error writing results", e);
		} finally
		{
			try
			{
				if (fileWriter != null)
				{
					fileWriter.close();
				}
			} catch (IOException e)
			{
				LUGGER.log(Level.ERROR, "Unable to close the file writer", e);
			}
		}

	}

	/**
	 * Writes the data to a .txt file in the form of JSON for easy reading.
	 * 
	 * @param companies
	 */
	public void simpleFileWrite(Companies companies)
	{
		ObjectMapper mapper = new ObjectMapper();
		File file = null;
		FileWriter fileWriter = null;
		for (String key : companies.getCompaniesMap().keySet())
		{
			String path = System.getProperty("user.dir") + File.separator + "output" + File.separator + key + ".txt";
			file = new File(path);

			try
			{
				fileWriter = new FileWriter(file);
				CompanyDO company = companies.getCompaniesMap().get(key);
				mapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, company);

			} catch (IOException e)
			{
				LUGGER.log(Level.ERROR, "Writing data failed.");
			} finally
			{
				try
				{
					if (fileWriter != null)
					{
						fileWriter.close();
					}
				} catch (IOException e)
				{
					LUGGER.log(Level.ERROR, "Cannot close file writer in " + CLASS_NAME);
				}
			}
		}

	}
}
