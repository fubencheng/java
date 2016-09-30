package com.net.lnk.design.pattern.factory.simple;

import com.net.lnk.design.pattern.Pizza;

/**
 * @tag
 *
 * @author Ben
 * @memo 2016年9月28日
 */
public class PizzaStore {

	SimplePizzaFactory factory;

	public PizzaStore(SimplePizzaFactory factory) {
		this.factory = factory;
	}

	public Pizza orderPizza(String type) {
		Pizza pizza = factory.createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();

		return pizza;
	}

}
