package com.example.android_async_http_sample;

import gueei.binding.Binder;
import android.app.Application;

public class MainApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Binder.init(this);
	}

}
