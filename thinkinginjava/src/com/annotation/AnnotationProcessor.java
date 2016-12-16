package com.annotation;

import java.lang.reflect.Field;

public class AnnotationProcessor {

	public void process(Object o) throws Exception {
		Class<? extends Object> clazz = o.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			process(field, o);
			field.setAccessible(false);
		}
	}

	public void process(Field field, Object o) throws Exception {
		NotNull annotation = field.getAnnotation(NotNull.class);
		Object value = field.get(o);
		if (annotation == null) {
			return;
		}

		/************* 注解解析工作开始 ***************/

		if (value == null) {
			throw new Exception(annotation.message());
		}
	}

}
