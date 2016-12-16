package com.annotation;

public class User {

	@NotNull(message = "用户名不能为空")
	private String userName;

	@NotNull(message = "密码不能为空")
	private String password;

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
