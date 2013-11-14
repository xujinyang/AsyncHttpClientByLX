package com.jamesXu.asynchttprequest.request;

import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.jamesXu.asynchttprequest.AppContext;
import com.jamesXu.asynchttprequest.net.AsyncHttpResponse;
import com.jamesXu.asynchttprequest.net.HttpRequestParams;
import com.jamesXu.asynchttprequest.net.LoadDataHandler;
import com.jamesXu.asynchttprequest.net.RequestClient;
import com.jamesXu.asynchttprequest.net.ResponseParseHandler;
import com.jamesXu.asynchttprequest.parser.BaseResponseParser;

/**
 * 
 * @author MobileXu
 * 
 * @param <E>
 * @param <T>
 */
public abstract class AbsAsyncHttpRequest<E, T> implements IAsyncHttpRequest<T> {

	public static final String JSON = "json";
	private Context mContext;
	private HttpRequestParams mParams;
	private ResponseParseHandler<T> mHandler;
	private String mPrefName;
	private String mCacheKey;

	public AbsAsyncHttpRequest(Context context) {
		mContext = context;
	}

	public void setParams(HttpRequestParams params) {
		mParams = params;
	}

	public abstract String getAPI();

	public abstract HttpMethod getHttpMethod();

	public void request(final ResponseParseHandler<T> handler) {
		request(null, null, handler);
	}

	@Override
	public void request(String prefName, String cacheKey,
			final ResponseParseHandler<T> handler) {
		this.mPrefName = prefName;
		this.mCacheKey = cacheKey;
		mHandler = handler;
		mResponse.updatePreNames(mContext,mPrefName, mCacheKey);
		if (getHttpMethod() == HttpMethod.GET) {
			doGet();
		} else {
			doPost();
		}
	}

	private void doGet() {
		if (isParamsEmpty(mParams)) {
			RequestClient.get(getAPI(), mResponse);
		} else {
			RequestClient.get(getUrlWithParams(getAPI(), mParams), mResponse);
		}
	}

	private void doPost() {
		if (isParamsEmpty(mParams)) {
			RequestClient.post(getAPI(), mResponse);
		} else {
			RequestClient.post(getAPI(), mParams.toString(), mResponse);
		}
	}

	private boolean isParamsEmpty(HttpRequestParams params) {
		return params == null || params.isEmpty();
	}

	private String getUrlWithParams(String url, HttpRequestParams params) {
		Set<String> keySet = params.keySet();
		StringBuffer sb = new StringBuffer();
		sb.append("?");
		int i=0;
		for (String key : keySet) {
			i++;
			if(i==keySet.size()){
				sb.append(key + "=" + params.get(key));
			}else{
				sb.append(key + "=" + params.get(key)+"&");
			}
		}
		AppContext.showLog("GET方式URL" + url + sb.toString());
		return url + sb.toString();
	}

	private AsyncHttpResponse mResponse = new AsyncHttpResponse(mContext,
			mPrefName, mCacheKey, new LoadDataHandler() {

				@Override
				public void onStart() {
//					AppContext.showLog("onRequestStart");
					mHandler.onStart();
				}

				@Override
				public void onLoadCaches(String data) {
//					AppContext.showLog("onLoadCaches");
					mHandler.onLoadCaches(parse(data));
				}

				@Override
				public void onSuccess(String data) {
//					AppContext.showLog("成功获得Json数据" + data);
					mHandler.onSuccess(parse(data));
				}

				@Override
				public void onFinished() {
//					AppContext.showLog("onFinished");
					mHandler.onFinished();
				}

				@Override
				public void onFailure(String error, String message) {
					AppContext.showLog(error + "   " + message);
					mHandler.onFailure(error, message);
				}

			});

	private T parse(String responseData) {
		JSONObject jsonData;
		try {
			jsonData = new JSONObject(responseData);
			BaseResponseParser<String> brp = new BaseResponseParser<String>(
					jsonData);
			if (brp.isSucceed()) {
				return parseEntity(brp.getData());
			} else {
				mHandler.onFailure(brp.getErrorCode(), brp.getErrorMessage());
			}
		} catch (JSONException e) {
			mHandler.onFailure("-1", e.getMessage());
		}
		return null;
	}

	protected abstract T parseEntity(String parseData) throws JSONException;
}
