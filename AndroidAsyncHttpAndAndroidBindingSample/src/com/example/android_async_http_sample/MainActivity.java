package com.example.android_async_http_sample;

import gueei.binding.app.BindingActivity;
import gueei.binding.labs.EventAggregator;
import gueei.binding.labs.EventSubscriber;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

// Activity起動時にgoogle.comのhtmlをログに主力するだけのサンプル
public class MainActivity extends BindingActivity {

	ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// ModelとAggregatorの結びつけ
		EventAggregator eventAggregator = EventAggregator.getInstance(this);
		MainViewModel viewModel = new MainViewModel();
		viewModel.setEventAggregator(eventAggregator);

		// ViewModelとViewの結びつけ
		setAndBindRootView(R.layout.activity_main, viewModel);

		// 購読処理
		eventAggregator.subscribe("toast", new EventSubscriber() {
			@Override
			public void onEventTriggered(String arg0, Object arg1, Bundle arg2)
			{
				progressDialog.dismiss();
				Toast.makeText(MainActivity.this, arg2.getString("message"), Toast.LENGTH_LONG).show();
			}
		});

		// プログレスダイアログの準備
		progressDialog = new ProgressDialog(this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage("now loading...");
		progressDialog.setCancelable(false);
		eventAggregator.subscribe("progress", new EventSubscriber() {
			@Override
			public void onEventTriggered(String arg0, Object arg1, Bundle arg2)
			{
				progressDialog.show();
			}
		});

	}

}
