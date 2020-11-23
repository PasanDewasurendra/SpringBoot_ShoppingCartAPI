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
	
	public void addCart(String customer) {
		cartRepository.save(new Cart(customer));
	}
	
	public Optional<Cart> getCart(String id){
		return cartRepository.findById(id);
	}
	
	public void removeCart(String id){
		cartRepository.deleteById(id);
	}
	
	
	public Optional<Cart> addItemToCart(String id, Item item){
		
		Optional<Cart> cart = cartRepository.findById(id);
		cart.ifPresent(c -> c.addItem(item));
		cart.ifPresent(c -> cartRepository.save(c));
		return cart;
	}
	

	public Optional<Cart> updateCartItem(String id, Item item) {
		
		Optional<Cart> cart = cartRepository.findById(id);
		List<Item> items = cart.get().getItems();
		
		for (Item i : items) {
			if (i.getName().equals(item.getName())) {
				i.setQty(item.getQty());
				break;
			}
		}
		cart.ifPresent(c -> c.setItems(items));
		cart.ifPresent(c -> cartRepository.save(c));
		return cart;
	}
	
	
	public Optional<Cart> removeCartItem(String id, Item item) {
		Optional<Cart> cart = cartRepository.findById(id);
		List<Item> items = cart.get().getItems();
		
		for (Item i : items) {
			if (i.getName().equals(item.getName())) {
				items.remove(i);
				break;
			}
		}	
		cart.ifPresent(c -> c.setItems(items));
		cart.ifPresent(c -> cartRepository.save(c));
		return cart;
	}
	
	
}
