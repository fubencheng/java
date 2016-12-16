package com.net.lnk.cglib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CGLIB 返回的代理类是基于原来类的子类的
 */
@Inherited // 必须添加，否则CGLIB生成的代理类方法上会丢失该注解
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Auth {

}
