package com.jamesXu.asynchttprequest.request;

import com.jamesXu.asynchttprequest.net.ResponseParseHandler;

/**
 * 
 * @author MobileXu
 *
 * @param <T>
 */
public interface IAsyncHttpRequest<T> {
	
	/**
	 * 
	 * @param prefName
	 * @param cacheKey
	 * @param handler
	 */
	public void request( String prefName, String cacheKey, ResponseParseHandler<T> handler);
}
