package com.l.activity;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import android.R.bool;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * @ClassName: ActivityManager
 * @Description: Activity管理者
 * @author Levi
 * @date 2014-9-15
 */
public class LFragmentManager {
	private volatile static LFragmentManager instance = null;
	private static HashMap<Integer, LBaseFragment> fragmentMap;
	private static FragmentManager mfmManager;
	private static int mlayoutId;
	private FragmentTransaction mTransaction;

	public static LFragmentManager getInstance(FragmentManager fmManager,
			int layoutId) {
		mfmManager = fmManager;
		mlayoutId = layoutId;
		if (instance == null) {
			synchronized (LFragmentManager.class) {
				if (instance == null) {
					instance = new LFragmentManager();
				}
			}
		}
		return instance;
	}

	private LFragmentManager() {
	}

	/**
	 * @Description: 添加一个Fragment
	 */
	public void intoAStack(LBaseFragment fragment) {
		if (fragmentMap == null) {
			fragmentMap = new HashMap<Integer, LBaseFragment>();
		}
		fragmentMap.put(fragment.getFragmentId(), fragment);
	}

	/**
	 * @Description: 获取Fragment
	 */
	public LBaseFragment findFragment(Integer fragmentId) {
		LBaseFragment findfm = null;
		try {
			findfm = fragmentMap.get(fragmentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return findfm;
	}

	/**
	 * @Description: 删除栈以及Map中的Fragment
	 */
	public void removeFragment(Integer fragmentId) {
		try {
			mTransaction = mfmManager.beginTransaction();
			mTransaction.remove(findFragment(fragmentId));
			mTransaction.commitAllowingStateLoss();
			removeFragmentToMap(fragmentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 删除FragmentMap中的Fragment;
	 */
	private void removeFragmentToMap(Integer fragmentId) {
		try {
			fragmentMap.remove(fragmentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 删除Fragment栈中的所有Fragment
	 */
	public void removeAllFragment(Integer fragmentId) {
		try {
			mTransaction = mfmManager.beginTransaction();
			Set<Integer> keySet = fragmentMap.keySet();
			for (Integer key : keySet) {
				mTransaction.remove(findFragment(key));
			}
			mTransaction.commitAllowingStateLoss();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 切换当前fargment，如果当前fargment则返回false
	 */
	private LBaseFragment currentFm;

	@SuppressWarnings("unused")
	private boolean refreshFragment(
			Class<? extends LBaseFragment> targetFramentClass, Bundle bundle,
			boolean isStack, int animRes) {
		if (currentFm != null && currentFm.getClass() == targetFramentClass) {
			return false;// 如果相同直接方法false，不刷新
		} else {
			try {
				mTransaction = mfmManager.beginTransaction();
				currentFm = targetFramentClass.newInstance();
				if (null != bundle) {
					currentFm.setArguments(bundle);
				}
				mTransaction.replace(mlayoutId, currentFm);
				if (isStack) {
					mTransaction.addToBackStack(null);
				}
				if (animRes != 0) {
					mTransaction.setTransition(animRes);
				}
				mTransaction.commitAllowingStateLoss();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}
}
