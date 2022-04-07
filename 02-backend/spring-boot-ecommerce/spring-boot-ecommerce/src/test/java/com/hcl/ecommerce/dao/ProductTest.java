package com.hcl.ecommerce.dao;

import java.math.BigDecimal;
import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.ProductCategory;
import com.stripe.model.Application;

@ContextConfiguration(classes = {ProductRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.hcl.ecommerce.entity"})
@DataJpaTest
public class ProductTest {
	
	// Required Repos
	@Autowired
	private ProductRepository prodRepo;
	
	@Autowired
	private ProductCategoryRepository prodCatRepo;
	
	// Commonly used objects
	private Date testCreatedDate = new Date();
	private Date testUpdatedDate = new Date();
	
	BigDecimal price = new BigDecimal(9.99);
	
	private Product p1;
	private Product p2;
	private Product p3;
	private Product p4;
	private Product p5;
	private ProductCategory prodCat;
	private ProductCategory prodCat2;
	
	
	@BeforeEach
	public void setUp() {

		prodCat = ProductCategory.builder()
				.categoryName("Test Category")
				.thumbnailUrl("assets/images/test.jpg")
				.products(null)
				.build();

		prodCat2 = ProductCategory.builder()
				.categoryName("Test Category 2")
				.thumbnailUrl("assets/images/test.jpg")
				.products(null)
				.build();

		prodCatRepo.save(prodCat);
		prodCatRepo.save(prodCat2);

		p1 = Product.builder()
				.category(prodCat)
				.sku("TEST-PROD-0001")
				.name("Test Product")
				.description("this is a description for the test product")
				.unitPrice(price)
				.imageUrl("assets/images/products/placeholder.png")
				.active(true)
				.unitsInStock(100)
				.dateCreated(testCreatedDate)
				.lastUpdated(testUpdatedDate)
				.build();

		p2 = Product.builder()
				.category(prodCat)
				.sku("TEST-PROD-0001")
				.name("Test Product")
				.description("this is a description for the test product")
				.unitPrice(price)
				.imageUrl("assets/images/products/placeholder.png")
				.active(true)
				.unitsInStock(100)
				.dateCreated(testCreatedDate)
				.lastUpdated(testUpdatedDate)
				.build();

		p3 = Product.builder()
				.category(prodCat)
				.sku("TEST-PROD-0001")
				.name("Test Product Gaming 1")
				.description("this is a description for the test product")
				.unitPrice(price)
				.imageUrl("assets/images/products/placeholder.png")
				.active(true)
				.unitsInStock(100)
				.dateCreated(testCreatedDate)
				.lastUpdated(testUpdatedDate)
				.build();

		p4 = Product.builder()
				.category(prodCat)
				.sku("TEST-PROD-0001")
				.name("Test Product Gaming 2")
				.description("this is a description for the test product")
				.unitPrice(price)
				.imageUrl("assets/images/products/placeholder.png")
				.active(true)
				.unitsInStock(100)
				.dateCreated(testCreatedDate)
				.lastUpdated(testUpdatedDate)
				.build();

		p5 = Product.builder()
				.category(prodCat)
				.sku("TEST-PROD-0001")
				.name("Test Product Gaming 3")
				.description("this is a description for the test product")
				.unitPrice(price)
				.imageUrl("assets/images/products/placeholder.png")
				.active(true)
				.unitsInStock(100)
				.dateCreated(testCreatedDate)
				.lastUpdated(testUpdatedDate)
				.build();

		Set<Product> pSet = new HashSet<>();
		pSet.add(p1);
		pSet.add(p2);
		pSet.add(p3);
		pSet.add(p4);
		pSet.add(p5);
		prodCat.setProducts(pSet);

		prodCatRepo.save(prodCat);

		System.out.println("\n=========================\n");
	}
	
	/*
	 * Sample test to ensure JUnit is set up properly
	 */
	@Test
	public void testSample() {
		
		int val = 1;

		Assertions.assertEquals(1, val);
		Assertions.assertNotEquals(2, val);
	}

	
	/*
	 * ==================== TEST QUERY METHODS ==================== 
	 */
	/*
	 * Confirms that any and all returned products belong to the
	 * appropriate Category based on the category variable's id
	 */
	@Test
	public void testProductFindByCategoryIdHappy() {

		System.out.println("FIND BY CATEGORY ID HAPPY");

		Pageable pageable = PageRequest.of(0, 10);

		Long id = 1L;
		
		Page<Product> prodPage = prodRepo.findByCategoryId(id, pageable);
		List<Product> prodList = prodPage.toList();
		
		for (Product product : prodList) {
			
			System.out.println(product.getCategory().getCategoryName());
			Assertions.assertEquals(1, (long) product.getCategory().getId());
		}
	}
	
	/*
	 * Confirms that finding by category Id which does
	 * not exist will return an empty result
	 */
	@Test
	public void testProductFindByCategoryIdUnHappy() {

		System.out.println("FIND BY CATEGORY ID UNHAPPY");

		List<ProductCategory> prodCatList = prodCatRepo.findAll();

		System.out.println("Available Category Id's: ");
		for (ProductCategory prodCat : prodCatList) {
			System.out.println("ID: " + prodCat.getId() + "\nName: " + prodCat.getCategoryName());
		}

		Pageable pageable = PageRequest.of(0, 10);
		
		Long id = 99999L;
		
		Page<Product> prodPage = prodRepo.findByCategoryId(id, pageable);
		List<Product> prodList = prodPage.toList();
		
		Assertions.assertTrue(prodList.size() == 0);
	}
	
	/*
	 * Confirms findByNameContaining query method
	 * returns products only containing that string
	 * or substring
	 */
	@Test
	public void testProductFindByNameContainingHappy() {

		System.out.println("FIND BY NAME CONTAINING HAPPY");

		Pageable pageable = PageRequest.of(0, 20);
		String search = "Gaming";

		Page<Product> prodPage = prodRepo.findByNameContaining(search, pageable);
		List<Product> prodList = prodPage.toList();
		
		if (prodList.size() > 0) {
			
			for (Product product : prodList) {
			
				System.out.println(product.getName());
				Assertions.assertTrue(product.getName().contains(search));
			}
		}
		else {
			
			System.out.println("No matches found when matches were expected");
			Assertions.fail();
		}
			
	}
	
	/*
	 * Confirms findByNameContaining query method
	 * returns products only containing that string
	 * or substring
	 * Checks unhappy path if no string is found
	 */
	@Test
	public void testProductFindByNameContainingUnHappy() {

		System.out.println("FIND BY NAME CONTAINING UNHAPPY");

		Pageable pageable = PageRequest.of(0, 10);
		String search = "asdfsdfaser232d";
		
		Page<Product> prodPage = prodRepo.findByNameContaining(search, pageable);
		List<Product> prodList = prodPage.toList();

		Assertions.assertEquals(0, prodList.size());
	}
	
	
	
	/*
	 * ==================== TEST CRUD OPERATIONS ====================
	 */
	
	/* 
	* JUnit test for Saving Product to Database
	* tests the product was successfully saved
	* by checking if the auto generated ID was 
	* created.
	*/
	@Test
	public void testCreateProduct() {

		System.out.println("CRATE PRODUCT");

		prodCatRepo.save(prodCat);
		prodRepo.save(p1);
		
		System.out.println("Test Create product: " + p1.toString());
		
		Assertions.assertTrue(p1.getId() > 0);
	}
	
	/*
	 * Test product repository ability to GET a product
	 * from the MySQL db
	 */
	@Test
	public void testGetProduct() {

		System.out.println("GET PRODUCT");

		prodRepo.save(p1);
		Product expectedProd = p1;

		System.out.println("Expected Product: " + expectedProd.getName());

		Long id = p1.getId();
		System.out.println("Actual Product: " + prodRepo.getById(id).getName());

		Assertions.assertSame(prodRepo.getById(id), expectedProd);
	}
	
	/*
	 * Tests product repository's ability to update
	 * products
	 */
	@Test
	public void testUpdateProduct() {

		System.out.println("UPDATE PRODUCT");

		String oldName;
		String newName;

		prodCatRepo.save(prodCat);
		prodRepo.save(p1);
		
		System.out.println("Id: " + p1.getId() + "\nName: " + prodRepo.getById(p1.getId()).getName() + "\n");
		oldName = prodRepo.getById(p1.getId()).getName();
		
		String testName = "New Test Name";
		prodRepo.getById(p1.getId()).setName(testName);
		
		System.out.println("Id: " + p1.getId() + "\nName: " + prodRepo.getById(p1.getId()).getName() + "\n");
		newName = prodRepo.getById(p1.getId()).getName();

		Assertions.assertFalse(oldName == newName);
	}
	
	/*
	 * Tests product repository to delete
	 * products from the repo
	 */
	@Test
	public void testDeleteProduct() {

		System.out.println("DELETE PRODUCT");

		prodRepo.save(p1);
		prodRepo.save(p2);
		
		Long testProdId = p1.getId();

		System.out.println("Delete product with id: " + testProdId);

		Assertions.assertNotNull(prodRepo.getById(p1.getId()));

		prodRepo.delete(prodRepo.getById(testProdId));
		
		List<Product> prodList = prodRepo.findAll();

		for (Product product : prodList) {
			System.out.println(product.getId());
		}
		
		Assertions.assertFalse(prodList.contains(p1));
	}
}
