package com.example.android_async_http_sample;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class GoogleHTML {
	private static final String BASE_URL = "http://www.google.com";
	private static AsyncHttpClient client = new AsyncHttpClient();

	public static void get(AsyncHttpResponseHandler responseHandler) {
		Log.d("@@@ get in google html", "!!!!!!!!");
		client.get(BASE_URL, responseHandler);
	}

}
