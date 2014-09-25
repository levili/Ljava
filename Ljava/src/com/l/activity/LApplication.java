package com.l.activity;

import java.lang.Thread.UncaughtExceptionHandler;

import com.l.utils.MessageUtils;


import android.app.Application;
import android.content.Context;

public class LApplication extends Application {
	public static Context appContext;
	@Override
	public void onCreate() {
		appContext = this;
		super.onCreate();
	}
	@SuppressWarnings("unused")
	private UncaughtExceptionHandler uncaughtExceptionHandler = new UncaughtExceptionHandler() {
		@SuppressWarnings("static-access")
		@Override
		public void uncaughtException(Thread thread, Throwable ex) {
			String info = ex.getLocalizedMessage();
			MessageUtils.getInstance().showCollapseDialog(info);
		}
	};
}
