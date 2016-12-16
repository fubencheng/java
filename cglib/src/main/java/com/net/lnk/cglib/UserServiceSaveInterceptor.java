package com.net.lnk.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class UserServiceSaveInterceptor implements MethodInterceptor {

	public Object intercept(Object target, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
		Object result = null;
		System.out.println(method.getName() + " invoked! Begin transaction...");
		try {
			result = methodProxy.invokeSuper(target, params);
			System.out.println("Invoke super method successfully, submit transaction!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception! Rollback transaction!");
		}

		return result;
	}

}
