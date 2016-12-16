package com.net.lnk.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackFilter;

/**
 * CallbackFilter 会逐个检查并匹配UserService中的方法实用什么callback
 */
public class UserServiceCallbackFilter implements CallbackFilter {

	// callback index
	private static final int SAVE = 0;
	private static final int GET = 1;

	private String methodPrefix;

	public UserServiceCallbackFilter(String methodPrefix) {
		this.methodPrefix = methodPrefix;
	}

	/**
	 * accept方法中对代理方法和回调进行了匹配，返回的值是匹配的方法的回调在回调数组中的索引
	 */
	public int accept(Method method) {
		if (method.getName().startsWith(methodPrefix)) {
			return SAVE;
		}

		return GET;
	}

}
