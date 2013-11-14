package com.jamesXu.asynchttprequest.parser;


/**
 * 抽象响应解析器
 * 
 * <p>该类主要用于响应结果为 JSON 的网络请求接口</p>
 * 
 * @author lwz
 *
 * @param <T> 	响应结果类型
 * @param <E>	响应结果中要提取出的数据类型
 */
public abstract class AbsResponseParser<T, E> implements IResponseParser<E> {
	
	protected T responseData;
	
	protected AbsResponseParser(T responseData) {
		this.responseData = responseData;
	}
}
