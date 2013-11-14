package com.jamesXu.asynchttprequest.parser;

import org.json.JSONException;
import org.json.JSONObject;

import com.jamesXu.asynchttprequest.Journal;
import com.jamesXu.asynchttprequest.net.API;

/**
 * 
 * @author MobileXu
 *
 */
public class GetJournalParser implements IParser<JSONObject, Journal>{
	
	private final String KEY_ID= "id";
	private final String KEY_JOURNAL= "journal";
	private final String KEY_YEAR= "year";
	private final String KEY_MONTH= "mouth";
	private final String KEY_COVERPICTURE= "coverPicture";
	private final String KEY_RELEASETIME= "releaseTime";
	@Override
	public Journal parse(JSONObject parserData){
		if( ! parserData.keys().hasNext()) {
			return new Journal();
		}
		try {
			Journal entity = new Journal();
			entity.setId(parserData.getInt(KEY_ID));
			entity.setCoverPicture(API.HTTPHOST+parserData.getString(KEY_COVERPICTURE));
			entity.setJournal(parserData.getInt(KEY_JOURNAL));
			entity.setMonth(parserData.getInt(KEY_MONTH));
			entity.setReleaseTime(parserData.getString(KEY_RELEASETIME));
			entity.setYear(parserData.getInt(KEY_YEAR));
			return entity;
		} catch (JSONException e) {
			e.printStackTrace();
			return new Journal();
		}
	}
}
