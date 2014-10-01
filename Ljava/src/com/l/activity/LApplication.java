package com.l.activity;

import com.l.handler.CrashHandler;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;


public class LApplication extends Application {
	public static Context appContext;
	public static Handler appHandler;
	public static boolean isCollapse;

	@Override
	public void onCreate() {
		appContext = this;
		appHandler = new Handler(Looper.getMainLooper());
		isCollapse = false;
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(this);
		super.onCreate();
	}
}
