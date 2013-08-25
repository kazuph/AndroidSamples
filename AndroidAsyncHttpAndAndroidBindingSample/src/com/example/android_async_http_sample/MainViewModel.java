package com.example.android_async_http_sample;

import gueei.binding.Command;
import gueei.binding.labs.EventAggregator;
import gueei.binding.observables.StringObservable;
import gueei.binding.validation.validators.Required;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MainViewModel {

	public StringObservable Input = new StringObservable();

	EventAggregator eventAggregator;

	public void setEventAggregator(EventAggregator ea) {
		this.eventAggregator = ea;
	}

	public MainViewModel() {
		Input.set("カラだよ");
	}

	public Command onClickButton = new Command() {
		@Override
		public void Invoke(View arg0, Object... arg1) {
			eventAggregator.publish("progress", null, null);
			loadHtml();
		}
	};

	public void loadHtml() {

		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://www.google.com", new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				Log.d("@@@@ by nomal", response);
				Bundle data = new Bundle();
				data.putString("message", "success");
				Input.set(response);
				eventAggregator.publish("toast", MainViewModel.this, data);
			}

			@Override
			public void onFailure(Throwable error, String response) {
				Log.d("@@@@ by nomal", response);
				Bundle data = new Bundle();
				data.putString("message", "failure");
				eventAggregator.publish("toast", MainViewModel.this, data);
				Input.set("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNGGGGGGGGGGGGGGGGGGGGGGGGGGGGGNG");
			}
		});
	}
}
