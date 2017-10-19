package com.ecsdepot.investing.tradeking;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.model.OAuth1RequestToken;

public class TradeKingApi extends DefaultApi10a
{

	private static final String AUTHORIZE_URL = "https://developers.tradeking.com/oauth/authorize?oauth_token=%s";
	private static final String REQUEST_TOKEN_RESOURCE = "https://developers.tradeking.com/oauth/request_token";
	private static final String ACCESS_TOKEN_RESOURCE = "https://developers.tradeking.com/oauth/access_token";

	protected TradeKingApi()
	{
		// TODO Auto-generated constructor stub
	}

	private static class InstanceHolder
	{
		private static final TradeKingApi INSTANCE = new TradeKingApi();
	}

	public static TradeKingApi instance()
	{
		return InstanceHolder.INSTANCE;
	}

	@Override
	public String getAccessTokenEndpoint()
	{
		return ACCESS_TOKEN_RESOURCE;
	}

	@Override
	public String getRequestTokenEndpoint()
	{
		return REQUEST_TOKEN_RESOURCE;
	}

	@Override
	public String getAuthorizationUrl(OAuth1RequestToken requestToken)
	{
		return String.format(AUTHORIZE_URL, requestToken.getToken());
	}
}
