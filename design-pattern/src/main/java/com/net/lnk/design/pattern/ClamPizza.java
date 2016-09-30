package com.net.lnk.design.pattern;

/**
 * @tag
 *
 * @author Ben
 * @memo 2016年9月28日
 */
public class ClamPizza implements Pizza {

	public void prepare() {
		System.out.println("Prepare clam pizza...");
	}

	public void bake() {
		System.out.println("Bake clam pizza...");
	}

	public void cut() {
		System.out.println("Cut clam pizza...");
	}

	public void box() {
		System.out.println("Box clam pizza...");
	}

}
