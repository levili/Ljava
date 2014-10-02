package com.l.volley;

import org.json.JSONObject;

import android.content.Context;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.l.base.ui.LApplication;
import com.l.constants.ParameteConstant;
import com.l.utils.CheckUtils;
import com.l.utils.LogUtils;

public abstract class BaseVolleyEvent<T> {
	protected String action;
	protected String call;
	protected T data;

	public BaseVolleyEvent(String action, String call, T data) {
		super();
		this.action = action;
		this.call = call;
		this.data = data;
	}

	private RequestQueue getQueue(Context c) {
		RequestQueue requestQueue = null;
		if (c != null) {
			int hashCode = c.hashCode();
			if (LApplication.queueSet.containsKey(hashCode)) {
				requestQueue = LApplication.queueSet.get(hashCode);
			} else {
				requestQueue = Volley.newRequestQueue(c);
			}
		} else {
			try {
				LogUtils.getInstance().e("Context is null");
				throw new Exception("Context is null");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return requestQueue;
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	private StringRequest getGsonRequest(BaseVolleyEvent event) {
		GsonRequest request = new GsonRequest<T>(ParameteConstant.ROOT_URL+event.action+event.call, data.getClass(), data, new Response.Listener<BaseVolleyEvent>() {
			@Override
			public void onResponse(BaseVolleyEvent response) {
				
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		});
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public boolean LVolleyRequest(Context c,
			Class<? extends BaseVolleyEvent> Levent) {
		try {
			if (CheckUtils.isNullOrEmpty(c)) {
				return false;
			} else {
				RequestQueue queue = getQueue(c);
				BaseVolleyEvent newInstance = Levent.newInstance();
			}
		} catch (Exception e) {
			LogUtils.getInstance().e("Construction Error");
			return false;
		}
		return false;
	}
}
