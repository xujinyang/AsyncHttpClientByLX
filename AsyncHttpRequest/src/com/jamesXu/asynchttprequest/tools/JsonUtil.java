package com.jamesXu.asynchttprequest.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
	
	/**
	 * �?jsonObj 转换�?Map 
	 * <p> 前提�?�?jsonObj 中的�?值全部都会以相同的配对方式存储到 Map 中，不�?合要跳过的某些键值的解析</p>
	 * @param jsonObj
	 * @return
	 * @throws JSONException
	 */
	public static Map<String, String> jsonObj2Map(JSONObject jsonObj) throws JSONException {
		@SuppressWarnings("unchecked")
		Iterator<String> it = jsonObj.keys();
		Map<String, String> map = new HashMap<String, String>();
		while ( it.hasNext() ) {
			String key = it.next();
			map.put(key, jsonObj.getString(key));
		}
		return map;
	}
	
	/**
	 * �?jsonArr 转化�?List<Map<String, String>>
	 * <p>前提�?�?jsonObj 中的�?值全部都会以相同的配对方式存储到 Map 中，不�?合要跳过的某些键值的解析</p>
	 * @param jsonArr
	 * @return
	 * @throws JSONException
	 */
	public static List<Map<String, String>> jsonArr2ListMap( JSONArray jsonArr ) throws JSONException {
		int size = jsonArr.length();
		List<Map<String, String>> data = new ArrayList<Map<String,String>>();
		for( int i=0; i<size; i++ ) {
			data.add( jsonObj2Map( jsonArr.getJSONObject(i)));
		}
		
		return data;
	}
	
	/**
	 * �?Map<String, Obejct> 转换�?JsonObject
	 * @param map
	 * @return
	 */
	public static String map2JsonObj( Map<String, Object> map) {
		return new JSONObject(map).toString();
	}
	
	/**
	 * �?List<Map<String, Object>> 转换�?JsonArray
	 * @param list
	 * @return
	 */
	public static String listMap2JsonArr( List<Map<String, Object>> list) {
		StringBuffer sb = new StringBuffer("[");
		int size = list.size();
		for( int i=0; i<size; i++ ) {
			sb.append(map2JsonObj(list.get(i)));
			if( i == size - 1 ) {
				continue;
			}
			sb.append(",");
		}
		return sb.append("]").toString();
	}
	
	/**
	 * �?JSONArray 转化 �?List&ltString&gt
	 * @param jsonArr
	 * @return List&ltString&gt
	 * @throws JSONException
	 */
	public static List<String> JsonArr2List(JSONArray jsonArr) throws JSONException {
		List<String> data = new ArrayList<String>();
		int count = jsonArr.length();
		for( int i=0; i<count; i++ ) {
			data.add( jsonArr.getString(i) );
		}
		return data;
	}
	
}
