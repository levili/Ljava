package com.l.volley;

public class UserVolley extends BaseVolleyEvent<UserVolley>{
	private String userName,passWord;
	public UserVolley(String action, String call) {
		super(action, call);
	}
	public UserVolley getlog(String userName,String password){
		UserVolley userVolley = new UserVolley("user", "login");
		userVolley.passWord = password;
		userVolley.userName = userName;
		return userVolley;
	}
}
