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

import de.greenrobot.event.EventBus;

@SuppressWarnings("rawtypes")
public abstract class BaseVolleyEvent<T extends BaseVolleyEvent> {
	protected String action;
	protected String call;
	protected RequestQueue queue;
	public BaseVolleyEvent(String action, String call) {
		super();
		this.action = action;
		this.call = call;
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

	public boolean LVolleyRequest(Context c) {
		queue = getQueue(c);
		GsonRequest<T> request = new GsonRequest<T>(this,
				this.getClass(), new Response.Listener<T>() {
					@Override
					public void onResponse(T response) {
						LogUtils.getInstance().i("请求成功,已回送");
						EventBus.getDefault().post(response);
					}
				}, new Response.ErrorListener() {  
		            @Override  
		            public void onErrorResponse(VolleyError error) { 
		            	LogUtils.getInstance().i("请求失敗,已回滾");
						EventBus.getDefault().post(error);
		            }  
		        });
		queue.add(request);
		return false;
	}
}
