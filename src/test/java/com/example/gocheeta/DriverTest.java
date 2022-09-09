package com.example.gocheeta;

import com.example.gocheeta.model.Driver;
import com.example.gocheeta.model.User;
import com.example.gocheeta.repository.CustomerRepository;
import com.example.gocheeta.repository.DriverRepository;
import com.example.gocheeta.repository.UserRepository;
import com.example.gocheeta.service.UserService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@AllArgsConstructor
public class DriverTest {

    Driver driver;
    CustomerRepository customerRepository;
    UserRepository userRepository;
    DriverRepository driverRepository;

    UserService userService;


    @Test
    void testDriverCreate(){


        driver.setUsername("test");
        driver.setFullName("test driver");
        driver.setEmail("test@gmail.com");
        driver.setBranch("athurugiriya");
        driver.setRole("driver");
        driver.setPassword("12345");

        Driver response = userService.saveDriver(driver);

        Assertions.assertEquals(driver.getFullName(),response.getFullName());
    }
}
