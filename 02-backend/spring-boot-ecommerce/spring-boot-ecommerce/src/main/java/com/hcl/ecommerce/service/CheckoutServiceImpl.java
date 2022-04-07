package com.hcl.ecommerce.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dao.CustomerRepository;
import com.hcl.ecommerce.dao.ProductRepository;
import com.hcl.ecommerce.dto.PaymentInfo;
import com.hcl.ecommerce.dto.Purchase;
import com.hcl.ecommerce.dto.PurchaseResponse;
import com.hcl.ecommerce.entity.Customer;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.OrderItem;
import com.hcl.ecommerce.entity.Product;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	private ProductRepository productRepository;
	private CustomerRepository customerRepository;
	static Logger log = Logger.getLogger(CheckoutServiceImpl.class);

	@Autowired
	public CheckoutServiceImpl(CustomerRepository customerRepository, ProductRepository productRepository,
			@Value("${stripe.key.secret}") String secretKey) {
		this.customerRepository = customerRepository;
		this.productRepository = productRepository;
		// initialize stripe API with secret key
		Stripe.apiKey = secretKey;
	}

	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {

		// retrieve the order info from dto
		Order order = purchase.getOrder();
		log.info("Got order of purchase");
		// generate tracking number
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);

		log.debug("Got order tracking number. Order Tracking number: " + order);
		// populate order with orderItems

		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		for (OrderItem orders : orderItems) {
			// log.debug("Got order item of id: " +orders.getProductId() + " it has an
			// quantity of " + orders.getQuantity());
			Optional<Product> orderFromDB = productRepository.findById(orders.getProductId());
			int decrementAmount = orderFromDB.get().getUnitsInStock() - orders.getQuantity();
			orderFromDB.get().setUnitsInStock(decrementAmount);
			productRepository.save(orderFromDB.get());
			Optional<Product> orderFromDB2 = productRepository.findById(orders.getProductId());
			log.debug(orderFromDB2.get().getUnitsInStock());
		}

		// populate order with billingAddress and shippingAddress
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());

		log.debug("Got order Billing Address: " + purchase.getBillingAddress());
		log.debug("Got order Shipping Address: " + purchase.getShippingAddress());

		// populate customer with order
		Customer customer = purchase.getCustomer();

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
		return new PurchaseResponse(orderTrackingNumber);
	}

	@Override
	public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {

		List<String> paymentMethodTypes = new ArrayList<>();
		paymentMethodTypes.add("card");

		log.info("Creating payment intent");
		Map<String, Object> params = new HashMap<>();

		params.put("amount", paymentInfo.getAmount());
		params.put("currency", paymentInfo.getCurrency());
		params.put("payment_method_types", paymentMethodTypes);
		params.put("description", "Computer Accessories purchase");
		params.put("receipt_email", paymentInfo.getReceiptEmail());

		log.debug("Payment Params: " + params);

		return PaymentIntent.create(params);
	}

	private String generateOrderTrackingNumber() {

		// generate a random UUID number (UUID version-4)
		// For details see: Universally unique identifier in wiki

		log.info("Creating random OrderID");
		return UUID.randomUUID().toString();
	}

}
