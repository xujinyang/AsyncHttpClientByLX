package com.jamesXu.asynchttprequest.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.jamesXu.asynchttprequest.AppContext;
/**
 * 
 * @author Jinyang
 *
 */
public class SharedPreferecesUtil {
	
	/**
	 * 得到SharedPreferences对象
	 * @param context		当前对象
	 * @param sharedName	保存SharedPreferences的名字, 不可以为NULL
	 * @return				返回一个SharedPreferences对象
	 */
	public static SharedPreferences newSharedPreferences(Context context, String sharedName) {
		if(context==null){
			AppContext.showLog("SharedPreferecesUtil里面context为空"+sharedName);
		}
		return context.getSharedPreferences(sharedName, Context.MODE_PRIVATE);
	}
	
	/**
	 * 缓存数据到SharedPrefereces
	 * @param context		当前对象
	 * @param sharedName	保存SharedPreferences的名字, 不可以为NULL
	 * @param valueKey		保存数据的key
	 * @param value			保存的数据
	 */
	public static void putString(Context context, String sharedName, 
			String valueKey, String value) {
		
		Editor dEditor = newSharedPreferences(context, sharedName).edit();
		// 判断数据类型
		dEditor.putString(valueKey, value.toString());
		dEditor.commit();		
	}
	
	/**
	 * 得到保存到SharedPrefereces中的缓存数据
	 * @param context		当前对象
	 * @param sharedName	保存SharedPreferences的名字, 不可以为NULL
	 * @param valueKey		保存数据的key
	 * @return
	 */
	public static String getString(Context context, String sharedName, 
			String valueKey) {
		return newSharedPreferences(context, sharedName).getString(valueKey, null);
	}
	
	/**
	 * 移除SharedPrefereces中的缓存数据
	 * @param context
	 * @param sharedName
	 * @param valueKey
	 */
	public static void removeDataSharedPreferences(Context context, String sharedName, 
			String valueKey) {
		newSharedPreferences(context, sharedName).edit().remove(valueKey);
	}
	
	/**
	 * 清除SharedPreference中文件名为SharedName的数据
	 * @param context
	 * @param sharedName
	 */
	public static void clearSharedPreferences(Context context ,String sharedName){
		newSharedPreferences(context, sharedName).edit().clear().commit();
	}

}
