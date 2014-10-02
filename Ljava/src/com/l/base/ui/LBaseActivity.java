package com.l.base.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

/**
 * @ClassName: LBaseActivity
 * @Description: Activity基类
 * @author Levi
 * @date 2014-9-16
 */
public abstract class LBaseActivity extends Activity {
	protected LActivityManager mAManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mAManager = LActivityManager.getInstance();
		mAManager.intoAStack(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		mAManager.finishActivity(this);
		super.onDestroy();
	}
}