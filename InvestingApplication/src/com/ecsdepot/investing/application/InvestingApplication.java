package com.ecsdepot.investing.application;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class InvestingApplication extends WebApplication{

	@Override
	public Class<? extends Page> getHomePage() {
		// TODO Auto-generated method stub
		return InvestingBasePage.class;
	}

}
