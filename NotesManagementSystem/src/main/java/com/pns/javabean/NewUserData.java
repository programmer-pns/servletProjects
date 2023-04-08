package com.pns.javabean;

import java.io.Serializable;

public class NewUserData implements Serializable{

	private static final long serialVersionUID = 1L;
	private String fullname;
	private String email;
	private String password;
	public NewUserData() {
		System.out.println("JavaBean NewUserData 0-param constructor");
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}