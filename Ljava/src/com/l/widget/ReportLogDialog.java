package com.l.widget;

import com.l.activity.LActivityManager;
import com.l.utils.CheckUtils;
import com.library.ljava.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
public class ReportLogDialog extends Dialog {
	private static ReportLogDialog rDialog;
	private static TextView logInfo;
	private static Button exitBt;
	private ReportLogDialog(Context context) {
		super(context, R.style.Lreport_Dialog);
	}

	public static ReportLogDialog getInstrance(Context context) {
		if (CheckUtils.isNullOrEmpty(rDialog)) {
			rDialog = new ReportLogDialog(context);
			rDialog.setContentView(R.layout.report_dialog_layout);
			rDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
			logInfo = (TextView) rDialog.findViewById(R.id.logInfo);
			exitBt = (Button) rDialog.findViewById(R.id.report_exit_bt);
			WindowManager m = LActivityManager.getInstance()
					.getCurrentActivity().getWindowManager();
			Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
			WindowManager.LayoutParams p = rDialog.getWindow().getAttributes(); // 获取对话框当前的参数值
			p.height = (int) (d.getHeight() * 0.85); // 高度设置为屏幕的0.6
			p.width = (int) (d.getWidth() * 0.85); // 宽度设置为屏幕的0.65
			rDialog.getWindow().setAttributes(p);
			exitBt.setOnClickListener(new android.view.View.OnClickListener() {
				@Override
				public void onClick(View view) {
					LActivityManager.getInstance().AppExit();
				}
			});
		}
		return rDialog;
	}

	public void setLogInfo(String info) {
		if (!CheckUtils.isNullOrEmpty(logInfo)) {
			logInfo.setText(info);
		}
	}
}
