package com.l.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public abstract class LBaseFragment extends Fragment {
	protected Context context;
	protected LayoutInflater inflater;
	protected ViewGroup container;
	protected View rootview;
	protected LBaseFragmentActivity mActivity;

	protected abstract Object getResourcesOrView();

	protected abstract Integer getFragmentId();

	protected abstract void initFargment(View mianView);

	protected FragmentCallBack mCallBack;

	public interface FragmentCallBack {
		public void fCallBack(Integer fragmentId, String data);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		this.container = container;
		if (getResourcesOrView() instanceof Integer) {
			int layoutId = (Integer) getResourcesOrView();
			rootview = inflater.inflate(layoutId, container, false);
		} else if (getResourcesOrView() instanceof View) {
			rootview = (View) getResourcesOrView();
		}
		ViewGroup parent = (ViewGroup) rootview.getParent();
		if (parent != null) {
			parent.removeView(rootview);
		}
		initFargment(rootview);
		return rootview;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = (LBaseFragmentActivity) activity;
		mCallBack = (FragmentCallBack) activity;
		context = activity;
	}
}
