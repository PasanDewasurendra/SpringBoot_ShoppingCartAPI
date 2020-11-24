package com.assignment.shoppingCartAPI.model;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection =  "shoppingCart")
public class Cart {

	@Id
	private String id;
	private String customer;
	private List<Item> items;
	private double total;
	
	
	public Cart() {
		super();
		this.items = new ArrayList<Item>();
		this.total = 0.0;
		// TODO Auto-generated constructor stub
	}

	public Cart(String customer) {
		super();
		this.customer = customer;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		this.items.add(item);
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	
	
	@Override
	public String toString() {
		return "Cart [id=" + id + ", customer=" + customer + ", items=" + items + ", total=" + total + "]";
	}
	
	
}
