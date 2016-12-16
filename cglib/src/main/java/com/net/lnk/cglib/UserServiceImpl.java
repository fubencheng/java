package com.net.lnk.cglib;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

	@Auth
	public User getUser(String userName) {
		System.out.println("Get user, userName = " + userName);

		User user = new User();
		user.setUserName("Ben");
		user.setPassword("123456");

		return user;
	}

	public List<User> getAllUser() {
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < 5; i++) {
			User user = new User();
			user.setUserName("Ben" + i);
			user.setPassword("123456" + i);
			users.add(user);
		}

		return users;
	}

	public User saveUser(User user) {
		// DAO
		return new User("Bean", "111111");
	}

}
