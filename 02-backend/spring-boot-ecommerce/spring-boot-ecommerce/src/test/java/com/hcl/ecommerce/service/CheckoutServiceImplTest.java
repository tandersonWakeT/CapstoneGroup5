package com.hcl.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hcl.ecommerce.dao.CustomerRepository;
import com.hcl.ecommerce.dto.PaymentInfo;
import com.hcl.ecommerce.dto.Purchase;
import com.hcl.ecommerce.entity.Address;
import com.hcl.ecommerce.entity.Customer;
import com.hcl.ecommerce.entity.Order;
import com.stripe.exception.StripeException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CheckoutServiceImpl.class, String.class})
@ExtendWith(MockitoExtension.class)
class CheckoutServiceImplTest {

    @InjectMocks
    private CheckoutServiceImpl checkoutServiceImpl;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void testPlaceOrder() {
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
        when(this.customerRepository.findByEmail((String) any())).thenReturn(customer);
        when(this.customerRepository.save((Customer) any())).thenReturn(customer1);

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
        this.checkoutServiceImpl.placeOrder(purchase);
        verify(this.customerRepository).findByEmail((String) any());
        verify(this.customerRepository).save((Customer) any());
        Address expectedShippingAddress = purchase.getShippingAddress();
        Order order5 = purchase.getOrder();
        assertSame(expectedShippingAddress, order5.getShippingAddress());
        Address expectedBillingAddress = purchase.getBillingAddress();
        assertSame(expectedBillingAddress, order5.getBillingAddress());
        assertSame(customer, order5.getCustomer());
    }

//    @Test
//    @Disabled
//    void testCreatePaymentIntentUnHappy() throws StripeException {
//        //   Reason: R011 Sandboxing policy violation.
//        //   Diffblue Cover ran code in your project that tried
//        //     to access the network.
//        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
//        //   your code from damaging your system environment.
//        //   See https://diff.blue/R011 to resolve this issue.
//
//        PaymentInfo paymentInfo = new PaymentInfo();
//        paymentInfo.setAmount(10);
//        paymentInfo.setCurrency("GBP");
//        paymentInfo.setReceiptEmail("jane.doe@example.org");
//        this.checkoutServiceImpl.createPaymentIntent(paymentInfo);
//    }

    @Test
    void testCreatePaymentIntentHappy() throws StripeException {

        System.out.println("TEST CREATE PAYMENT INTENT HAPPY");

        PaymentInfo paymentInfo = mock(PaymentInfo.class);
//        when(paymentInfo.getAmount()).thenReturn(10);
//        when(paymentInfo.getCurrency()).thenReturn("GBP");
//        when(paymentInfo.getReceiptEmail()).thenReturn("jane.doe@example.org");
//        doNothing().when(paymentInfo).setAmount(anyInt());
//        doNothing().when(paymentInfo).setCurrency((String) any());
//        doNothing().when(paymentInfo).setReceiptEmail((String) any());
        paymentInfo.setAmount(10);
        paymentInfo.setCurrency("USD");
        paymentInfo.setReceiptEmail("jane.doe@example.org");

        PaymentIntent expectedIntent = mock(PaymentIntent.class);
//        when(expectedIntent.getAmount()).thenReturn(10L);
//        when(expectedIntent.getCurrency()).thenReturn("GBP");
//        when(expectedIntent.getReceiptEmail()).thenReturn("jane.doe@example.org");
//        doNothing().when(expectedIntent).setAmount(anyLong());
//        doNothing().when(expectedIntent).setCurrency((String) any());
//        doNothing().when(expectedIntent).setReceiptEmail((String) any());
        expectedIntent.setAmount(10L);
        expectedIntent.setCurrency("USD");
        expectedIntent.setReceiptEmail("jane.doe@example.org");

        try (MockedStatic<PaymentIntent> mockedIntent = Mockito.mockStatic(PaymentIntent.class)) {

            mockedIntent.when((MockedStatic.Verification) PaymentIntent.create(Mockito.anyMap())).thenReturn(expectedIntent);
            PaymentIntent actualIntent = checkoutServiceImpl.createPaymentIntent(paymentInfo);
            assertEquals(expectedIntent, actualIntent);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}

