package com.jamesXu.asynchttprequest.tools;

import java.util.List;
import java.util.Map;

/**
 * 集合 工具类
 * 
 * @author lwz
 *
 */
public class CollectionsUtil {

	/**
	 * 判断 list 是否为 null 或   size = 0
	 * @param list
	 * @return 若为 null 或 size =0 返回 true, 否则返回 false
	 */
	public static boolean isNullOrEmpty( List<?> list ) {
		return list == null || list.isEmpty();
	}
	
	/**
	 * 判断 map 是否为 null 或   size = 0
	 * @param map
	 * @return 若为 null 或 size =0 返回 true, 否则返回 false
	 */
	public static boolean isNullOrEmpty( Map<?, ?> map ) {
		return map == null || map.isEmpty();
	}
}
