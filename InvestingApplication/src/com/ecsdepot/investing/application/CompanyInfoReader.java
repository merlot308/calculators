/**
 * 
 */
package com.ecsdepot.investing.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Justin
 *
 */
public class CompanyInfoReader {

	public void readCompanyData(Path path) throws ValueInvestingException {
		BufferedReader bufferedReader = null;
		int lineIndex = 1;
		try {

			// FileReader fileReader = new FileReader(new File(path));
			System.out.println("found file");
			// bufferedReader = new BufferedReader(fileReader);
			List<CompanyInfoDO> companyList = new ArrayList<CompanyInfoDO>();

			bufferedReader = Files.newBufferedReader(path);
			for (String line = bufferedReader.readLine(); line != null; line = bufferedReader
					.readLine()) {
				StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
				if (lineIndex == 1) {
					System.out.println("Line: " + line);
					// bufferedReader.readLine();
					System.out.println("exiting index equals one");
				}
				if (lineIndex > 1) {
					System.out.println("line: " + line);
					List<String> information = new ArrayList<String>();
					// while (stringTokenizer.hasMoreElements()) {
					// //String token = tokens[0];
					// if (token == null) {
					// token = "";
					// System.out.println("Null token");
					// }
					// System.out.println("Token: " + token);
					// information.add(token);
					// System.out.println("added token");
					// }
					System.out.println("Information List: " + information);
					// populateCompanyInfoList(companyList, information);
				}
				lineIndex++;
			}

		} catch (Exception e) {
			throw new ValueInvestingException("Cannot read company data.", e);
		} finally {
			try {
				bufferedReader.close();
			} catch (Exception e) {
				throw new ValueInvestingException(
						"Unable to complete Finally actions: ", e);
			}
		}
	}

	public void readFile(String path) throws ValueInvestingException {
		FileReader file;
		BufferedReader bufferedReader = null;
		// StringTokenizer stringTokenizer = null;
		try {
			file = new FileReader(path);
			bufferedReader = new BufferedReader(file);
			String line = null;
			List<CompanyInfoDO> companyList = new ArrayList<CompanyInfoDO>();
			List<String> tokenList = new ArrayList<String>();
			int lineIndex = 0;
			while ((line = bufferedReader.readLine()) != null) {
				if (lineIndex == 0) {
					System.out.println("Line: " + line);
					// bufferedReader.readLine();
					System.out.println("exiting index equals one");
				}
				if (lineIndex > 0) {

					String[] tokens = line.split(",");

					CompanyInfoDO companyInfo = new CompanyInfoDO();
					companyInfo.setTicker(tokens[0]);
					companyInfo.setCompanyName(tokens[1]);
					companyInfo.setDate(tokens[2]);
					companyInfo.setStockPrice(tokens[3]);
					companyInfo.setCashFromOperatingActivity(tokens[4]);
					companyInfo.setPurchaseFromPropertyEquipment(tokens[5]);
					companyInfo.setShares(tokens[6]);
					companyInfo.setEarningsPerShare(tokens[7]);
					companyInfo.setFreeCashFlowPerShare(tokens[8]);
					companyInfo.setHistoricalHighPE(tokens[9]);
					companyInfo.setHistoricalLowPE(tokens[10]);
					companyInfo.setFutureGrowthRate(tokens[11]);
					companyInfo.setFuturePE(tokens[12]);
					companyInfo.setStickerPrice(tokens[13]);
					companyInfo.setMarginOfSafety(tokens[14]);
					companyInfo.setPaybackTimePrice(tokens[15]);
					companyInfo.setFutureValue(tokens[16]);
					companyList.add(companyInfo);
				}
				lineIndex++;
				System.out.println("Company List: " + companyList);
			}

		} catch (Exception e) {
			throw new ValueInvestingException("Cannot read company data.", e);
		} finally {
			try {
				bufferedReader.close();
			} catch (Exception e) {
				throw new ValueInvestingException(
						"Unable to complete Finally actions: ", e);
			}
		}

	}

	/**
	 * @param companyList
	 * @param information
	 */
	private void populateCompanyInfoList(List<CompanyInfoDO> companyList,
			List<String> information) {
		for (String s : information) {
			CompanyInfoDO companyInfo = new CompanyInfoDO();
			companyInfo.setTicker(s);
			companyInfo.setCompanyName(s);
			companyInfo.setDate(s);
			companyInfo.setStockPrice(s);
			companyInfo.setCashFromOperatingActivity(s);
			companyInfo.setPurchaseFromPropertyEquipment(s);
			companyInfo.setShares(s);
			companyInfo.setEarningsPerShare(s);
			companyInfo.setFreeCashFlowPerShare(s);
			companyInfo.setHistoricalHighPE(s);
			companyInfo.setHistoricalLowPE(s);
			companyInfo.setFutureGrowthRate(s);
			companyInfo.setFuturePE(s);
			companyInfo.setStickerPrice(s);
			companyInfo.setMarginOfSafety(s);
			companyInfo.setPaybackTimePrice(s);
			companyInfo.setFutureValue(s);
			System.out.println("Company INFO: " + companyInfo.toString());
			companyList.add(companyInfo);
		}
	}

}
