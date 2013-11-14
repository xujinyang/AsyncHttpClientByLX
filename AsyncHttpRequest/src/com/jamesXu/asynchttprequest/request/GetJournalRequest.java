package com.jamesXu.asynchttprequest.request;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.jamesXu.asynchttprequest.Journal;
import com.jamesXu.asynchttprequest.net.API;
import com.jamesXu.asynchttprequest.parser.GetJournalParser;
import com.jamesXu.asynchttprequest.tools.CollectionsUtil;

/**
 * 
 * @author MobileXu
 * 
 */
public class GetJournalRequest extends
		AbsAsyncHttpRequest<JSONArray, List<Journal>> {

	public static final String KEY_PARAM_JOURNALS = "Journals";

	public GetJournalRequest(Context context) {
		super(context);
	}

	@Override
	public String getAPI() {
		return API.GETJOURNAL_URL;
	}

	@Override
	public HttpMethod getHttpMethod() {
		return HttpMethod.GET;
	}

	@Override
	protected List<Journal> parseEntity(String parseData) throws JSONException {
		List<Journal> data = new ArrayList<Journal>();
		JSONObject jsonObject = new JSONObject(parseData);
		JSONArray jsonArray = jsonObject.getJSONArray(KEY_PARAM_JOURNALS);
		GetJournalParser getJournalParser = new GetJournalParser();
		for (int i = 0; i < jsonArray.length(); i++) {
			data.add(getJournalParser.parse(jsonArray.getJSONObject(i)));
		}
		return CollectionsUtil.isNullOrEmpty(data) ? new ArrayList<Journal>()
				: data;
	}
}
