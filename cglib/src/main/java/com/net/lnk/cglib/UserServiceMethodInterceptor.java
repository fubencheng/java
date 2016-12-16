package com.net.lnk.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class UserServiceMethodInterceptor implements MethodInterceptor {

	private AuthorizationService authorizationService;

	public UserServiceMethodInterceptor(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	public Object intercept(Object target, Method method, Object[] params, MethodProxy fastMethodProxy)
			throws Throwable {
		// 调用目标方法之前，执行额外的拦截操作
		authorizationService.authorize(method);

		// 可以这么做，但是效率没有代理的fastMethodProxy快
		// return method.invoke(target, params);

		return fastMethodProxy.invokeSuper(target, params);
	}

}
