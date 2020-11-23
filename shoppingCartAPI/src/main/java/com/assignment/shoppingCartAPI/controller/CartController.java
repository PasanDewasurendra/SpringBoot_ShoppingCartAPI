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
import com.assignment.shoppingCartAPI.repository.ItemRepository;


@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ItemRepository itemRepository;

//	Get All Shopping Carts
	@GetMapping(value = "/")
	public List<Cart> getAllCarts(){
		
		return cartRepository.findAll();
	}
	
//	Get Specific Shopping Cart
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getAllItems(@PathVariable String id) {
		
		Cart c = cartRepository.findById(id).get();
		
		Map<String, Object> status = new HashMap<String, Object>();
		status.put("cartId", c.getId());
		status.put("items", c.getItems());
		
		return ResponseEntity.ok(status);
	}
	
//	Create New Cart
	@PostMapping(value = "/create")
	public ResponseEntity<Object> addCart(@RequestBody Cart cart){
		
		Cart c = cartRepository.save(cart);
		
		Map<String, Object> status = new HashMap<String, Object>();
		status.put("cartId", c.getId());
		
		return ResponseEntity.ok(status);
	}
	
//	Remove Existing Cart
	@DeleteMapping(value = "/delete/{id}")
	public String delete(@PathVariable String id) {
		
		cartRepository.deleteById(id);
		return "success";
	}
	
	
//	Add Item to Cart
	@PostMapping(value = "/{id}/add")
	public ResponseEntity<Object> addItem(@PathVariable String id, @RequestBody Item item) {
		
		Optional<Cart> cart = cartRepository.findById(id);
		cart.ifPresent(c -> c.addItem(item));
		cart.ifPresent(c -> cartRepository.save(c));
		
		Map<String, String> status = new HashMap<String, String>();
		status.put("Status", "Success");
		
		return ResponseEntity.ok(status);
		
	}
	
//	Update Item from Cart
	@PutMapping(value = "/{id}/update")
	public ResponseEntity<Object> updateItem(@PathVariable String id, @RequestBody Item item) {
		
		Optional<Cart> cart = cartRepository.findById(id);
		List<Item> items = cart.get().getItems();
		Map<String, String> status = new HashMap<String, String>();
		
		for (Item i : items) {
			if (i.getName().equals(item.getName())) {
				i.setQty(item.getQty());
				status.put("Status", "Success");
				break;
			}else {
				status.put("Status", "Item not found");
			}
		}
		cart.ifPresent(c -> c.setItems(items));
		cart.ifPresent(c -> cartRepository.save(c));
		return ResponseEntity.ok(status);
	}
	
//	Remove Item from Cart
	@DeleteMapping(value = "/{id}/delete")
	public ResponseEntity<Object> deleteItem(@PathVariable String id, @RequestBody Item item) {
		
		Optional<Cart> cart = cartRepository.findById(id);
		List<Item> items = cart.get().getItems();
		Map<String, String> status = new HashMap<String, String>();
		
		for (Item i : items) {
			if (i.getName().equals(item.getName())) {
				items.remove(i);
				status.put("Status", "Success");
				break;
				
			}else {
				status.put("Status", "Item not found");
			}
		}	
		cart.ifPresent(c -> c.setItems(items));
		cart.ifPresent(c -> cartRepository.save(c));
		
		return ResponseEntity.ok(status);
	}
	
	
//	Checkout
	@GetMapping(value = "/{id}/checkout")
	public ResponseEntity<Object> checkout(@PathVariable String id) {
		
		Map<String, Object> status = new HashMap<String, Object>();
		Optional<Cart> cart = cartRepository.findById(id);
		List<Item> items = cart.get().getItems();
		
		double total = 0;
		for (Item i : items) {
			total += i.getPrice() * i.getQty();
		}
		
		status.put("cartId", cart.get().getId());
		status.put("total", total);
			
		return ResponseEntity.ok(status);	
	}
		

}
