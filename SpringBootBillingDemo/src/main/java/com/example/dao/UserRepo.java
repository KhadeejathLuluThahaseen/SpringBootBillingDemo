package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	User findByUsername(String a);

	@Query(value = "select id from user where username=?", nativeQuery = true)
	long findid(String username);



}
