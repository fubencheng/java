package com.net.lnk.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:beans.xml");

		UserService userService = ctx.getBean("userService", UserService.class);
		User user = userService.getUser(1L);

		System.out.println(user.getUserName());
	}

}
