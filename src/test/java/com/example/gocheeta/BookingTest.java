package com.example.gocheeta;

import com.example.gocheeta.model.*;
import com.example.gocheeta.repository.JourneyRepository;
import com.example.gocheeta.service.JourneyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookingTest {

    JourneyService journeyService;
    JourneyRepository journeyRepository;
    Driver driver = new Driver();
    Customer customer = new Customer();
    Vehicle vehicle = new Vehicle();

    Journey journey = new Journey();

    @BeforeEach
    public void setUp(){

        vehicle.setDriver(new Driver());
        vehicle.setStatus("free");
        vehicle.setNumberPlate("ka 1234");
        vehicle.setType("car");

        customer.setUsername("test");
        customer.setFullName("test customer");
        customer.setEmail("test@gmail.com");
        customer.setRole("customer");
        customer.setPassword("12345");
    }

    @Test
    public void createJourney(){
        journey.setUser(customer);
        journey.setVehicle(vehicle);
        journey.setPickup("athurugiriya");
        journey.setDestination("mount lavinia");
        journey.setPrice(1000);

        Journey response = journeyRepository.save(journey);

        Assertions.assertEquals("ka 1234",response.getVehicle().getNumberPlate());
    }
}
