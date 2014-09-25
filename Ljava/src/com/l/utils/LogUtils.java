package com.l.utils;


import android.util.Log;

public class LogUtils {
	private volatile static LogUtils instance = null;
	private String authorName = "Levi";
	private final String delimiter = "--: ";
	private final String authorNameColon = authorName + delimiter;

	public static LogUtils getInstance() {
		if (instance == null) {
			synchronized (LogUtils.class) {
				if (instance == null) {
					instance = new LogUtils();
				}
			}
		}
		return instance;
	}

	private LogUtils() {
		super();
	}

	public void setLogAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public void i(Object str) {
		Log.i(authorNameColon + getCallerTag(), str.toString());
	}

	public void d(Object str) {
		Log.e(authorNameColon + getCallerTag(), str.toString());
	}

	public void w(Object str) {
		Log.w(authorNameColon + getCallerTag(), str.toString());
	}

	public void e(Object str) {
		Log.e(authorNameColon + getCallerTag(), str.toString());
	}

	public String getCallerTag() {
		StackTraceElement[] stacks = (new Throwable()).getStackTrace();
		StackTraceElement ste = stacks[2];
		String className = ste.getClassName();
		String classTag = ste.getClassName().substring(
				className.lastIndexOf('.') + 1);
		int lineNumber = ste.getLineNumber();
		return classTag + "/" + ste.getMethodName() + delimiter + "/"
				+ lineNumber;
	}
}
