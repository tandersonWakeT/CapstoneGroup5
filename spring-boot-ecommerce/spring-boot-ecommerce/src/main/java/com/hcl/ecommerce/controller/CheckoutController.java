package com.hcl.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.dto.PurchaseDto;
import com.hcl.ecommerce.dto.PurchaseResponseDto;
import com.hcl.ecommerce.service.CheckoutService;

import io.swagger.annotations.ApiOperation;

//@CrossOrigin("http://localhost:4200") - deprecated
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
	
	private CheckoutService checkoutService;
	
	@Autowired
	public CheckoutController(CheckoutService checkoutService) {
		
		this.checkoutService = checkoutService;
	}
	
	@PostMapping("/purchase")
	@ApiOperation(value = "placing order for website",
	response = PurchaseResponseDto.class)
	public PurchaseResponseDto placeOrder(@RequestBody PurchaseDto purchaseDto) {
		
		PurchaseResponseDto purchaseResponseDto = checkoutService.placeOrder(purchaseDto);
		
		return purchaseResponseDto;
	}
}