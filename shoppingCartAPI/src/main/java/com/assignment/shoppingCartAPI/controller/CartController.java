package com.assignment.shoppingCartAPI.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.shoppingCartAPI.model.Cart;
import com.assignment.shoppingCartAPI.model.Item;
import com.assignment.shoppingCartAPI.repository.CartRepository;
import com.assignment.shoppingCartAPI.service.CartService;


@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	
	
//	Get All Shopping Carts
	@GetMapping(value = "/")
	public ResponseEntity<Object> getAllCarts(){
		
		Map<String, Object> status = new HashMap<String, Object>();

		try {
			status.put("Carts",cartService.getAllCarts());
			
		}catch (Exception e) {
			// TODO: handle exception
			status.put("Status", e.getMessage());
		}
		return ResponseEntity.ok(status);
	}
	
	
//	Create New Cart
	@PostMapping(value = "/create")
	public ResponseEntity<Object> addCart(@RequestBody Cart cart){

		Map<String, Object> status = new HashMap<String, Object>();

		try {
			Cart c = cartService.addCart(cart);
			status.put("cartId", c.getId());
			
		}catch (Exception e) {
			// TODO: handle exception
			status.put("Status", e.getMessage());
		}
		return ResponseEntity.ok(status);
	}
	
	
//	Remove Existing Cart
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id) {
		
		Map<String, Object> status = new HashMap<String, Object>();

		status.put("Status", cartService.removeCart(id));
	
		return ResponseEntity.ok(status);
	}
	
	
//	Get All Items from Cart
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getAllItems(@PathVariable String id) {
		
		Map<String, Object> status = new HashMap<String, Object>();
		
		try {
			Cart c = (Cart) cartService.getAllItems(id);
			
			status.put("cartId", c.getId());
			status.put("items", c.getItems());
		
		}catch (Exception e) {
			// TODO: handle exception
			status.put("Status", e.getMessage());
		}	
		return ResponseEntity.ok(status);
	}
	
	
//	Add Item to Cart
	@PostMapping(value = "/{id}/add")
	public ResponseEntity<Object> addItem(@PathVariable String id, @RequestBody Item item) {

		Map<String, String> status = new HashMap<String, String>();

		status.put("Status", cartService.addItemToCart(id, item));

		return ResponseEntity.ok(status);
		
	}
	
	
//	Update Item from Cart
	@PutMapping(value = "/{id}/update")
	public ResponseEntity<Object> updateItem(@PathVariable String id, @RequestBody Item item) {
		
		Map<String, Object> status = new HashMap<String, Object>();
		
		status.put("Status", cartService.updateCartItem(id, item));
		
		return ResponseEntity.ok(status);	
	
	}
	
	
//	Remove Item from Cart
	@DeleteMapping(value = "/{id}/delete")
	public ResponseEntity<Object> deleteItem(@PathVariable String id, @RequestBody Item item) {
		
		Map<String, Object> status = new HashMap<String, Object>();
		
		status.put("Status", cartService.removeCartItem(id, item));

		return ResponseEntity.ok(status);

	}
	
	
//	Checkout
	@GetMapping(value = "/{id}/checkout")
	public ResponseEntity<Object> checkout(@PathVariable String id) {
		
		Map<String, Object> status = new HashMap<String, Object>();

		try {
			Cart c = cartService.checkout(id);
			status.put("cartId", c.getId());
			status.put("total", c.getTotal());
			
		}catch (Exception e) {
			// TODO: handle exception
			status.put("status", e.getMessage());
		}
		
		return ResponseEntity.ok(status);	
	}
		

}
