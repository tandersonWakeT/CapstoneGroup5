package com.hcl.ecommerce.service;

import com.hcl.ecommerce.dto.PurchaseDto;
import com.hcl.ecommerce.dto.PurchaseResponseDto;

public interface CheckoutService {
	
	PurchaseResponseDto placeOrder(PurchaseDto purchase);
}
