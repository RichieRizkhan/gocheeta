package com.example.gocheeta.service;

import com.example.gocheeta.dto.JourneyDto;
import com.example.gocheeta.model.Journey;
import com.example.gocheeta.model.User;
import com.example.gocheeta.model.Vehicle;
import com.example.gocheeta.repository.JourneyRepository;
import com.example.gocheeta.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JourneyService {

    private JourneyRepository journeyRepository;
    private UserRepository userRepository;
    private VehicleService vehicleService;

    public Journey BookJourney(JourneyDto journeyDto){

        Journey journey = new Journey();

        journey.setPickup(journeyDto.getPickup());
        journey.setDestination(journeyDto.getDestination());
        journey.setDistance(journeyDto.getDistance());
        journey.setPrice(journeyDto.getPrice());
        journey.setStatus("ongoing");
        Vehicle vehicle =vehicleService.findById(journeyDto.getVehicle());
        if(vehicle.getStatus().equalsIgnoreCase("busy")){
            throw new RuntimeException("vehicle is busy");
        }
        User user = userRepository.findById(journeyDto.getUser()).orElseThrow(() -> new RuntimeException("user not found key"));

        vehicle.setStatus("busy");
        vehicleService.saveVehicle(vehicle);

        journey.setVehicle(vehicleService.findById(journeyDto.getVehicle()));
        journey.setUser(user);


        return journeyRepository.save(journey);

    }

    public Journey closeJourney(long id){

        Journey journey = journeyRepository.findById(id).orElseThrow(() -> new RuntimeException("journey not found key"));
        Vehicle vehicle = journey.getVehicle();
        vehicle.setStatus("free");
        vehicleService.saveVehicle(vehicle);

        journey.setStatus("done");
        return journeyRepository.save(journey);
    }

    public Journey cancelJourney(long id){

        Journey journey = journeyRepository.findById(id).orElseThrow(() -> new RuntimeException("journey not found key"));
        Vehicle vehicle = journey.getVehicle();
        vehicle.setStatus("free");
        vehicleService.saveVehicle(vehicle);

        journey.setStatus("canceled");
        return journeyRepository.save(journey);
    }

    public List<Journey> findByUser(long id){
        return journeyRepository.findAllByUser_UserID(id);
    }

    public List<Journey> findByVehicleType(String vehicle){
        return journeyRepository.findAllByVehicle_Type(vehicle);
    }

    public List<Journey> findByBVehicle(long id){
        return journeyRepository.findAllByVehicle_Id(id);
    }

    public Journey delete(long id){
        Journey deleted = journeyRepository.findById(id).orElseThrow(()-> new RuntimeException("journey not found"));
        journeyRepository.deleteById(id);
        return deleted;
    }


    public List<Journey> findAll() {
        return journeyRepository.findAll();
    }

    public List<Journey> findAllByStatusAndDriver(String status, long id){
        return journeyRepository.findAllByStatusAndVehicle_Driver_UserID(status,id);
    }
}
