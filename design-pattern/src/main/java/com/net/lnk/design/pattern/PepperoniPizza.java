package com.net.lnk.design.pattern;

/**
 * @tag
 *
 * @author Ben
 * @memo 2016年9月28日
 */
public class PepperoniPizza implements Pizza {

	public void prepare() {
		System.out.println("Prepare pepperoni pizza...");
	}

	public void bake() {
		System.out.println("Bake pepperoni pizza...");
	}

	public void cut() {
		System.out.println("Cut pepperoni pizza...");
	}

	public void box() {
		System.out.println("Box pepperoni pizza...");
	}

}
