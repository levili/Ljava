package com.l.activity;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class LApplication extends Application {
	public static Context appContext;
	public static Handler appHandler;

	@Override
	public void onCreate() {
		appContext = this;
		appHandler = new Handler(Looper.getMainLooper());
		LCrashHandler crashHandler = LCrashHandler.getInstance();
		crashHandler.init(getApplicationContext());
		super.onCreate();
	}

}
