package com.hcl.ecommerce.service;

import com.hcl.ecommerce.dto.Purchase;
import com.hcl.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
	
	PurchaseResponse placeOrder(Purchase purchase);
}
