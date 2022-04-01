package com.hcl.ecommerce.service;

import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dao.CustomerRepository;
import com.hcl.ecommerce.dto.PurchaseDto;
import com.hcl.ecommerce.dto.PurchaseResponseDto;
import com.hcl.ecommerce.entity.Customer;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.OrderItem;
import com.hcl.mapper.PurchaseMapper;

@Service
public class CheckoutServiceImpl implements CheckoutService {
	
	private CustomerRepository customerRepository;
	
	private PurchaseMapper purchaseMapper;
	
	@Autowired
	public CheckoutServiceImpl(CustomerRepository customerRepository/*, PurchaseMapper purchaseMapper*/) {
		this.customerRepository = customerRepository;
		//this.purchaseMapper = purchaseMapper;
	}
	
	@Override
	@Transactional
	public PurchaseResponseDto placeOrder(PurchaseDto purchaseDto) {
		
		// retrieve the order info from dto
		Order order = purchaseDto.getOrder();
		
		// generate tracking number
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		// populate order with orderItems
		Set<OrderItem> orderItems = purchaseDto.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		
		// populate order with billingAddress and shippingAddress
		order.setBillingAddress(purchaseDto.getBillingAddress());
		order.setShippingAddress(purchaseDto.getShippingAddress());
		
		// populate customer with order
		Customer customer = purchaseDto.getCustomer();
		
		// check if this is an existing customer
		String theEmail = customer.getEmail();
		
		Customer customerFromDB = customerRepository.findByEmail(theEmail);
		
		if (customerFromDB != null) {
			// if we find the customer assign to customer variable
			customer = customerFromDB;
		}
		
		customer.add(order);
		
		// save to the database
		customerRepository.save(customer);
		
		// return a response
		return new PurchaseResponseDto(orderTrackingNumber);
	}

	private String generateOrderTrackingNumber() {
		
		// generate a random UUID number (UUID version-4)
		// For details see: Universally unique identifier in wiki
		return UUID.randomUUID().toString();
	}

}
