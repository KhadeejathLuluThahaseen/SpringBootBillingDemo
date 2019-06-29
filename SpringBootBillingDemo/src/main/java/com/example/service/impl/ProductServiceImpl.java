package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ProductRepo;

@Service
public class ProductServiceImpl {

	@Autowired
	ProductRepo repo;

}
