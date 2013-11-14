package com.jamesXu.asynchttprequest.parser;

import org.json.JSONException;

/**
 * 接口 响应结果解析器
 * 
 * @author lwz
 */
public interface IResponseParser<T> {

	/**
	 * 判断响应的结果是否成功得到数据
	 * 
	 * @return boolean 成功得到数据则返回true,否则返回false
	 * @throws JSONException
	 */
	public boolean isSucceed() throws JSONException;

	/**
	 * 当isSuccessed()返回的结果为真，说明调用成功 则可以调用这个方法来获取 JSONArray 数据
	 * 
	 * @return JSONArray
	 * @throws JSONException
	 */
	public T getData() throws JSONException;
	
	/**
	 * 得到结果码，如： 
	 * 1 : 查询成功 0 : 查询成功 没有数据 -1 : 未鉴权 -2 : 系统正忙[查询数据库失败等]
	 * 
	 * @return 结果码
	 * @throws JSONException
	 */
	public int getResultCode() throws JSONException;

	/**
	 * 当isSuccessed()返回的结果为false,则可以调用这个方法来得到出错的结果
	 * 
	 * @return String 出错的结果
	 * @throws JSONException
	 */
	public String getErrorMessage() throws JSONException;

	/**
	 * 当isSuccessed()返回的结果为false,则可以调用这个方法来得到出错码
	 * 
	 * @return String 出错码
	 * @throws JSONException
	 */
	public String getErrorCode() throws JSONException;
}
