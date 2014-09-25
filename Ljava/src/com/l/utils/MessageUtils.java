package com.l.utils;

import com.l.activity.LApplication;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import static com.l.constant.PromptConstant.*;
public class MessageUtils {

	private volatile static MessageUtils instance = null;
	private static Toast mTextToast;
	private static Toast mImgToast;
	private static Handler mHandler = new Handler(Looper.getMainLooper());
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
		mTextToast = new Toast(mContext);
		mImgToast = new Toast(mContext);
		mTextToast.setGravity(Gravity.CENTER, 0, 0);
		mImgToast.setGravity(Gravity.CENTER, 0, 0);
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

	private void showMessage(final String msg, final int len) {
		if (CheckUtils.isNullOrEmpty(mTextToast)) {
			LogUtils.getInstance().e("Toast" + ERROR);
		} else {
			new Thread(new Runnable() {
				public void run() {
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							synchronized (LApplication.appContext) {
								if (mTextToast != null) {
									mTextToast.cancel();
								} else {
									mTextToast.setText(msg);
									mTextToast.setDuration(len);
								}
								mTextToast.show();
							}
						}
					});
				}
			}).start();
		}
	}

	private Dialog alertDialog;
	private Builder aBuilder;

	public void showCollapseDialog(String message) {
		if (CheckUtils.isNullOrEmpty(aBuilder)) {
			aBuilder = new AlertDialog.Builder(mContext).setTitle(
					INFORMATION_ERROR).setPositiveButton(CANCEL,
					new OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							alertDialog.dismiss();
						}
					});
		}
		aBuilder.setMessage(message);
		alertDialog = aBuilder.create();
		alertDialog.setCanceledOnTouchOutside(false);
		alertDialog.getWindow().setType(
				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		alertDialog.show();
	}
}
