package com.jamesXu.asynchttprequest.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 解析工具类
 * 
 * @author lwz
 *
 */
public class ParserUtil {
	
	private ParserUtil(){
	};
	
	/**
	 * 通过 jsonArr 得到 List对象
	 * @param jsonArr		待解析的JSON 数组对象
	 * @param subParser		JSON 数组中的 JSONObject 对象的解析器
	 * @return
	 * @throws JSONException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <K>List<K> getListData(JSONArray jsonArr, IParser subParser) throws JSONException {
		List<K> data = new ArrayList<K>();
		int count = jsonArr.length();
		for( int i=0; i<count; i++ ) {
			data.add( (K)subParser.parse(jsonArr.getJSONObject(i)) );
		}
		return data;
	}
	
	@SuppressWarnings("rawtypes")
	public static JSONArray getJSONArray( List list, IReverseParser parser ) throws JSONException {
		
		int count = list.size();
		JSONArray jsonArray = new JSONArray();
		for( int i=0; i<count; i++ ) {
			jsonArray.put( getJSONObject( list.get(i),  parser ) );
		}
		return jsonArray;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static JSONObject getJSONObject( Object entity, IReverseParser parser ) throws JSONException {
		return (JSONObject) parser.reverseParse( entity );
	}
}
