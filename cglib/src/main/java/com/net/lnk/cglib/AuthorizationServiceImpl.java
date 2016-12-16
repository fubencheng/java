package com.net.lnk.cglib;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AuthorizationServiceImpl implements AuthorizationService {

	public void authorize(Method method) throws UnAuthorizeException {
		Annotation[] annotations = method.getDeclaredAnnotations();
		for (Annotation annotation : annotations) {
			if ("Auth".equals(annotation.annotationType().getSimpleName())) {
				System.out.println("Method [" + method.getName() + "] has been authorized!");
				return;
			}
		}

		throw new UnAuthorizeException("UnAuthorized!!!");
	}

}
