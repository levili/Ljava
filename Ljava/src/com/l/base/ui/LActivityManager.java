package com.l.base.ui;

import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

/**
 * @ClassName: ActivityManager
 * @Description: Activity管理者
 * @author Levi
 * @date 2014-9-15
 */
public class LActivityManager {
	private volatile static LActivityManager instance = null;
	private static Stack<Activity> activityStack;

	public static LActivityManager getInstance() {
		if (instance == null) {
			synchronized (LActivityManager.class) {
				if (instance == null) {
					instance = new LActivityManager();
				}
			}
		}
		return instance;
	}
	private LActivityManager() {
	}

	/**
	 * @Description: 添加一个Activity
	 */
	public void intoAStack(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * @Description:获取最后一个Activity
	 */
	public Activity getCurrentActivity() {
		if (activityStack == null || activityStack.isEmpty()) {
			return null;
		}
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * @Description: 获取Activity
	 */
	public Activity findActivity(Class<?> cls) {
		Activity activity = null;
		for (Activity aty : activityStack) {
			if (aty.getClass().equals(cls)) {
				activity = aty;
				break;
			}
		}
		return activity;
	}

	/**
	 * @Description: 重载删除Activity
	 */
	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * @Description: 重载删除Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * @Description: 重载删除Activity
	 */
	public void finishActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * @Description: 重载删除Activity
	 */
	public void finishOthersActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (!(activity.getClass().equals(cls))) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * @Description: 重载删除Activity
	 */
	private void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * @Description:退出应用
	 */
	public void AppExit() {
		try {
			finishAllActivity();
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(1);
		} catch (Exception e) {
			System.exit(0);
		}
	}
}
