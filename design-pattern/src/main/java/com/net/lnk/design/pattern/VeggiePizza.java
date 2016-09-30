package com.net.lnk.design.pattern;

/**
 * @tag
 *
 * @author Ben
 * @memo 2016年9月28日
 */
public class VeggiePizza implements Pizza {

	public void prepare() {
		System.out.println("Prepare veggie pizza...");
	}

	public void bake() {
		System.out.println("Bake veggie pizza...");
	}

	public void cut() {
		System.out.println("Cut veggie pizza...");
	}

	public void box() {
		System.out.println("Box veggie pizza...");
	}

}
