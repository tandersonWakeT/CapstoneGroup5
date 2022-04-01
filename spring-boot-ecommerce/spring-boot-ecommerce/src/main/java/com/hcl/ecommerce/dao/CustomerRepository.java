package com.hcl.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.ecommerce.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	
	// query method to find by email
	Customer findByEmail(String theEmail);
	
}
