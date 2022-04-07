package com.hcl.ecommerce.dao;

import static org.junit.jupiter.api.Assertions.assertNull;

import com.hcl.ecommerce.entity.Customer;

import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {CustomerRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.hcl.ecommerce.entity"})
@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testFindByEmailHappy() {
        Customer customer = new Customer();
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setOrders(new HashSet<>());

        Customer customer1 = new Customer();
        customer1.setEmail("john.doe@example.org");
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setOrders(new HashSet<>());
        this.customerRepository.save(customer);
        this.customerRepository.save(customer1);

        Customer retrievedCustomer = this.customerRepository.findByEmail("jane.doe@example.org");
        System.out.println(retrievedCustomer.getFirstName() + " " + retrievedCustomer.getLastName());

        Assertions.assertTrue(retrievedCustomer.getEmail() == customer.getEmail());
    }

    @Test
    void testFindByEmailUnHappy() {
        Customer customer = new Customer();
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setOrders(new HashSet<>());

        Customer customer1 = new Customer();
        customer1.setEmail("john.doe@example.org");
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setOrders(new HashSet<>());
        this.customerRepository.save(customer);
        this.customerRepository.save(customer1);

        Customer retrievedCustomer = this.customerRepository.findByEmail("john.doe@example.org");
        System.out.println(retrievedCustomer.getFirstName() + " " + retrievedCustomer.getLastName());

        Assertions.assertFalse(retrievedCustomer.getEmail() == customer.getEmail());
    }

    @Test
    void testFindByEmailUnhappyNull() {
        Customer customer = new Customer();
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setOrders(new HashSet<>());

        Customer customer1 = new Customer();
        customer1.setEmail("jane.doe@example.org");
        customer1.setFirstName("Jane");
        customer1.setLastName("Doe");
        customer1.setOrders(new HashSet<>());
        this.customerRepository.save(customer);
        this.customerRepository.save(customer1);
        assertNull(this.customerRepository.findByEmail("foo"));
    }
}

