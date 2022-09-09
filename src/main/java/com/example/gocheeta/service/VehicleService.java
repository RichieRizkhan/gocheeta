package com.example.gocheeta.service;

import com.example.gocheeta.model.Journey;
import com.example.gocheeta.model.Vehicle;
import com.example.gocheeta.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VehicleService {

    private VehicleRepository vehicleRepository;

    //save vehicle
    public Vehicle saveVehicle(Vehicle vehicle){return vehicleRepository.save(vehicle);}

    //findAll
    public List<Vehicle> findAll(){return vehicleRepository.findAll();}

    //findAll By type
    public List<Vehicle> findAllByType(String type){ return vehicleRepository.findAllByType(type);}

    //findAll BY Status
    public List<Vehicle> findAllBYStatus(String status){ return vehicleRepository.findAllByStatus(status);}

    //find all by status and type
    public  List<Vehicle> findAllByTypeAndStatus( String type, String status){ return vehicleRepository.findAllByTypeAndStatus(type,status);}

    //find by id
    public Vehicle findById(long id){ return vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("vehicle not found key"));}

    public List<Vehicle> findByDriver(long id){
        return vehicleRepository.findAllByDriver_UserID(id);
    }

    public Vehicle delete(long id){
        Vehicle deleted = vehicleRepository.findById(id).orElseThrow(()->new RuntimeException("vehicle not found"));
        vehicleRepository.deleteById(id);
        return deleted;
    }


}
