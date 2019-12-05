package com.kimreporter.domain;

import java.io.Serializable;

public class UserInfoVO implements Serializable {
	
	private String user_email;
	private String user_pwd;
	private String user_name;
	private String user_id;
	private int is_active;
	
	public UserInfoVO() throws Exception{
	}
	public UserInfoVO(String user_email, String user_pwd, String user_name, String user_id, int is_active) {
		this.user_email = user_email;
		this.user_pwd = user_pwd;
		this.user_name = user_name;
		this.user_id = user_id;
		this.is_active = is_active;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	@Override
	public String toString() {
		return "UserInfoVO [user_email=" + user_email + ", user_pwd=" + user_pwd + ", user_name=" + user_name
				+ ", user_id=" + user_id + "]";
	}

}
