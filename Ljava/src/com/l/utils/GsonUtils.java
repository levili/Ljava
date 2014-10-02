package com.l.utils;
import com.google.gson.Gson;
public class GsonUtils {
	private volatile static GsonUtils instance = null;
	private static Gson mGson;
	public static GsonUtils getInstance() {
		if (instance == null) {
			synchronized (LogUtils.class) {
				if (instance == null) {
					instance = new GsonUtils();
				}
			}
		}
		return instance;
	}

	private GsonUtils() {
		mGson = new Gson();
	}
	/** @Description: 对象转换gson字符串 */
	public  String getGsonStr(Object o) {
		String gsonStr = mGson.toJson(o);
		return gsonStr;
	}

	/** @Description: 将gson字符转换为对象 */
	public  Object getGsonObject(String json, Class<?> tagclass) {
		Object fromJson = mGson.fromJson(json, tagclass);
		return fromJson;
	}

}
