package com.ecsdepot.investing.intrinio;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class IntrinioTagsMessages
{
	private static final String BUNDLE_NAME = "com.ecsdepot.investing.intrinio.intrinioTags"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private IntrinioTagsMessages()
	{
	}

	public static String getString(String key)
	{
		try
		{
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e)
		{
			return '!' + key + '!';
		}
	}
}
