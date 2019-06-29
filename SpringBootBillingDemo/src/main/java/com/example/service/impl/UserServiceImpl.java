package com.example.service.impl;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.BillingRepo;
import com.example.dao.ProductRepo;
import com.example.dao.RoleRepository;
import com.example.dao.UserRepo;
import com.example.model.Product;
import com.example.model.User;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	UserRepo userRepo;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	ProductRepo repo;
	@Autowired
	BillingRepo bilrepo;

	// private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User userForm) {
		System.out.println("entered");
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println("entered");
		userForm.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
		System.out.println("entered");
		System.out.println(roleRepository.findAll());
		userForm.setRoles(new HashSet<>(roleRepository.findAll()));

		System.out.println("entered");
		userRepo.save(userForm);
	}

	@Override
	public User findByUsername(String a) {
		return userRepo.findByUsername(a);

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public double findByName(String name) {
		try {
			logger.info("Into the  Employers Products Service Layer");
			Product prod = repo.findByName(name);
			String type = prod.getType();
			double rate = prod.getRate();
			if (type.equals("grocery")) {
				return rate;
			}
			double rates = (.3 * rate);
			double discount = rate - rates;
			return discount;
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	@Transactional
	public double findByNameCustomer(String name, String username) {
		try {
			logger.info("Into the Customers Products Service Layer");
			User user = userRepo.findByUsername(username);
			Long id = user.getId();
			Date regdate = user.getRegisterdate();
			java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			int year = date.getYear() - regdate.getYear();
			int month = date.getMonth() - regdate.getMonth();
			int day = date.getDay() - regdate.getMonth();
			Product prod = repo.findByName(name);
			String type = prod.getType();
			double rate = prod.getRate();
			if ((year >= 2) && (month >= 2) && (day >= 2)) {
				if (type.equals("grocery")) {
					return rate;
				}
				double rates = (.05 * rate);
				double discount = rate - rates;
				return discount;
			} else {
				int items = finditems(id);
				items = items + 1;
				double grocrate = 0;
				int discount = 0;
				if (type.equals("grocery")) {
					grocrate = rate;
				}

				Double prevrate = findrate(id);
				// Double newdis = prevrate + rate;
				// System.out.println(newdis);
				discount = (int) ((rate / 100));
				discount = discount * 5;
				System.out.println(discount);
				double afterdis = rate - discount;
				if (type.equals("grocery")) {
					afterdis = 0;
				}
				System.out.println(afterdis);
				double newrate = afterdis + grocrate + prevrate;
				System.out.println(newrate);
				int i = update(newrate, items, id);
				return 0.00;
			}

		} catch (Exception e) {
			return 0;
		}

	}



	@Override
	public double findByNameAffiliate(String name) {
		try {
			logger.info("Into the Affiliate Products Service Layer");
			Product prod = repo.findByName(name);
			String type = prod.getType();
			double rate = prod.getRate();
			if (type.equals("grocery")) {
				return rate;
			}
			double rates = (.1 * rate);
			double discount = rate - rates;
			return discount;
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public long findId(String username) {
		try {
			logger.info("Into the EXtrcting UserInfo Service Layer");
			long id = userRepo.findid(username);
			return id;
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public Double findrate(long id) {
		try {
			logger.info("Into the EXtrcting totalcost from billing info of user Service Layer");
			double rate = bilrepo.findid(id);
			return rate;
		} catch (Exception e) {
			logger.error("Error occured", e);
			return 0.00;
		}
	}

	@Override
	@Transactional
	public int update(Double totalcost, int items, long id) {
		try {
			logger.info("Into the Updating billing table of user Service Layer");
			bilrepo.update(totalcost, items, id);
			return 1;
		} catch (Exception e) {
			logger.error("Error occured", e);
			return 0;
		}
	}

	@Override
	public int finditems(long id) {
		try {
			logger.info("Into the Extracting Items inside user Service Layer");
			int i = bilrepo.findByUserId(id);
			return i;
		} catch (Exception e) {
			logger.error("Error occured", e);
			return 0;
		}
	}

	@Override
	public Double findproductrate(String name) {
		try {
			logger.info("Into the Extracting product rate inside user Service Layer");
			Double rate = repo.findproductrate(name);
			return rate;
		} catch (Exception e) {
			logger.error("Error occured", e);
			return 0.00;
		}
	}

}
