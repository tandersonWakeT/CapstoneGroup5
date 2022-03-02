package com.hcl.ecommerce.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcl.ecommerce.entity.Product;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	// query method, findBy - match by categoryId -- Spring Magic
	Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
}