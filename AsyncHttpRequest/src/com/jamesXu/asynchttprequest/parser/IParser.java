package com.jamesXu.asynchttprequest.parser;

import org.json.JSONException;

/**
 * 
 * @author MobileXu
 *
 * @param <T>
 * @param <E>
 */
public interface IParser<T, E> {
	
	public E parse( T parseData) throws JSONException;
	
}
