package com.l.base.ui;

import com.l.base.ui.LBaseFragment.FragmentCallBack;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;

public abstract class LBaseFragmentActivity extends FragmentActivity implements
		FragmentCallBack {
	private FragmentManager mManager;
	protected LActivityManager mAManager;

	protected LFragmentManager mFragmentMg;

	protected abstract Integer getActivityLayoutRes();

	protected abstract Integer getFragmentLayoutId();

	protected abstract void onFirstFragment(LFragmentManager mg);


	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(getActivityLayoutRes());
		mManager = getSupportFragmentManager();
		mFragmentMg = LFragmentManager.getInstance(mManager,
				getFragmentLayoutId());
		mAManager = LActivityManager.getInstance();
		mAManager.intoAStack(this);
		onFirstFragment(mFragmentMg);
	}

	@Override
	protected void onDestroy() {
		mAManager.finishActivity(this);
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			mFragmentMg.notifyCurrentALastFm();
		}
		return super.onKeyDown(keyCode, event);
	}
}
