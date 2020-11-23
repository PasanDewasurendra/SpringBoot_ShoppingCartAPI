package com.assignment.shoppingCartAPI.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.assignment.shoppingCartAPI.model.Item;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
	
}
