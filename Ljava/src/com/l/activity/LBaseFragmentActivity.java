package com.l.activity;

import com.l.activity.LBaseFragment.FragmentCallBack;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;

public abstract class LBaseFragmentActivity extends FragmentActivity implements
		FragmentCallBack {
	private FragmentManager mManager;
	protected LActivityManager mAManager;

	protected abstract Integer getActivityLayoutRes();

	protected abstract Integer getFragmentLayoutId();

	protected LFragmentManager mFragmentMg;

	@Override
	protected void onCreate(Bundle bundle) {
		mManager = getSupportFragmentManager();
		mFragmentMg = LFragmentManager.getInstance(mManager,
				getFragmentLayoutId());
		mAManager = LActivityManager.getInstance();
		mAManager.intoAStack(this);
		setContentView(getActivityLayoutRes());
		super.onCreate(bundle);
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
