package com.l.utils;

import com.l.base.ui.LActivityManager;

import android.content.Context;
import android.util.DisplayMetrics;

public class WindowUtils {
	private volatile static WindowUtils instance = null;

	/** @Description: 单例的实现 */
	public static WindowUtils getInstance() {
		if (instance == null) {
			synchronized (WindowUtils.class) {
				if (instance == null) {
					instance = new WindowUtils();
				}
			}
		}
		return instance;
	}

	private DisplayMetrics metric;

	private WindowUtils() {
		metric = new DisplayMetrics();
		if (null != LActivityManager.getInstance().getCurrentActivity()) {
			LActivityManager.getInstance().getCurrentActivity()
					.getWindowManager().getDefaultDisplay().getMetrics(metric);
		} else {
			try {
				LogUtils.getInstance().e("WindowUtils CurrentActivity not");
				throw new Exception("WindowUtils CurrentActivity not");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/** @Description: 获取屏幕宽度 */
	public int getWindowWidth() {
		return metric.widthPixels;
	}

	/** @Description: 获取屏幕高度 */
	public int getWindowHeight() {
		return metric.heightPixels;
	}

	/** @Description: 根据手机的分辨率从 dp 的单位 转成为 px(像素) */
	public int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/** @Description: 根据手机的分辨率从 px(像素) 的单位 转成为 dp */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
