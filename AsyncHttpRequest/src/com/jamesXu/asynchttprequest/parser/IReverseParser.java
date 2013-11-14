package com.jamesXu.asynchttprequest.parser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 反解析接口, 将 =对象 反解析为 其他数据类型
 * @author lwz
 *
 */
public interface IReverseParser<T extends Object> {
	
	public JSONObject reverseParse( T entity) throws JSONException;
	
}
