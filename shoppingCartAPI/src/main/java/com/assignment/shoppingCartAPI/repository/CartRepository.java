package com.assignment.shoppingCartAPI.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.assignment.shoppingCartAPI.model.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, String>{

	@Query("{'Item.name':?0}")
	Cart findByName(String name);
	
	
}
