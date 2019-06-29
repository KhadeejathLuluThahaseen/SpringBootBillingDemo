package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

	Product findByName(String a);

	@Query(value = "select rate from product where name=?", nativeQuery = true)
	Double findproductrate(String name);

}
