package com.l.volley;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.l.constants.ParameteConstant;
import com.l.utils.GsonUtils;

public class GsonRequest<T> extends Request<T> {

	private final Listener<T> mListener;
	private BaseVolleyEvent mdata;
	private Gson mGson;
	private Class<? extends BaseVolleyEvent> mClass;

	public GsonRequest(int method, String url, Class<? extends BaseVolleyEvent> clazz,
			Listener<T> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		mGson = new Gson();
		mClass = clazz;
		mListener = listener;
	}

	public GsonRequest(BaseVolleyEvent data,Class<? extends BaseVolleyEvent> clazz,
			Listener<T> listener, ErrorListener errorListener) {
		this(Method.GET, ParameteConstant.ROOT_URL + data.action + data.call,
				clazz, listener, errorListener);
		mdata = data;
	}



	@SuppressWarnings("unchecked")
	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String jsonString = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			return (Response<T>) Response.success(mGson.fromJson(jsonString, mClass),
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected void deliverResponse(T response) {
		mListener.onResponse(response);
	}
}
