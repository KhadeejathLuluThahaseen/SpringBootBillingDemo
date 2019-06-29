package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Billing;

public interface BillingRepo extends JpaRepository<Billing, Long> {

	@Query(value = "update billing set total_cost=?,no_of_items=? where user_id=? ", nativeQuery = true)
	@Modifying
	void update(Double totalcost, int items, long id);

	@Query(value = "select no_of_items from billing where user_id=?", nativeQuery = true)
	int findByUserId(long user_id);

	@Query(value = "select total_cost from billing where user_id=?", nativeQuery = true)
	double findid(long id);

}
