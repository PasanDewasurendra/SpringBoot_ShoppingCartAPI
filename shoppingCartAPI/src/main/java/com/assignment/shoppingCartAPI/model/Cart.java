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
	
	
	public Cart() {
		super();
		this.items = new ArrayList<Item>();
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


	@Override
	public String toString() {
		return "Cart {id=" + id + ", customer=" + customer + ", items=" + items + "}";
	}

	
}
