package com.net.lnk.cglib;

import java.lang.reflect.Method;

public interface AuthorizationService {

	void authorize(Method method) throws UnAuthorizeException;

}
