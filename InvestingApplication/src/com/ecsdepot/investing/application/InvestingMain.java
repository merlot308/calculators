/**
 * 
 */
package com.ecsdepot.investing.application;

import java.nio.file.Paths;

/**
 * @author Justin
 *
 */
public class InvestingMain {

	/**
	 * @param args
	 * @throws ValueInvestingException 
	 */
	public static void main(String[] args) throws ValueInvestingException {
		
		
		CompanyInfoReader companyInfoReader = new CompanyInfoReader(); 
		//companyInfoReader.readCompanyData(Paths.get("Book1.csv"));
		
		companyInfoReader.readFile("Book1.csv");

		
		
	}

}
