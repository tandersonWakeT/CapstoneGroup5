package com.hcl.ecommerce.controller;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.ecommerce.dao.CustomerRepository;
import com.hcl.ecommerce.dao.ProductRepository;
import com.hcl.ecommerce.dto.PaymentInfo;
import com.hcl.ecommerce.dto.Purchase;
import com.hcl.ecommerce.entity.Address;
import com.hcl.ecommerce.entity.Customer;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.service.CheckoutService;
import com.hcl.ecommerce.service.CheckoutServiceImpl;
import com.stripe.model.PaymentIntent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CheckoutController.class})
@ExtendWith(SpringExtension.class)
class CheckoutControllerTest {
    @Autowired
    private CheckoutController checkoutController;

    @MockBean
    private CheckoutService checkoutService;

    @Test
    void testPlaceOrder() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of CheckoutController.
        //   Add a package-visible constructor or a factory method for testing which
        //   (ideally) takes no arguments, and does not throw, return null or return
        //   a subtype.
        //   See https://diff.blue/R008

        Customer customer = new Customer();
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setOrders(new HashSet<>());

        Customer customer1 = new Customer();
        customer1.setEmail("jane.doe@example.org");
        customer1.setFirstName("Jane");
        customer1.setId(123L);
        customer1.setLastName("Doe");
        customer1.setOrders(new HashSet<>());
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        ProductRepository productRepository = mock(ProductRepository.class);
        when(customerRepository.findByEmail((String) any())).thenReturn(customer);
        when(customerRepository.save((Customer) any())).thenReturn(customer1);
        CheckoutController checkoutController = new CheckoutController(
                new CheckoutServiceImpl(customerRepository, productRepository, "EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY"));

        Address address = new Address();
        address.setCity("Oxford");
        address.setCountry("GB");
        address.setId(123L);
        address.setOrder(new Order());
        address.setState("MD");
        address.setStreet("Street");
        address.setZipCode("21654");

        Customer customer2 = new Customer();
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setId(123L);
        customer2.setLastName("Doe");
        customer2.setOrders(new HashSet<>());

        Address address1 = new Address();
        address1.setCity("Oxford");
        address1.setCountry("GB");
        address1.setId(123L);
        address1.setOrder(new Order());
        address1.setState("MD");
        address1.setStreet("Street");
        address1.setZipCode("21654");

