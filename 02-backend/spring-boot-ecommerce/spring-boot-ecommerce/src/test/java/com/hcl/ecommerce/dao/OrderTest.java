package com.hcl.ecommerce.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.ecommerce.entity.Customer;
import com.hcl.ecommerce.entity.Order;

@ContextConfiguration(classes = {OrderRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.hcl.ecommerce.entity"})
@DataJpaTest
public class OrderTest {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private CustomerRepository custRepo;
	
	
	@Test
	public void testOrderExists() {
		
		Order o1 = new Order();
		orderRepo.save(o1);
		
		System.out.println(o1.getId());
		Assertions.assertTrue(o1.getId() > 0);
	}
	
	/*
	 * Confirm query method returns orders belonging
	 * only to expected email and the order date
	 * is in descending order
	 */
	@Test
	public void testfindByCustomerEmailOrderByDateCreatedDesc() throws ParseException {
		
		Pageable pageable = PageRequest.of(0, 10);

		String testEmail = "test.email@test.com";
//		
		Order o1 = new Order();
		Order o2 = new Order();
		Order o3 = new Order();
		Order o4 = new Order();
//		Order o5 = new Order();
//		
		Customer c1 = new Customer();
		Customer c2 = new Customer();
//		Customer c3 = new Customer();
//		Customer c4 = new Customer();
//		Customer c5 = new Customer();

		String dateString1 = "2022-03-24 16:06:22.023";
		String dateString2 = "2022-03-21 13:30:21.012";
		String dateString3 = "2022-03-22 12:06:00.523";
		String dateString4 = "2022-03-17 14:50:26.057";
//		String dateString5 = "2022-03-24 14:05:53.158";

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date d1 = df.parse(dateString1);
		Date d2 = df.parse(dateString2);
		Date d3 = df.parse(dateString3);
		Date d4 = df.parse(dateString4);
//		Date d5 = df.parse(dateString5);

		System.out.println("d1: " + d1);

		o1.setDateCreated(d1);
		System.out.println("o1 date created: " + o1.getDateCreated());
		o2.setDateCreated(d2);
		o3.setDateCreated(d3);
		o4.setDateCreated(d4);
//		o5.setDateCreated(d5);

		c1.setEmail(testEmail);
		c2.setEmail("wrong.email@test.com");
//		c3.setEmail(testEmail);
//		c4.setEmail(testEmail);
//		c5.setEmail(testEmail);

		o1.setCustomer(c1);
		o2.setCustomer(c1);
		o3.setCustomer(c1);
		o4.setCustomer(c2);
//		o5.setCustomer(c5);

		custRepo.save(c1);
		custRepo.save(c2);
//		custRepo.save(c3);
//		custRepo.save(c4);
//		custRepo.save(c5);

		System.out.println("First Customer Id  " + c1.getId());
		System.out.println("Second Customer Id " + c2.getId());

		orderRepo.save(o1);
		orderRepo.save(o2);
		orderRepo.save(o3);
		orderRepo.save(o4);
//		orderRepo.save(o5);

		o1.setDateCreated(d1);
		System.out.println("o1 date created: " + o1.getDateCreated());
		o2.setDateCreated(d2);
		o3.setDateCreated(d3);
		o4.setDateCreated(d4);

		Page<Order> orderPage = orderRepo.findByCustomerEmailOrderByDateCreatedDesc(testEmail, pageable);
		
		List<Order> orderList = orderPage.toList();

		System.out.println("Size of orderList: " + orderList.size());

		for (Order order : orderList) {
			
			System.out.println("Retrieved customer email: " + order.getCustomer().getEmail());
			Assertions.assertTrue(order.getCustomer().getEmail().equalsIgnoreCase(testEmail));
		}
		
		for (int i = 0; i < orderList.size() - 1; i++) {
			
			System.out.println("Date order was created: " + orderList.get(i).getDateCreated());
			Assertions.assertTrue(orderList.get(i).getDateCreated().compareTo(orderList.get(i + 1).getDateCreated()) > 0);
		}
		System.out.println("Date order was created: " + orderList.get(orderList.size() - 1).getDateCreated());
	}
}
