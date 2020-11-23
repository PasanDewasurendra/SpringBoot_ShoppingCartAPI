package com.assignment.shoppingCartAPI.model;

import org.springframework.data.annotation.Id;

public class Item {
	@Id
	private String name;
	private int qty;
	private double price;
	
	public Item() {
		super();
		this.price = 100.00;
		// TODO Auto-generated constructor stub
	}

	public Item(String name, int qty) {
		super();
		this.name = name;
		this.qty = qty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	@Override
	public String toString() {
		return "Item {name=" + name + ", qty=" + qty + ", price=" + price + "}";
	}
	

}
