package com.l.activity;

import android.view.View;
import android.view.View.OnClickListener;

import com.l.utils.MessageUtils;
import com.library.ljava.R;

public class TextFragment extends LBaseFragment {
	public static final int TEXT_FRAGMENTID = 1103;

	@Override
	protected Object getResourcesOrView() {
		return R.layout.main_fragment;
	}

	@Override
	protected Integer getFragmentId() {
		return TEXT_FRAGMENTID;
	}

	private String aa = null;
	private int u;

	@Override
	protected void initFargment(View mianView) {
		View findViewById = mianView.findViewById(R.id.myTextView);
		findViewById.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				u = Integer.parseInt(aa) + 1;
			}
		});
	}

}
