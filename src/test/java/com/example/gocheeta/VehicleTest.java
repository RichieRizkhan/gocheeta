package com.example.gocheeta;

import com.example.gocheeta.model.Driver;
import com.example.gocheeta.model.Vehicle;
import com.example.gocheeta.repository.VehicleRepository;
import com.example.gocheeta.service.VehicleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VehicleTest {

    VehicleRepository vehicleRepository;
    VehicleService service;
    Vehicle vehicle = new Vehicle();
    Driver driver = new Driver();

    @BeforeEach
    public void setUp() {


        driver.setUsername("test");
        driver.setFullName("test driver");
        driver.setEmail("test@gmail.com");
        driver.setBranch("athurugiriya");
        driver.setRole("driver");
        driver.setPassword("12345");

    }

    @Test
    void testDriverCreate(){

        vehicle.setDriver(new Driver());
        vehicle.setStatus("free");
        vehicle.setNumberPlate("ka 1234");
        vehicle.setType("car");

        Vehicle response = service.saveVehicle(vehicle);

        Assertions.assertNotEquals(null,response.getNumberPlate());
    }

}
