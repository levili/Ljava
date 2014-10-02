package com.l.base.adapter;

import java.util.ArrayList;
import java.util.List;

import com.l.utils.LogUtils;

import android.accounts.OnAccountsUpdateListener;
import android.content.Context;
import android.database.DataSetObserver;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

public abstract class LBaseAdapter<T> extends BaseAdapter {
	protected Context context;
	protected List<T> data;
	protected Double proportion;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public LBaseAdapter(Context context, List<T> data, Double proportion) {
		this.context = context;
		this.data = data == null ? new ArrayList<T>() : new ArrayList<T>(data);
		this.proportion = proportion;
	}

	@Override
	public int getCount() {
		if (data == null)
			return 0;
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		if (position >= data.size())
			return null;
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 该方法需要子类实现，需要返回item布局的resource id
	 * 
	 * @return
	 */
	public abstract Object getItemResource();

	/**
	 * 使用该getItemView方法替换原来的getView方法，需要子类实现
	 */
	public abstract View getItemView(int position, View convertView,
			ViewHolder holder);

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (null == convertView) {
			if (getItemResource() instanceof Integer) {
				convertView = View.inflate(context,
						(Integer) getItemResource(), null);
			} else if (getItemResource() instanceof View) {
				convertView = (View) getItemResource();
			} else {
				try {
					LogUtils.getInstance().e("BaseAdapter itemRes error");
					throw new Exception("BaseAdapter itemRes error");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		View itemView = getItemView(position, convertView, holder);
		setItemParam(itemView);
		return itemView;
	}

	private void setItemParam(View v) {
		int w = v.getLayoutParams().width;
		int h = (int) (w * proportion);
		v.getLayoutParams().height = h;
	}

	public class ViewHolder {
		private SparseArray<View> views = new SparseArray<View>();
		private View convertView;

		public ViewHolder(View convertView) {
			this.convertView = convertView;
		}

		@SuppressWarnings("unchecked")
		public <T extends View> T getView(int resId) {
			View v = views.get(resId);
			if (null == v) {
				v = convertView.findViewById(resId);
				views.put(resId, v);
			}
			return (T) v;
		}
	}

	public void addAll(List<T> elem) {
		data.addAll(elem);
		notifyDataSetChanged();
	}

	public void remove(T elem) {
		data.remove(elem);
		notifyDataSetChanged();
	}

	public void remove(int index) {
		data.remove(index);
		notifyDataSetChanged();
	}

	public void replaceAll(List<T> elem) {
		data.clear();
		data.addAll(elem);
		notifyDataSetChanged();
	}

}