        Order order = new Order();
        order.setBillingAddress(address);
        order.setCustomer(customer2);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        order.setDateCreated(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        order.setId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order.setLastUpdated(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        order.setOrderItems(new HashSet<>());
        order.setOrderTrackingNumber("42");
        order.setShippingAddress(address1);
        order.setStatus("Status");
        order.setTotalPrice(BigDecimal.valueOf(42L));
        order.setTotalQuantity(1);

        Address address2 = new Address();
        address2.setCity("Oxford");
        address2.setCountry("GB");
        address2.setId(123L);
        address2.setOrder(order);
        address2.setState("MD");
        address2.setStreet("Street");
        address2.setZipCode("21654");

        Customer customer3 = new Customer();
        customer3.setEmail("jane.doe@example.org");
        customer3.setFirstName("Jane");
        customer3.setId(123L);
        customer3.setLastName("Doe");
        customer3.setOrders(new HashSet<>());

        Order order1 = new Order();
        order1.setBillingAddress(new Address());
        order1.setCustomer(new Customer());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order1.setDateCreated(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        order1.setId(123L);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order1.setLastUpdated(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        order1.setOrderItems(new HashSet<>());
        order1.setOrderTrackingNumber("42");
        order1.setShippingAddress(new Address());
        order1.setStatus("Status");
        order1.setTotalPrice(null);
        order1.setTotalQuantity(1);

        Address address3 = new Address();
        address3.setCity("Oxford");
        address3.setCountry("GB");
        address3.setId(123L);
        address3.setOrder(order1);
        address3.setState("MD");
        address3.setStreet("Street");
        address3.setZipCode("21654");

        Customer customer4 = new Customer();
        customer4.setEmail("jane.doe@example.org");
        customer4.setFirstName("Jane");
        customer4.setId(123L);
        customer4.setLastName("Doe");
        customer4.setOrders(new HashSet<>());

        Order order2 = new Order();
        order2.setBillingAddress(new Address());
        order2.setCustomer(new Customer());
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order2.setDateCreated(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        order2.setId(123L);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order2.setLastUpdated(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        order2.setOrderItems(new HashSet<>());
        order2.setOrderTrackingNumber("42");
        order2.setShippingAddress(new Address());
        order2.setStatus("Status");
        order2.setTotalPrice(null);
        order2.setTotalQuantity(1);

        Address address4 = new Address();
        address4.setCity("Oxford");
        address4.setCountry("GB");
        address4.setId(123L);
        address4.setOrder(order2);
        address4.setState("MD");
        address4.setStreet("Street");
        address4.setZipCode("21654");

        Order order3 = new Order();
        order3.setBillingAddress(address3);
        order3.setCustomer(customer4);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order3.setDateCreated(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        order3.setId(123L);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order3.setLastUpdated(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        order3.setOrderItems(new HashSet<>());
        order3.setOrderTrackingNumber("42");
        order3.setShippingAddress(address4);
        order3.setStatus("Status");
        order3.setTotalPrice(BigDecimal.valueOf(42L));
        order3.setTotalQuantity(1);

        Address address5 = new Address();
        address5.setCity("Oxford");
        address5.setCountry("GB");
        address5.setId(123L);
        address5.setOrder(new Order());
        address5.setState("MD");
        address5.setStreet("Street");
        address5.setZipCode("21654");

        Customer customer5 = new Customer();
        customer5.setEmail("jane.doe@example.org");
        customer5.setFirstName("Jane");
        customer5.setId(123L);
        customer5.setLastName("Doe");
        customer5.setOrders(new HashSet<>());

        Address address6 = new Address();
        address6.setCity("Oxford");
        address6.setCountry("GB");
        address6.setId(123L);
        address6.setOrder(new Order());
        address6.setState("MD");
        address6.setStreet("Street");
        address6.setZipCode("21654");

        Order order4 = new Order();
        order4.setBillingAddress(address5);
        order4.setCustomer(customer5);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order4.setDateCreated(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        order4.setId(123L);
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order4.setLastUpdated(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        order4.setOrderItems(new HashSet<>());
        order4.setOrderTrackingNumber("42");
        order4.setShippingAddress(address6);
        order4.setStatus("Status");
        order4.setTotalPrice(BigDecimal.valueOf(42L));
        order4.setTotalQuantity(1);

        Address address7 = new Address();
        address7.setCity("Oxford");
        address7.setCountry("GB");
        address7.setId(123L);
        address7.setOrder(order4);
        address7.setState("MD");
        address7.setStreet("Street");
        address7.setZipCode("21654");

        Purchase purchase = new Purchase();
        purchase.setBillingAddress(address2);
        purchase.setCustomer(customer3);
        purchase.setOrder(order3);
        purchase.setOrderItems(new HashSet<>());
        purchase.setShippingAddress(address7);
        checkoutController.placeOrder(purchase);
        verify(customerRepository).findByEmail((String) any());
        verify(customerRepository).save((Customer) any());
        Address expectedShippingAddress = purchase.getShippingAddress();
        Order order5 = purchase.getOrder();
        assertSame(expectedShippingAddress, order5.getShippingAddress());
        Address expectedBillingAddress = purchase.getBillingAddress();
        assertSame(expectedBillingAddress, order5.getBillingAddress());
        assertSame(customer, order5.getCustomer());
    }

//    @Test
//    @Disabled()
//    void testPlaceOrder2() {
//        //   Diffblue Cover was unable to write a Spring test,
//        //   so wrote a non-Spring test instead.
//        //   Reason: R008 Failed to instantiate class under test.
//        //   Diffblue Cover was unable to construct an instance of CheckoutController.
//        //   Add a package-visible constructor or a factory method for testing which
//        //   (ideally) takes no arguments, and does not throw, return null or return
//        //   a subtype.
//        //   See https://diff.blue/R008
//
//        //   Reason: R013 No inputs found that don't throw a trivial exception.
//        //   Diffblue Cover tried to run the arrange/act section, but the method under
//        //   test threw
//        //   java.lang.NullPointerException
//        //       at com.hcl.ecommerce.controller.CheckoutController.placeOrder(CheckoutController.java:38)
//        //   In order to prevent placeOrder(Purchase)
//        //   from throwing NullPointerException, add constructors or factory
//        //   methods that make it easier to construct fully initialized objects used in
//        //   placeOrder(Purchase).
//        //   See https://diff.blue/R013 to resolve this issue.
//
//        CheckoutController checkoutController = new CheckoutController(null);
//
//        Address address = new Address();
//        address.setCity("Oxford");
//        address.setCountry("GB");
//        address.setId(123L);
//        address.setOrder(new Order());
//        address.setState("MD");
//        address.setStreet("Street");
//        address.setZipCode("21654");
//
//        Customer customer = new Customer();
//        customer.setEmail("jane.doe@example.org");
//        customer.setFirstName("Jane");
//        customer.setId(123L);
//        customer.setLastName("Doe");
//        customer.setOrders(new HashSet<>());
//
//        Address address1 = new Address();
//        address1.setCity("Oxford");
//        address1.setCountry("GB");
//        address1.setId(123L);
//        address1.setOrder(new Order());
//        address1.setState("MD");
//        address1.setStreet("Street");
//        address1.setZipCode("21654");
//
//        Order order = new Order();
//        order.setBillingAddress(address);
//        order.setCustomer(customer);
//        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order.setDateCreated(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
//        order.setId(123L);
//        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order.setLastUpdated(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
//        order.setOrderItems(new HashSet<>());
//        order.setOrderTrackingNumber("42");
//        order.setShippingAddress(address1);
//        order.setStatus("Status");
//        order.setTotalPrice(BigDecimal.valueOf(42L));
//        order.setTotalQuantity(1);
//
//        Address address2 = new Address();
//        address2.setCity("Oxford");
//        address2.setCountry("GB");
//        address2.setId(123L);
//        address2.setOrder(order);
//        address2.setState("MD");
//        address2.setStreet("Street");
//        address2.setZipCode("21654");
//
//        Customer customer1 = new Customer();
//        customer1.setEmail("jane.doe@example.org");
//        customer1.setFirstName("Jane");
//        customer1.setId(123L);
//        customer1.setLastName("Doe");
//        customer1.setOrders(new HashSet<>());
//
//        Order order1 = new Order();
//        order1.setBillingAddress(new Address());
//        order1.setCustomer(new Customer());
//        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order1.setDateCreated(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
//        order1.setId(123L);
//        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order1.setLastUpdated(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
//        order1.setOrderItems(new HashSet<>());
//        order1.setOrderTrackingNumber("42");
//        order1.setShippingAddress(new Address());
//        order1.setStatus("Status");
//        order1.setTotalPrice(null);
//        order1.setTotalQuantity(1);
//
//        Address address3 = new Address();
//        address3.setCity("Oxford");
//        address3.setCountry("GB");
//        address3.setId(123L);
//        address3.setOrder(order1);
//        address3.setState("MD");
//        address3.setStreet("Street");
//        address3.setZipCode("21654");
//
//        Customer customer2 = new Customer();
//        customer2.setEmail("jane.doe@example.org");
//        customer2.setFirstName("Jane");
//        customer2.setId(123L);
//        customer2.setLastName("Doe");
//        customer2.setOrders(new HashSet<>());
//
//        Order order2 = new Order();
//        order2.setBillingAddress(new Address());
//        order2.setCustomer(new Customer());
//        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order2.setDateCreated(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
//        order2.setId(123L);
//        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order2.setLastUpdated(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
//        order2.setOrderItems(new HashSet<>());
//        order2.setOrderTrackingNumber("42");
//        order2.setShippingAddress(new Address());
//        order2.setStatus("Status");
//        order2.setTotalPrice(null);
//        order2.setTotalQuantity(1);
//
//        Address address4 = new Address();
//        address4.setCity("Oxford");
//        address4.setCountry("GB");
//        address4.setId(123L);
//        address4.setOrder(order2);
//        address4.setState("MD");
//        address4.setStreet("Street");
//        address4.setZipCode("21654");
//
//        Order order3 = new Order();
//        order3.setBillingAddress(address3);
//        order3.setCustomer(customer2);
//        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order3.setDateCreated(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
//        order3.setId(123L);
//        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order3.setLastUpdated(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
//        order3.setOrderItems(new HashSet<>());
//        order3.setOrderTrackingNumber("42");
//        order3.setShippingAddress(address4);
//        order3.setStatus("Status");
//        order3.setTotalPrice(BigDecimal.valueOf(42L));
//        order3.setTotalQuantity(1);
//
//        Address address5 = new Address();
//        address5.setCity("Oxford");
//        address5.setCountry("GB");
//        address5.setId(123L);
//        address5.setOrder(new Order());
//        address5.setState("MD");
//        address5.setStreet("Street");
//        address5.setZipCode("21654");
//
//        Customer customer3 = new Customer();
//        customer3.setEmail("jane.doe@example.org");
//        customer3.setFirstName("Jane");
//        customer3.setId(123L);
//        customer3.setLastName("Doe");
//        customer3.setOrders(new HashSet<>());
//
//        Address address6 = new Address();
//        address6.setCity("Oxford");
//        address6.setCountry("GB");
//        address6.setId(123L);
//        address6.setOrder(new Order());
//        address6.setState("MD");
//        address6.setStreet("Street");
//        address6.setZipCode("21654");
//
//        Order order4 = new Order();
//        order4.setBillingAddress(address5);
//        order4.setCustomer(customer3);
//        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order4.setDateCreated(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
//        order4.setId(123L);
//        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order4.setLastUpdated(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
//        order4.setOrderItems(new HashSet<>());
//        order4.setOrderTrackingNumber("42");
//        order4.setShippingAddress(address6);
//        order4.setStatus("Status");
//        order4.setTotalPrice(BigDecimal.valueOf(42L));
//        order4.setTotalQuantity(1);
//
//        Address address7 = new Address();
//        address7.setCity("Oxford");
//        address7.setCountry("GB");
//        address7.setId(123L);
//        address7.setOrder(order4);
//        address7.setState("MD");
//        address7.setStreet("Street");
//        address7.setZipCode("21654");
//
//        Purchase purchase = new Purchase();
//        purchase.setBillingAddress(address2);
//        purchase.setCustomer(customer1);
//        purchase.setOrder(order3);
//        purchase.setOrderItems(new HashSet<>());
//        purchase.setShippingAddress(address7);
//        checkoutController.placeOrder(purchase);
//    }

    @Test
    void testPlaceOrder3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of CheckoutController.
        //   Add a package-visible constructor or a factory method for testing which
        //   (ideally) takes no arguments, and does not throw, return null or return
        //   a subtype.
        //   See https://diff.blue/R008

        Customer customer = new Customer();
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setOrders(new HashSet<>());

        Customer customer1 = new Customer();
        customer1.setEmail("jane.doe@example.org");
        customer1.setFirstName("Jane");
        customer1.setId(123L);
        customer1.setLastName("Doe");
        customer1.setOrders(new HashSet<>());
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        ProductRepository productRepository = mock(ProductRepository.class);
        when(customerRepository.findByEmail((String) any())).thenReturn(customer);
        when(customerRepository.save((Customer) any())).thenReturn(customer1);
        CheckoutController checkoutController = new CheckoutController(
                new CheckoutServiceImpl(customerRepository, productRepository, "EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY"));

        Address address = new Address();
        address.setCity("Oxford");
        address.setCountry("GB");
        address.setId(123L);
        address.setOrder(new Order());
        address.setState("MD");
        address.setStreet("Street");
        address.setZipCode("21654");

        Customer customer2 = new Customer();
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setId(123L);
        customer2.setLastName("Doe");
        customer2.setOrders(new HashSet<>());

        Address address1 = new Address();
        address1.setCity("Oxford");
        address1.setCountry("GB");
        address1.setId(123L);
        address1.setOrder(new Order());
        address1.setState("MD");
        address1.setStreet("Street");
        address1.setZipCode("21654");

        Order order = new Order();
        order.setBillingAddress(address);
        order.setCustomer(customer2);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        order.setDateCreated(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        order.setId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order.setLastUpdated(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        order.setOrderItems(new HashSet<>());
        order.setOrderTrackingNumber("42");
        order.setShippingAddress(address1);
        order.setStatus("Status");
        order.setTotalPrice(BigDecimal.valueOf(42L));
        order.setTotalQuantity(1);

        Address address2 = new Address();
        address2.setCity("Oxford");
        address2.setCountry("GB");
        address2.setId(123L);
        address2.setOrder(order);
        address2.setState("MD");
        address2.setStreet("Street");
        address2.setZipCode("21654");

        Customer customer3 = new Customer();
        customer3.setEmail("jane.doe@example.org");
        customer3.setFirstName("Jane");
        customer3.setId(123L);
        customer3.setLastName("Doe");
        customer3.setOrders(new HashSet<>());

        Order order1 = new Order();
        order1.setBillingAddress(new Address());
        order1.setCustomer(new Customer());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order1.setDateCreated(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        order1.setId(123L);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order1.setLastUpdated(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        order1.setOrderItems(new HashSet<>());
        order1.setOrderTrackingNumber("42");
        order1.setShippingAddress(new Address());
        order1.setStatus("Status");
        order1.setTotalPrice(null);
        order1.setTotalQuantity(1);

        Address address3 = new Address();
        address3.setCity("Oxford");
        address3.setCountry("GB");
        address3.setId(123L);
        address3.setOrder(order1);
        address3.setState("MD");
        address3.setStreet("Street");
        address3.setZipCode("21654");

        Customer customer4 = new Customer();
        customer4.setEmail("jane.doe@example.org");
        customer4.setFirstName("Jane");
        customer4.setId(123L);
        customer4.setLastName("Doe");
        customer4.setOrders(new HashSet<>());

        Order order2 = new Order();
        order2.setBillingAddress(new Address());
        order2.setCustomer(new Customer());
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order2.setDateCreated(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        order2.setId(123L);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order2.setLastUpdated(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        order2.setOrderItems(new HashSet<>());
        order2.setOrderTrackingNumber("42");
        order2.setShippingAddress(new Address());
        order2.setStatus("Status");
        order2.setTotalPrice(null);
        order2.setTotalQuantity(1);

        Address address4 = new Address();
        address4.setCity("Oxford");
        address4.setCountry("GB");
        address4.setId(123L);
        address4.setOrder(order2);
        address4.setState("MD");
        address4.setStreet("Street");
        address4.setZipCode("21654");

        Order order3 = new Order();
        order3.setBillingAddress(address3);
        order3.setCustomer(customer4);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order3.setDateCreated(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        order3.setId(123L);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order3.setLastUpdated(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        order3.setOrderItems(new HashSet<>());
        order3.setOrderTrackingNumber("42");
        order3.setShippingAddress(address4);
        order3.setStatus("Status");
        order3.setTotalPrice(BigDecimal.valueOf(42L));
        order3.setTotalQuantity(1);

        Address address5 = new Address();
        address5.setCity("Oxford");
        address5.setCountry("GB");
        address5.setId(123L);
        address5.setOrder(new Order());
        address5.setState("MD");
        address5.setStreet("Street");
        address5.setZipCode("21654");

        Customer customer5 = new Customer();
        customer5.setEmail("jane.doe@example.org");
        customer5.setFirstName("Jane");
        customer5.setId(123L);
        customer5.setLastName("Doe");
        customer5.setOrders(new HashSet<>());

        Address address6 = new Address();
        address6.setCity("Oxford");
        address6.setCountry("GB");
        address6.setId(123L);
        address6.setOrder(new Order());
        address6.setState("MD");
        address6.setStreet("Street");
        address6.setZipCode("21654");

        Order order4 = new Order();
        order4.setBillingAddress(address5);
        order4.setCustomer(customer5);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order4.setDateCreated(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        order4.setId(123L);
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order4.setLastUpdated(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        order4.setOrderItems(new HashSet<>());
        order4.setOrderTrackingNumber("42");
        order4.setShippingAddress(address6);
        order4.setStatus("Status");
        order4.setTotalPrice(BigDecimal.valueOf(42L));
        order4.setTotalQuantity(1);

        Address address7 = new Address();
        address7.setCity("Oxford");
        address7.setCountry("GB");
        address7.setId(123L);
        address7.setOrder(order4);
        address7.setState("MD");
        address7.setStreet("Street");
        address7.setZipCode("21654");

        Purchase purchase = new Purchase();
        purchase.setBillingAddress(address2);
        purchase.setCustomer(customer3);
        purchase.setOrder(order3);
        purchase.setOrderItems(new HashSet<>());
        purchase.setShippingAddress(address7);
        checkoutController.placeOrder(purchase);
        verify(customerRepository).findByEmail((String) any());
        verify(customerRepository).save((Customer) any());
        Address expectedShippingAddress = purchase.getShippingAddress();
        Order order5 = purchase.getOrder();
        assertSame(expectedShippingAddress, order5.getShippingAddress());
        Address expectedBillingAddress = purchase.getBillingAddress();
        assertSame(expectedBillingAddress, order5.getBillingAddress());
        assertSame(customer, order5.getCustomer());
    }

//    @Test
//    @Disabled()
//    void testPlaceOrder4() {
//        //   Diffblue Cover was unable to write a Spring test,
//        //   so wrote a non-Spring test instead.
//        //   Reason: R008 Failed to instantiate class under test.
//        //   Diffblue Cover was unable to construct an instance of CheckoutController.
//        //   Add a package-visible constructor or a factory method for testing which
//        //   (ideally) takes no arguments, and does not throw, return null or return
//        //   a subtype.
//        //   See https://diff.blue/R008
//
//        //   Reason: R013 No inputs found that don't throw a trivial exception.
//        //   Diffblue Cover tried to run the arrange/act section, but the method under
//        //   test threw
//        //   java.lang.NullPointerException
//        //       at com.hcl.ecommerce.controller.CheckoutController.placeOrder(CheckoutController.java:38)
//        //   In order to prevent placeOrder(Purchase)
//        //   from throwing NullPointerException, add constructors or factory
//        //   methods that make it easier to construct fully initialized objects used in
//        //   placeOrder(Purchase).
//        //   See https://diff.blue/R013 to resolve this issue.
//
//        CheckoutController checkoutController = new CheckoutController(null);
//
//        Address address = new Address();
//        address.setCity("Oxford");
//        address.setCountry("GB");
//        address.setId(123L);
//        address.setOrder(new Order());
//        address.setState("MD");
//        address.setStreet("Street");
//        address.setZipCode("21654");
//
//        Customer customer = new Customer();
//        customer.setEmail("jane.doe@example.org");
//        customer.setFirstName("Jane");
//        customer.setId(123L);
//        customer.setLastName("Doe");
//        customer.setOrders(new HashSet<>());
//
//        Address address1 = new Address();
//        address1.setCity("Oxford");
//        address1.setCountry("GB");
//        address1.setId(123L);
//        address1.setOrder(new Order());
//        address1.setState("MD");
//        address1.setStreet("Street");
//        address1.setZipCode("21654");
//
//        Order order = new Order();
//        order.setBillingAddress(address);
//        order.setCustomer(customer);
//        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order.setDateCreated(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
//        order.setId(123L);
//        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order.setLastUpdated(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
//        order.setOrderItems(new HashSet<>());
//        order.setOrderTrackingNumber("42");
//        order.setShippingAddress(address1);
//        order.setStatus("Status");
//        order.setTotalPrice(BigDecimal.valueOf(42L));
//        order.setTotalQuantity(1);
//
//        Address address2 = new Address();
//        address2.setCity("Oxford");
//        address2.setCountry("GB");
//        address2.setId(123L);
//        address2.setOrder(order);
//        address2.setState("MD");
//        address2.setStreet("Street");
//        address2.setZipCode("21654");
//
//        Customer customer1 = new Customer();
//        customer1.setEmail("jane.doe@example.org");
//        customer1.setFirstName("Jane");
//        customer1.setId(123L);
//        customer1.setLastName("Doe");
//        customer1.setOrders(new HashSet<>());
//
//        Order order1 = new Order();
//        order1.setBillingAddress(new Address());
//        order1.setCustomer(new Customer());
//        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order1.setDateCreated(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
//        order1.setId(123L);
//        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order1.setLastUpdated(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
//        order1.setOrderItems(new HashSet<>());
//        order1.setOrderTrackingNumber("42");
//        order1.setShippingAddress(new Address());
//        order1.setStatus("Status");
//        order1.setTotalPrice(null);
//        order1.setTotalQuantity(1);
//
//        Address address3 = new Address();
//        address3.setCity("Oxford");
//        address3.setCountry("GB");
//        address3.setId(123L);
//        address3.setOrder(order1);
//        address3.setState("MD");
//        address3.setStreet("Street");
//        address3.setZipCode("21654");
//
//        Customer customer2 = new Customer();
//        customer2.setEmail("jane.doe@example.org");
//        customer2.setFirstName("Jane");
//        customer2.setId(123L);
//        customer2.setLastName("Doe");
//        customer2.setOrders(new HashSet<>());
//
//        Order order2 = new Order();
//        order2.setBillingAddress(new Address());
//        order2.setCustomer(new Customer());
//        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order2.setDateCreated(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
//        order2.setId(123L);
//        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order2.setLastUpdated(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
//        order2.setOrderItems(new HashSet<>());
//        order2.setOrderTrackingNumber("42");
//        order2.setShippingAddress(new Address());
//        order2.setStatus("Status");
//        order2.setTotalPrice(null);
//        order2.setTotalQuantity(1);
//
//        Address address4 = new Address();
//        address4.setCity("Oxford");
//        address4.setCountry("GB");
//        address4.setId(123L);
//        address4.setOrder(order2);
//        address4.setState("MD");
//        address4.setStreet("Street");
//        address4.setZipCode("21654");
//
//        Order order3 = new Order();
//        order3.setBillingAddress(address3);
//        order3.setCustomer(customer2);
//        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order3.setDateCreated(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
//        order3.setId(123L);
//        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order3.setLastUpdated(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
//        order3.setOrderItems(new HashSet<>());
//        order3.setOrderTrackingNumber("42");
//        order3.setShippingAddress(address4);
//        order3.setStatus("Status");
//        order3.setTotalPrice(BigDecimal.valueOf(42L));
//        order3.setTotalQuantity(1);
//
//        Address address5 = new Address();
//        address5.setCity("Oxford");
//        address5.setCountry("GB");
//        address5.setId(123L);
//        address5.setOrder(new Order());
//        address5.setState("MD");
//        address5.setStreet("Street");
//        address5.setZipCode("21654");
//
//        Customer customer3 = new Customer();
//        customer3.setEmail("jane.doe@example.org");
//        customer3.setFirstName("Jane");
//        customer3.setId(123L);
//        customer3.setLastName("Doe");
//        customer3.setOrders(new HashSet<>());
//
//        Address address6 = new Address();
//        address6.setCity("Oxford");
//        address6.setCountry("GB");
//        address6.setId(123L);
//        address6.setOrder(new Order());
//        address6.setState("MD");
//        address6.setStreet("Street");
//        address6.setZipCode("21654");
//
//        Order order4 = new Order();
//        order4.setBillingAddress(address5);
//        order4.setCustomer(customer3);
//        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order4.setDateCreated(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
//        order4.setId(123L);
//        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        order4.setLastUpdated(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
//        order4.setOrderItems(new HashSet<>());
//        order4.setOrderTrackingNumber("42");
//        order4.setShippingAddress(address6);
//        order4.setStatus("Status");
//        order4.setTotalPrice(BigDecimal.valueOf(42L));
//        order4.setTotalQuantity(1);
//
//        Address address7 = new Address();
//        address7.setCity("Oxford");
//        address7.setCountry("GB");
//        address7.setId(123L);
//        address7.setOrder(order4);
//        address7.setState("MD");
//        address7.setStreet("Street");
//        address7.setZipCode("21654");
//
//        Purchase purchase = new Purchase();
//        purchase.setBillingAddress(address2);
//        purchase.setCustomer(customer1);
//        purchase.setOrder(order3);
//        purchase.setOrderItems(new HashSet<>());
//        purchase.setShippingAddress(address7);
//        checkoutController.placeOrder(purchase);
//    }

    @Test
    void testCreatePaymentIntent() throws Exception {
        when(this.checkoutService.createPaymentIntent((PaymentInfo) any())).thenReturn(new PaymentIntent());

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount(10);
        paymentInfo.setCurrency("GBP");
        paymentInfo.setReceiptEmail("jane.doe@example.org");
        String content = (new ObjectMapper()).writeValueAsString(paymentInfo);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/checkout/payment-intent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.checkoutController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(
                        MockMvcResultMatchers.content()
                                .string("{\n" + "  \"amount\": null,\n" + "  \"amount_capturable\": null,\n"
                                        + "  \"amount_received\": null,\n" + "  \"application\": null,\n"
                                        + "  \"application_fee_amount\": null,\n" + "  \"automatic_payment_methods\": null,\n"
                                        + "  \"canceled_at\": null,\n" + "  \"cancellation_reason\": null,\n"
                                        + "  \"capture_method\": null,\n" + "  \"charges\": null,\n" + "  \"client_secret\": null,\n"
                                        + "  \"confirmation_method\": null,\n" + "  \"created\": null,\n" + "  \"currency\": null,\n"
                                        + "  \"customer\": null,\n" + "  \"description\": null,\n" + "  \"id\": null,\n"
                                        + "  \"invoice\": null,\n" + "  \"last_payment_error\": null,\n" + "  \"livemode\": null,\n"
                                        + "  \"metadata\": null,\n" + "  \"next_action\": null,\n" + "  \"object\": null,\n"
                                        + "  \"on_behalf_of\": null,\n" + "  \"payment_method\": null,\n"
                                        + "  \"payment_method_options\": null,\n" + "  \"payment_method_types\": null,\n"
                                        + "  \"processing\": null,\n" + "  \"receipt_email\": null,\n" + "  \"review\": null,\n"
                                        + "  \"setup_future_usage\": null,\n" + "  \"shipping\": null,\n" + "  \"source\": null,\n"
                                        + "  \"statement_descriptor\": null,\n" + "  \"statement_descriptor_suffix\": null,\n"
                                        + "  \"status\": null,\n" + "  \"transfer_data\": null,\n" + "  \"transfer_group\": null\n" + "}"));
    }

    @Test
    void testCreatePaymentIntent2() throws Exception {
        when(this.checkoutService.createPaymentIntent((PaymentInfo) any())).thenReturn(new PaymentIntent());

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount(10);
        paymentInfo.setCurrency("GBP");
        paymentInfo.setReceiptEmail("jane.doe@example.org");
        String content = (new ObjectMapper()).writeValueAsString(paymentInfo);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/checkout/payment-intent", "Uri Vars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.checkoutController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(
                        MockMvcResultMatchers.content()
                                .string("{\n" + "  \"amount\": null,\n" + "  \"amount_capturable\": null,\n"
                                        + "  \"amount_received\": null,\n" + "  \"application\": null,\n"
                                        + "  \"application_fee_amount\": null,\n" + "  \"automatic_payment_methods\": null,\n"
                                        + "  \"canceled_at\": null,\n" + "  \"cancellation_reason\": null,\n"
                                        + "  \"capture_method\": null,\n" + "  \"charges\": null,\n" + "  \"client_secret\": null,\n"
                                        + "  \"confirmation_method\": null,\n" + "  \"created\": null,\n" + "  \"currency\": null,\n"
                                        + "  \"customer\": null,\n" + "  \"description\": null,\n" + "  \"id\": null,\n"
                                        + "  \"invoice\": null,\n" + "  \"last_payment_error\": null,\n" + "  \"livemode\": null,\n"
                                        + "  \"metadata\": null,\n" + "  \"next_action\": null,\n" + "  \"object\": null,\n"
                                        + "  \"on_behalf_of\": null,\n" + "  \"payment_method\": null,\n"
                                        + "  \"payment_method_options\": null,\n" + "  \"payment_method_types\": null,\n"
                                        + "  \"processing\": null,\n" + "  \"receipt_email\": null,\n" + "  \"review\": null,\n"
                                        + "  \"setup_future_usage\": null,\n" + "  \"shipping\": null,\n" + "  \"source\": null,\n"
                                        + "  \"statement_descriptor\": null,\n" + "  \"statement_descriptor_suffix\": null,\n"
                                        + "  \"status\": null,\n" + "  \"transfer_data\": null,\n" + "  \"transfer_group\": null\n" + "}"));
    }

    @Test
    void testCreatePaymentIntent3() throws Exception {
        when(this.checkoutService.createPaymentIntent((PaymentInfo) any())).thenReturn(new PaymentIntent());

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount(10);
        paymentInfo.setCurrency("GBP");
        paymentInfo.setReceiptEmail("jane.doe@example.org");
        String content = (new ObjectMapper()).writeValueAsString(paymentInfo);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/checkout/payment-intent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.checkoutController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(
                        MockMvcResultMatchers.content()
                                .string("{\n" + "  \"amount\": null,\n" + "  \"amount_capturable\": null,\n"
                                        + "  \"amount_received\": null,\n" + "  \"application\": null,\n"
                                        + "  \"application_fee_amount\": null,\n" + "  \"automatic_payment_methods\": null,\n"
                                        + "  \"canceled_at\": null,\n" + "  \"cancellation_reason\": null,\n"
                                        + "  \"capture_method\": null,\n" + "  \"charges\": null,\n" + "  \"client_secret\": null,\n"
                                        + "  \"confirmation_method\": null,\n" + "  \"created\": null,\n" + "  \"currency\": null,\n"
                                        + "  \"customer\": null,\n" + "  \"description\": null,\n" + "  \"id\": null,\n"
                                        + "  \"invoice\": null,\n" + "  \"last_payment_error\": null,\n" + "  \"livemode\": null,\n"
                                        + "  \"metadata\": null,\n" + "  \"next_action\": null,\n" + "  \"object\": null,\n"
                                        + "  \"on_behalf_of\": null,\n" + "  \"payment_method\": null,\n"
                                        + "  \"payment_method_options\": null,\n" + "  \"payment_method_types\": null,\n"
                                        + "  \"processing\": null,\n" + "  \"receipt_email\": null,\n" + "  \"review\": null,\n"
                                        + "  \"setup_future_usage\": null,\n" + "  \"shipping\": null,\n" + "  \"source\": null,\n"
                                        + "  \"statement_descriptor\": null,\n" + "  \"statement_descriptor_suffix\": null,\n"
                                        + "  \"status\": null,\n" + "  \"transfer_data\": null,\n" + "  \"transfer_group\": null\n" + "}"));
    }

    @Test
    void testCreatePaymentIntent4() throws Exception {
        when(this.checkoutService.createPaymentIntent((PaymentInfo) any())).thenReturn(new PaymentIntent());

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount(10);
        paymentInfo.setCurrency("GBP");
        paymentInfo.setReceiptEmail("jane.doe@example.org");
        String content = (new ObjectMapper()).writeValueAsString(paymentInfo);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/checkout/payment-intent", "Uri Vars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.checkoutController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(
                        MockMvcResultMatchers.content()
                                .string("{\n" + "  \"amount\": null,\n" + "  \"amount_capturable\": null,\n"
                                        + "  \"amount_received\": null,\n" + "  \"application\": null,\n"
                                        + "  \"application_fee_amount\": null,\n" + "  \"automatic_payment_methods\": null,\n"
                                        + "  \"canceled_at\": null,\n" + "  \"cancellation_reason\": null,\n"
                                        + "  \"capture_method\": null,\n" + "  \"charges\": null,\n" + "  \"client_secret\": null,\n"
                                        + "  \"confirmation_method\": null,\n" + "  \"created\": null,\n" + "  \"currency\": null,\n"
                                        + "  \"customer\": null,\n" + "  \"description\": null,\n" + "  \"id\": null,\n"
                                        + "  \"invoice\": null,\n" + "  \"last_payment_error\": null,\n" + "  \"livemode\": null,\n"
                                        + "  \"metadata\": null,\n" + "  \"next_action\": null,\n" + "  \"object\": null,\n"
                                        + "  \"on_behalf_of\": null,\n" + "  \"payment_method\": null,\n"
                                        + "  \"payment_method_options\": null,\n" + "  \"payment_method_types\": null,\n"
                                        + "  \"processing\": null,\n" + "  \"receipt_email\": null,\n" + "  \"review\": null,\n"
                                        + "  \"setup_future_usage\": null,\n" + "  \"shipping\": null,\n" + "  \"source\": null,\n"
                                        + "  \"statement_descriptor\": null,\n" + "  \"statement_descriptor_suffix\": null,\n"
                                        + "  \"status\": null,\n" + "  \"transfer_data\": null,\n" + "  \"transfer_group\": null\n" + "}"));
    }
}

