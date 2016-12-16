package com.net.lnk.cglib;

/**
 * CGLIB返回的代理类是基于原来类的子类
 */
public interface ProxyService {

	/**
	 * 创建类的代理对象，创建过程中调用无参构成函数构造
	 */
	@SuppressWarnings("rawtypes")
	Object createProxy(Class clazz);

	/**
	 * 创建类的代理对象，创建过程中调用有参构造函数构造
	 */
	@SuppressWarnings("rawtypes")
	Object createProxy(Class clazz, Class[] paramType, Object[] paramValue);

	/**
	 * 创建类的代理对象，使用自定义的MethodInterceptor类型回调（callback）来代替net.sf.cglib.proxy.
	 * NoOp回调
	 */
	@SuppressWarnings("rawtypes")
	Object createProxyWithMethodInterceptor(Class clazz);

	/**
	 * 创建类的代理对象，net.sf.cglib.proxy.CallbackFilter允许在方法层设置回调（callback）
	 */
	@SuppressWarnings("rawtypes")
	Object createProxyWithCallbackFilter(Class clazz);

	/**
	 * 创建类的代理对象
	 */
	@SuppressWarnings("rawtypes")
	Object createProxyWithMixin(Class[] interfaces, Object[] delegates);

}
