package com.net.lnk.design.pattern;

/**
 * @tag
 *
 * @author Ben
 * @memo 2016年9月28日
 */
public class CheesePizza implements Pizza {

	public void prepare() {
		System.out.println("Prepare cheese pizza...");
	}

	public void bake() {
		System.out.println("Bake cheese pizza...");
	}

	public void cut() {
		System.out.println("Cut cheese pizza...");
	}

	public void box() {
		System.out.println("Box cheese pizza...");
	}

}
