package com.net.lnk.design.pattern;

/**
 * @tag
 *
 * @author Ben
 * @memo 2016年9月29日
 */
public class ChicagoStyleVeggiPizza extends AbstractPizza {

	public ChicagoStyleVeggiPizza() {
		this.name = "Chicago Style Veggi Pizza";
		this.dough = "Chicago Style Veggi Pizza Dough";
		this.sauce = "Chicago Style Veggi Pizza Sauce";
		this.toppings.add("Chicago Style Veggi Pizza Toppings");
	}

	public void bake() {
		System.out.println("Bake for 30 minutes at 400");
	}

}
