package com.l.utils;

import com.l.base.ui.LActivityManager;
import com.l.base.ui.LApplication;
import com.l.widget.ReportLogDialog;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import static com.l.constants.PromptConstant.*;

public class MessageUtils {

	private volatile static MessageUtils instance = null;
	private static Toast mTextToast;
	private static Toast mImgToast;
	private static Context mContext;

	public static MessageUtils getInstance() {
		if (instance == null) {
			synchronized (MessageUtils.class) {
				if (instance == null) {
					instance = new MessageUtils();
				}
			}
		}
		return instance;
	}

	private MessageUtils() {
		mContext = LApplication.appContext;

	}

	public void toastLong(int resid) {
		String toastStr = mContext.getResources().getString(resid);
		showMessage(toastStr, Toast.LENGTH_LONG);
	}

	public void toastShort(int resid) {
		String toastStr = mContext.getResources().getString(resid);
		showMessage(toastStr, Toast.LENGTH_SHORT);
	}

	public void toastLong(String info) {
		showMessage(info, Toast.LENGTH_LONG);
	}

	public void toastShort(String info) {
		showMessage(info, Toast.LENGTH_SHORT);
	}

	public void toastImage(Context context, int ImageResourceId,
			CharSequence text, int duration) {
		mImgToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
		mImgToast.setGravity(Gravity.CENTER, 0, 0);
		View toastView = mImgToast.getView();
		ImageView img = new ImageView(context);
		img.setImageResource(ImageResourceId);
		LinearLayout ll = new LinearLayout(context);
		ll.addView(img);
		ll.addView(toastView);
		mImgToast.setView(ll);
		mImgToast.show();
	}

	private void showMessage(String msg, int len) {
		if (!CheckUtils.isNullOrEmpty(mTextToast)) {
			mTextToast.cancel();
		}
		mTextToast = Toast.makeText(LActivityManager.getInstance()
				.getCurrentActivity(), msg, len);
		mTextToast.show();
	}

	public void showCollapseDialog(String message) {
		ReportLogDialog instrance = ReportLogDialog
				.getInstrance(LApplication.appContext);
		instrance.setCanceledOnTouchOutside(false);
		instrance.getWindow().setType(
				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		instrance.setLogInfo(message);
		instrance.show();
	}
}
