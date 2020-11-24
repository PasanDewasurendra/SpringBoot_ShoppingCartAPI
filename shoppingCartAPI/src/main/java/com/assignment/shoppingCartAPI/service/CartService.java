package com.assignment.shoppingCartAPI.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.shoppingCartAPI.model.Cart;
import com.assignment.shoppingCartAPI.model.Item;
import com.assignment.shoppingCartAPI.repository.CartRepository;


@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	
	
	public List<Cart> getAllCarts(){
		return cartRepository.findAll();
	}
	
	public Cart addCart(Cart cart) {
		return cartRepository.save(cart);
	}
	
	public String removeCart(String id){
		try {
			cartRepository.deleteById(id);
			return "Success";
		}catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
		
	}
	
	
	public Object getAllItems(String id){
		try {
			return cartRepository.findById(id).get();
		}catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
		
	}
	
	
	public String addItemToCart(String id, Item item){
		
		try {
			Optional<Cart> cart = cartRepository.findById(id);
			cart.ifPresent(c -> c.addItem(item));
			cart.ifPresent(c -> cartRepository.save(c));
			return "Success";
			
		}catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
		
	}
	

	public String updateCartItem(String id, Item item) {
		try {
			Optional<Cart> cart = cartRepository.findById(id);
			List<Item> items = cart.get().getItems();
			String status = null;
			
			for (Item i : items) {
				if (i.getName().equals(item.getName())) {
					i.setQty(item.getQty());
					status = "Success";
					break;
				}else {
					status = "Item Not Found.";
				}
			}
			cart.ifPresent(c -> c.setItems(items));
			cart.ifPresent(c -> cartRepository.save(c));
			
			return status;
		
		}catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
		
	}
	
	
	public String removeCartItem(String id, Item item) {
		try {
			Optional<Cart> cart = cartRepository.findById(id);
			List<Item> items = cart.get().getItems();
			String status = null;
			
			for (Item i : items) {
				if (i.getName().equals(item.getName())) {
					items.remove(i);
					status = "Success";
					break;
				}else {
					status = "Item Not Found.";
				}
			}	
			cart.ifPresent(c -> c.setItems(items));
			cart.ifPresent(c -> cartRepository.save(c));
			
			return status;
			
		}catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
		
	}
	
	
	public Cart checkout(String id) {
		
		Optional<Cart> cart = cartRepository.findById(id);
		List<Item> items = cart.get().getItems();
		
		double total = 0;
		for (Item i : items) {
			total += i.getPrice() * i.getQty();
		}
		
		items.clear();
		cart.get().setItems(items);
		cart.get().setTotal(total);
		cartRepository.save(cart.get());
		
		return cart.get();
		
	}
	
	
}
