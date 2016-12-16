package com.annotation;

public class AnnotationMain {

	private static final AnnotationProcessor processor = new AnnotationProcessor();

	public static void main(String[] args) {
		User u = new User("Ben", null);
		u = new User(null, "123456");
		u = new User("Ben", "123456");
		try {
			processor.process(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
