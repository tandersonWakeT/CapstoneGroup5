package com.hcl.ecommerce.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.dto.PaymentInfo;
import com.hcl.ecommerce.dto.Purchase;
import com.hcl.ecommerce.dto.PurchaseResponse;
import com.hcl.ecommerce.service.CheckoutService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

//@CrossOrigin("http://localhost:4200") - deprecated
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
	
	static Logger log = Logger.getLogger(CheckoutController.class);
	
	private CheckoutService checkoutService;
	
	@Autowired
	public CheckoutController(CheckoutService checkoutService) {
		
		this.checkoutService = checkoutService;
	}
	
	@PostMapping("/purchase")
	public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
		
		log.info("Purchase: " + purchase);
		
		PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
		
		return purchaseResponse;
	}
	
	@PostMapping("/payment-intent")
	public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException {
		
		log.info("paymentInfo.amount: " + paymentInfo.getAmount());
		
		PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);
		
		String paymentStr = paymentIntent.toJson();
		
		return new ResponseEntity<>(paymentStr, HttpStatus.OK);
	}
}
