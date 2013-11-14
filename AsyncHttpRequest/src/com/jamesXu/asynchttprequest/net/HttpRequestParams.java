package com.jamesXu.asynchttprequest.net;

import java.util.HashMap;

import com.jamesXu.asynchttprequest.tools.JsonUtil;

/**
 * 
 * @author MobileXu
 *
 */
public class HttpRequestParams extends HashMap<String, Object> {

	private static final long serialVersionUID = -3736319677853216129L;

	@Override
	public String toString() {
		return JsonUtil.map2JsonObj(this);
	}
}
