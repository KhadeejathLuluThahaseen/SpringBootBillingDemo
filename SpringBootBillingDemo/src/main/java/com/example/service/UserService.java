package com.example.service;

import com.example.model.User;

public interface UserService {

	void save(User userForm);

	User findByUsername(String a);

	double findByName(String name);

	double findByNameCustomer(String name, String username);

	double findByNameAffiliate(String name);

	long findId(String username);

	Double findrate(long id);

	int update(Double totalcost, int items, long id);

	int finditems(long id);

	Double findproductrate(String name);

}
