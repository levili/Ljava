package com.l.activity;

import android.app.Activity;

import com.l.utils.LogUtils;
import com.library.ljava.R;

/**
 * @Title: TextAvtivity.java
 * @Description: 用于调试
 * @author Levi
 * @date 2014-9-25 上午11:04:05
 */
public class TextAvtivity extends LBaseFragmentActivity {

	@Override
	public void fCallBack(Integer fragmentId, String data) {
		switch (fragmentId) {
		case TextFragment.TEXT_FRAGMENTID:
			LogUtils.getInstance().e("我是textfragment");
			break;
		}
	}

	@Override
	protected Integer getFragmentLayoutId() {
		return R.id.main_fragment_layout;
	}

	@Override
	protected Integer getActivityLayoutRes() {
		return R.layout.main;
	}

	@Override
	protected void onFirstFragment(LFragmentManager mg) {
		mg.refreshFragment(TextFragment.class, null, true, 0);
	}

}
