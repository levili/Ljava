package com.l.activity;

import com.l.activity.LBaseFragment.FragmentCallBack;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public abstract class LBaseFragmentActivity extends FragmentActivity implements
		FragmentCallBack {
	private FragmentManager fmManager;

	protected LActivityManager mAManager;

	protected abstract Integer getFragmentLayoutRes();

	protected LFragmentManager mFragmentMg;

	@Override
	protected void onCreate(Bundle bundle) {
		fmManager = getSupportFragmentManager();
		mFragmentMg = LFragmentManager.getInstance(fmManager,
				getFragmentLayoutRes());
		mAManager = LActivityManager.getInstance();
		mAManager.intoAStack(this);
		super.onCreate(bundle);
	}

	@Override
	protected void onDestroy() {
		mAManager.finishActivity(this);
		super.onDestroy();
	}
}
