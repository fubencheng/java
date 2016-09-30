package com.net.lnk.design.pattern.factory.abstrt;

import com.net.lnk.design.pattern.AbstractPizza;
import com.net.lnk.design.pattern.ChicagoStyleCheesePizza;
import com.net.lnk.design.pattern.ChicagoStyleClamPizza;
import com.net.lnk.design.pattern.ChicagoStylePepperoniPizza;
import com.net.lnk.design.pattern.ChicagoStyleVeggiPizza;

/**
 * @tag
 *
 * @author Ben
 * @memo 2016年9月28日
 */
public class ChicagoStylePizzaStore extends PizzaStore {

	public AbstractPizza createPizza(String style) {
		AbstractPizza pizza = null;
		if ("cheese".equals(style)) {
			pizza = new ChicagoStyleCheesePizza();
		} else if ("veggi".equals(style)) {
			pizza = new ChicagoStyleVeggiPizza();
		} else if ("clam".equals(style)) {
			pizza = new ChicagoStyleClamPizza();
		} else if ("pepperoni".equals(style)) {
			pizza = new ChicagoStylePepperoniPizza();
		}

		return pizza;
	}

}
