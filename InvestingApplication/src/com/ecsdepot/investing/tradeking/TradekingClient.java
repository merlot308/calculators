package com.ecsdepot.investing.tradeking;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

public class TradekingClient
{
	public static class TradeKingClient
	{
		private static final String CONSUMER_KEY = "zXoQykAvz2DUDSOjje2XOPM8K6mLjOV0ibqBJKPg";
		private static final String CONSUMER_SECRET = "k8pPYv3z0rZttN2J2xhbrMTnJ4Arv2COGYf7Ymdo";
		private static final String OAUTH_TOKEN = "uTfCCBectS70eh6WkS93HGS0wzx2dys7jXp2maEm";
		private static final String OAUTH_TOKEN_SECRET = "xCD7Z4ISztPBEbnPwtlAbYSJJUVhjjUVNR4MaYpO";
		
		private static final String PROTECTED_RESOURCE_URL = "https://api.tradeking.com/v1/member/profile.json";
	  
		public static void main(String[] args)
		{
			//TODO - I can get EPS from tradeking and book value from tradeking. 
			
			OAuth10aService service = new ServiceBuilder()
	                                .apiKey(CONSUMER_KEY)
	                                .apiSecret(CONSUMER_SECRET)
	                                .build(TradeKingApi.instance());
			OAuth1AccessToken accessToken = new OAuth1AccessToken(OAUTH_TOKEN, OAUTH_TOKEN_SECRET);
	    
			// Now let's go and ask for a protected resource!
			OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
			service.signRequest(accessToken, request);
			Response response;
			try
			{
				response = service.execute(request);
				System.out.println(response.getBody());
			} catch (InterruptedException | ExecutionException | IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
			
		}
	}
}
