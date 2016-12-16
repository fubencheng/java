package com.net.lnk.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.Mixin;
import net.sf.cglib.proxy.NoOp;

public class ProxyServiceImpl implements ProxyService {

	/**
	 * 如果将泛型具体到UserService,会报错误java.lang.AbstractMethodError,
	 * 而具体到UserServiceImpl就正常
	 */
	@SuppressWarnings("rawtypes")
	public Object createProxy(Class targetClazz) {
		Enhancer e = new Enhancer();
		// target类是作为产生的代理的父类传进来的，不同于JDK的动态代理，它不能在创建代理时传target对象，target对象必须被CGLIB来创建
		e.setSuperclass(targetClazz);
		// NoOp回调把对方法调用直接委派到这个方法在父类中的实现，即在对目标方法调用前没有Interceptor
		e.setCallback(NoOp.INSTANCE);

		return e.create();
	}

	@SuppressWarnings("rawtypes")
	public Object createProxy(Class targetClazz, Class[] paramType, Object[] paramValue) {
		Enhancer e = new Enhancer();
		e.setSuperclass(targetClazz);
		e.setCallback(NoOp.INSTANCE);

		// 方法中第一个参数定义了参数的类型，第二个是参数的值
		return e.create(paramType, paramValue);
	}

	@SuppressWarnings("rawtypes")
	public Object createProxyWithMethodInterceptor(Class targetClazz) {
		Enhancer e = new Enhancer();
		e.setSuperclass(targetClazz);
		e.setCallback(new UserServiceMethodInterceptor(new AuthorizationServiceImpl()));

		return e.create();
	}

	@SuppressWarnings("rawtypes")
	public Object createProxyWithCallbackFilter(Class targetClazz) {
		Enhancer e = new Enhancer();
		e.setSuperclass(targetClazz);
		e.setCallbackFilter(new UserServiceCallbackFilter("save"));
		Callback saveCallback = new UserServiceSaveInterceptor();
		Callback getCallback = NoOp.INSTANCE;
		e.setCallbacks(new Callback[] { saveCallback, getCallback });

		return e.create();
	}

	@SuppressWarnings("rawtypes")
	public Object createProxyWithMixin(Class[] interfaces, Object[] delegates) {
		return Mixin.create(interfaces, delegates);
	}

}
