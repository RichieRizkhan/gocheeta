package com.example.gocheeta.controller;

import com.example.gocheeta.dto.CustomerDto;
import com.example.gocheeta.dto.DriverDto;
import com.example.gocheeta.dto.LoginDto;
import com.example.gocheeta.model.Customer;
import com.example.gocheeta.model.Driver;
import com.example.gocheeta.model.User;
import com.example.gocheeta.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private UserService userService;

    @PostMapping("/customer/signup")
    public ResponseEntity<Customer> customerRegister(@RequestBody CustomerDto customerDto){


        HttpStatus response = HttpStatus.EXPECTATION_FAILED;
        Customer customer = new Customer();

        if(customerDto == null){
            response = HttpStatus.BAD_REQUEST;
        }else {

            customer.setUsername(customerDto.getUsername());
            customer.setFullName(customerDto.getFullName());
            customer.setEmail(customerDto.getEmail());
            customer.setPassword(customerDto.getPassword());
            customer.setRole("customer");
            customer.setTelephone(customerDto.getTelephone());
            response=HttpStatus.CREATED;
        }
        return new ResponseEntity<>(userService.saveCustomer(customer), response);


    }

    @PostMapping("/driver/create")
    public ResponseEntity<Driver> driverRegistration(@RequestBody DriverDto driverDto){

        HttpStatus response = HttpStatus.EXPECTATION_FAILED;
        Driver driver = new Driver();

        if(driverDto == null){
            response = HttpStatus.BAD_REQUEST;
        }
        else {
            driver.setUserID(driverDto.getUserID());
            driver.setUsername(driverDto.getUsername());
            driver.setFullName(driverDto.getFullName());
            driver.setEmail(driverDto.getEmail());
            driver.setPassword(driverDto.getPassword());
            driver.setRole("driver");
            driver.setBranch(driverDto.getBranch());
            response = HttpStatus.CREATED;
            // TODO: 8/9/2022  save vehicle
        }

        return new ResponseEntity<>(userService.saveDriver(driver),response);

    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginDto loginDto){

        User user = new User();
        HttpStatus response = HttpStatus.CREATED;

        if(loginDto.getUsername()=="" || loginDto.getUsername()==null){
            response = HttpStatus.BAD_REQUEST;
        }else{

            user = userService.findByUsername(loginDto.getUsername());

            if(user == null){
                response = HttpStatus.NOT_FOUND;
            }else{
                if(user.getPassword().equals(loginDto.getPassword())){
                    response = HttpStatus.ACCEPTED;
                }else{
                    response = HttpStatus.BAD_REQUEST;
                }
            }




        }

        return new ResponseEntity<>(user,response);

    }

    @GetMapping("/byRole")
    public ResponseEntity<List<User>> findAllByRole(@RequestParam(name="role") String role){
        return new ResponseEntity<>(userService.findAllByRole(role),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteUser(@RequestParam(name="id") long id){
        return new ResponseEntity<>(userService.deleteUser(id),HttpStatus.OK);
    }
}
