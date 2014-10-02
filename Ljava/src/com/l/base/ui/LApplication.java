package com.l.base.ui;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.RequestQueue;
import com.l.handler.CrashHandler;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

@SuppressLint("UseSparseArrays")
public class LApplication extends Application {
	public static Context appContext;
	public static Handler appHandler;
	public static boolean isCollapse;
	public static Map<Integer, RequestQueue> queueSet;

	@Override
	public void onCreate() {
		appContext = this;
		appHandler = new Handler(Looper.getMainLooper());
		isCollapse = false;
		queueSet = new HashMap<Integer, RequestQueue>();
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(this);
		super.onCreate();
	}
}
