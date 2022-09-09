package com.example.gocheeta.repository;

import com.example.gocheeta.model.Journey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JourneyRepository extends JpaRepository<Journey,Long> {

    List<Journey> findAllByUser_UserID(long id);
    List<Journey> findAllByVehicle_Type(String type);
    List<Journey> findAllByVehicle_Id(long id);
    List<Journey> findAllByStatusAndVehicle_Driver_UserID(String status,long id);

}
