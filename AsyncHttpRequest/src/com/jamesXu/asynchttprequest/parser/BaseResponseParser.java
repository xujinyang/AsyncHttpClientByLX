package com.jamesXu.asynchttprequest.parser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 基础响应解析类
 * 
 * @author lwz
 *
 * @param <T>		待返回数据的 类型
 */
public class BaseResponseParser<T> extends AbsResponseParser<JSONObject, T> {
	
	private final String RESP_JSON_KEY_RET = "ret";
	@SuppressWarnings("unused")
	private final String RESP_JSON_KEY_ERROR_CODE = "errorcode";
	@SuppressWarnings("unused")
	private final String RESP_JSON_KEY_MSG = "msg";
	@SuppressWarnings("unused")
	private final String RESP_JSON_KEY_DATA = "data";
	
	private int mResultCode= 0;
	
	public BaseResponseParser(JSONObject responseData) throws JSONException {
		super(responseData);
		mResultCode = responseData.getInt(RESP_JSON_KEY_RET);
	}

	@Override
	public boolean isSucceed() throws JSONException {
		return getResultCode() == 1 ? true : false;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public T getData() throws JSONException {
		return (T)responseData.toString();
	}

	@Override
	public int getResultCode() throws JSONException {
		return mResultCode;
	}

	@Override
	public String getErrorMessage() throws JSONException {
		return null;
	}

	@Override
	public String getErrorCode() throws JSONException {
		return null;
	}
}
