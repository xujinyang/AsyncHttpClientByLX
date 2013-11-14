package com.jamesXu.asynchttprequest.net;

import android.content.Context;

import com.jamesXu.asynchttprequest.tools.SharedPreferecesUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * 异步 HTTP 请求响应类
 * 
 * <p>
 * 所以有异步 HTTP 请求响应类都要继承该类
 * </p>
 * 
 * @author MobileXu
 * 
 */
public class AsyncHttpResponse extends AsyncHttpResponseHandler {
	/**
	 * 自定义异步加载请求类
	 */
	private LoadDataHandler mHandler;
	private Context mContext;
	private String mPrefName;
	private String mCacheDataKey;
	private boolean mIsCache;// 是否需要缓存

	/**
	 * 
	 * 如果不需要缓存，建议使用
	 * {@link #AsyncHttpResponse(Context context, LoadDataHandler handler)}
	 * 
	 * @param context
	 *            上下文对象
	 * @param prefName
	 *            preferences 的名字
	 * @param cacheDataKey
	 *            缓存数据在 preferences 中的键；可为 null，表示该响应数据不需要存储在本地缓存
	 * @param handler
	 *            加载数据句柄对象
	 */
	public AsyncHttpResponse(Context context, String prefName,
			String cacheDataKey, LoadDataHandler handler) {
		mHandler = handler;
		mContext = context;
		mPrefName = prefName;
		mCacheDataKey = cacheDataKey;
	}

	/**
	 * @param context
	 *            上下文对象
	 * @param handler
	 *            加载数据句柄对象
	 */
	public AsyncHttpResponse(Context context, LoadDataHandler handler) {
		this(context, null, null, handler);
	}

	/**
	 * 更新String prefName, String cacheDataKey
	 * 因为文竹的重构，将AbsAsyncRequest初始化的时候，这俩个值还没用赋值，所有在get或者post方法之前进行更新
	 */
	public void updatePreNames(Context context,String preName, String cacheDate) {
		mContext = context;
		mPrefName = preName;
		mCacheDataKey = cacheDate;
		mIsCache = (mPrefName != null && mCacheDataKey != null);
	}

	@Override
	public void onStart() {
		mHandler.onStart();
		// 先从缓存中加载数据(如果有的话)
	
		if (mIsCache) {
			String cacheData = getDataFromCache(mCacheDataKey);
			if (cacheData != null) {
				mHandler.onLoadCaches(cacheData);
			}
		}
	}

	// 请求成功
	@Override
	public void onSuccess(String data) {
		if (data != null) {
			if (mIsCache) {
				storeDataToCache(mCacheDataKey, data);
			}
			mHandler.onSuccess(data);
		}
	}

	/**
	 * 解析数据方法
	 * 
	 * @param data
	 *            要解析的源数据
	 * @return 解析好的字符串对象，若返回 null， 会调用响应出错方法
	 */
	// public abstract String parserData(String data);

	@Override
	public void onFailure(Throwable arg0, String message) {
		onFailure(arg0.getMessage(), message);
	}

	/**
	 * 响应结果出错
	 * 
	 * @param error
	 * @param message
	 */
	public void onFailure(String error, String message) {
		mHandler.onFailure(error, message);
	}

	@Override
	public void onFinish() {
		mHandler.onFinished();
	}

	/**
	 * 存储数据到本地缓存
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	private void storeDataToCache(String key, String value) {
		SharedPreferecesUtil.putString(mContext, mPrefName, key, value);
	}

	/**
	 * 得到本地缓存数据中
	 * 
	 * @param key
	 *            键
	 * @return 返回值
	 */
	private String getDataFromCache(String key) {
		return SharedPreferecesUtil.getString(mContext, mPrefName, key);
	}
}
