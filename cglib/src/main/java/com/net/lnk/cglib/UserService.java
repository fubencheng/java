package com.net.lnk.cglib;

import java.util.List;

public interface UserService {

	User saveUser(User user);

	User getUser(String userName);

	List<User> getAllUser();

}
