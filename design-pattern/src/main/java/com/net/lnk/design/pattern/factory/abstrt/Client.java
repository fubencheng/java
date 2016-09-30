package com.net.lnk.design.pattern.factory.abstrt;

import com.net.lnk.design.pattern.AbstractPizza;

/**
 * @tag
 *
 * @author Ben
 * @memo 2016年9月29日
 */
public class Client {

	public static void main(String[] args) {
		PizzaStore nyStore = new NYStylePizzaStore();
		PizzaStore chicagoStore = new ChicagoStylePizzaStore();

		AbstractPizza pizza = nyStore.orderPizza("cheese");
		System.out.println("Ethan ordered a " + pizza.getName() + "\n");

		pizza = chicagoStore.orderPizza("veggi");
		System.out.println("Joel ordered a " + pizza.getName() + "\n");
	}

}
