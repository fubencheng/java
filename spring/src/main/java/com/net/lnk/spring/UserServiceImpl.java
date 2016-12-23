package com.net.lnk.spring;

public class UserServiceImpl implements UserService {

	public User getUser(long userId) {
		User user = new User();
		user.setUserId(userId);
		user.setUserName("Ben");
		user.setPassword("123456");
		return user;
	}

}
