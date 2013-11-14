package com.jamesXu.asynchttprequest;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.jamesXu.asynchttprequest.net.ResponseParseHandler;
import com.jamesXu.asynchttprequest.request.GetJournalRequest;

/**
 * AsyncHttpRequest Demo
 * @author Jinyang
 *
 */
public class MainActivity extends Activity {
	private TextView tv_showData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_showData =(TextView) findViewById(R.id.tv_showData);
		getWebData();
	}

	private void getWebData() {
		GetJournalRequest getJournalRequest = new GetJournalRequest(
				getApplicationContext());
		getJournalRequest.request(new ResponseParseHandler<List<Journal>>() {
					@Override
					public void onStart() {
						//mProgressBar.setVisibility(View.VISIBLE);
					}

					@Override
					public void onSuccess(List<Journal> result) {
//						mProgressBar.setVisibility(View.GONE);
//						mJournalAL.clear();
//						mJournalAL.addAll(result);
//						mAdapter.notifyDataSetChanged();
						String str="";
						for (Journal journal : result) {
							str= str+journal.toString()+"/n";
						}
						tv_showData.setText(str);
					}

					@Override
					public void onFailure(String error, String message) {
						AppContext.showToast(message + "--" + error);
					}
				});
		
	}
}
