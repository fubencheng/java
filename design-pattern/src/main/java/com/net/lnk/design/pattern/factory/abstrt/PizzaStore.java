package com.net.lnk.design.pattern.factory.abstrt;

import com.net.lnk.design.pattern.AbstractPizza;

/**
 * @tag
 *
 * @author Ben
 * @memo 2016年9月28日
 */
public abstract class PizzaStore {

	public final AbstractPizza orderPizza(String style) {
		AbstractPizza pizza = createPizza(style);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();

		return pizza;
	}

	public abstract AbstractPizza createPizza(String style);

}
