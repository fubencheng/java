package com.net.lnk.cglib;

import org.junit.Before;
import org.junit.Test;

public class ProxyServiceTest {

	ProxyService service;

	@Before
	public void init() {
		service = new ProxyServiceImpl();
	}

	@Test
	public void testCreateProxy() {
		UserService userService = (UserService) service.createProxy(UserServiceImpl.class);

		userService.getUser("Bean");
	}

	@Test
	public void testCreateProxyWithParams() {
		// 需要产生代理的类没有继承或者实现任何父亲，不同于JDK的动态代理，必须要有父亲
		User user = (User) service.createProxy(User.class, new Class[] { String.class, String.class },
				new Object[] { "Bean", "654321" });
		System.out.println(user.getUserName() + "|" + user.getPassword());
	}

	@Test
	public void testCreateProxyWithMethodInterceptor() {
		UserService userService = (UserService) service.createProxyWithMethodInterceptor(UserServiceImpl.class);

		userService.getUser("Ben");
	}

	@Test
	public void testCreateProxyWithCallbackFilter() {
		UserService userService = (UserService) service.createProxyWithCallbackFilter(UserServiceImpl.class);

		User u = userService.saveUser(new User());
		System.out.println(u.getUserName() + "|" + u.getPassword());
		u = userService.getUser("Bean");
		System.out.println(u.getUserName() + "|" + u.getPassword());
	}

	@Test
	public void testCreateProxyWithMixin() {
		Object object = service.createProxyWithMixin(new Class[] { UserService.class, AddressService.class },
				new Object[] { new UserServiceImpl(), new AddressServiceImpl() });
		UserService userService = (UserService) object;
		User u = userService.getUser("Ben");
		System.out.println(u.getUserName() + "|" + u.getPassword());

		AddressService addressService = (AddressService) object;
		UserAddress addr = addressService.getUserAddress("Ben");
		System.out.println(addr.getAddressId() + "|" + addr.getAddress() + "|" + addr.getUserName());
	}

}
