package com.example.gocheeta;

import com.example.gocheeta.model.Customer;
import com.example.gocheeta.model.Driver;
import com.example.gocheeta.repository.CustomerRepository;
import com.example.gocheeta.repository.DriverRepository;
import com.example.gocheeta.repository.UserRepository;
import com.example.gocheeta.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    Customer customer;
    CustomerRepository customerRepository;
    UserRepository userRepository;
    DriverRepository driverRepository;

    UserService userService;

    @Test
    void testCustomerCreate(){

        customer.setUsername("test");
        customer.setFullName("test customer");
        customer.setEmail("test@gmail.com");
        customer.setRole("customer");
        customer.setPassword("12345");

        Customer response = userService.saveCustomer(customer);

        Assertions.assertEquals(customer.getFullName(),response.getFullName());
    }
}
