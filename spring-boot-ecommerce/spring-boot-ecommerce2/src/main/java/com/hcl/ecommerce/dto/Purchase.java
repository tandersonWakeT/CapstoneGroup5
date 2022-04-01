package com.hcl.ecommerce.dto;

import java.util.Set;

import com.hcl.ecommerce.entity.Address;
import com.hcl.ecommerce.entity.Customer;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.OrderItem;

import lombok.Data;

@Data
public class Purchase {

	private Customer customer;
	
	private Address shippingAddress;
	
	private Address billingAddress;
	
	private Order order;
	
	private Set<OrderItem> orderItems;
	
}
