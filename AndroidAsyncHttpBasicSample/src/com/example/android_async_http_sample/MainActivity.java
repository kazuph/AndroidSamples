package com.example.android_async_http_sample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android_async_http_sample_basic.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

// Activity起動時にgoogle.comのhtmlをログに主力するだけのサンプル
public class MainActivity extends Activity {

	TextView textView;
	ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.hello_world);
		progressDialog = new ProgressDialog(this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage("now loading...");
		progressDialog.setCancelable(false);

		// その場で定義して使ってみる
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://www.google.com", new AsyncHttpResponseHandler() {
			@Override
			public void onStart() {
				progressDialog.show();
			}

			@Override
			public void onSuccess(String response) {
				textView.setText(response);
				Log.d("@@@@ success by nomal", response);
			}

			@Override
			public void onFailure(Throwable error, String response) {
				Log.d("@@@@ failure by nomal", response);
			}

			@Override
			public void onFinish() {
				progressDialog.dismiss();
			}
		});

		// 別クラスをつくってそれを読み込む場合
		// 注意点として、シングルトンで同じインスタンスを使いまわす時に、
		// 非同期通信が終わるまでの間に再度リクエスト処理を走らせてはいけない
		// 必ず別プロセスにするべき（上とは別インスタンスなので、同時に通信を行っていても問題ない）
		GoogleHTML.get(new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				Log.d("@@@@ success by google html class", response);
			}

			@Override
			public void onFailure(Throwable error, String response) {
				Log.d("@@@@ failure by google html class", response);
			}

		});
	}

}
