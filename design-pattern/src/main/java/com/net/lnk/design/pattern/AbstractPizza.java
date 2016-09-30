package com.net.lnk.design.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @tag
 *
 * @author Ben
 * @memo 2016年9月29日
 */
public abstract class AbstractPizza {

	String name;
	String dough;
	String sauce;
	List<Object> toppings = new ArrayList<Object>();

	public void prepare() {
		System.out.println("Preparing " + name);
		System.out.println("Tossing dough..." + dough);
		System.out.println("Adding sauce..." + sauce);
		System.out.println("Add toppings: ");
		for (int i = 0; i < toppings.size(); i++) {
			System.out.println("    " + toppings.get(i));
		}
	}

	public void bake() {
		System.out.println("Bake for 25 minutes at 350");
	}

	public void cut() {
		System.out.println("Cutting the pizza into diagonal slices");
	}

	public void box() {
		System.out.println("Place pizza in official PizzaStore box");
	}

	public String getName() {
		return name;
	}
}
