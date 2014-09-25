package com.l.activity;

import com.library.ljava.R;

public class TextFragment extends LBaseFragment{
	public static  final int TEXT_FRAGMENTID = 1103;
	@Override
	protected Object getResourcesOrView() {
		return R.layout.main_fragment;
	}
	@Override
	protected Integer getFragmentId() {
		return TEXT_FRAGMENTID;
	}

}
