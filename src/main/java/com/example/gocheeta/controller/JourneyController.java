package com.example.gocheeta.controller;

import com.example.gocheeta.dto.JourneyDto;
import com.example.gocheeta.model.Journey;
import com.example.gocheeta.model.Vehicle;
import com.example.gocheeta.service.JourneyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/journey")
@AllArgsConstructor
public class JourneyController {

    private JourneyService journeyService;

    @PostMapping("/create")
    public ResponseEntity<Journey> createJourney(@RequestBody JourneyDto journeyDto){

        HttpStatus response = HttpStatus.EXPECTATION_FAILED;
        Journey journey = new Journey();

        if(journeyDto == null){
           response = HttpStatus.BAD_REQUEST;
        }else{
            journey = journeyService.BookJourney(journeyDto);
            response =HttpStatus.CREATED;
        }

        return new ResponseEntity<>(journey, response);


    }


    @PostMapping("/finish")
    public ResponseEntity<Journey> finishJourney(@RequestParam(name = "id") String id){

        HttpStatus response = HttpStatus.EXPECTATION_FAILED;
        Journey journey = new Journey();

        if(id == null){
            response = HttpStatus.BAD_REQUEST;
        }else{
            journey = journeyService.closeJourney(Long.valueOf(id));
            response =HttpStatus.CREATED;
        }

        return new ResponseEntity<>(journey,response);
    }

    @PostMapping("/cancel")
    public ResponseEntity<Journey> cancelJourney(@RequestParam(name = "id") String id){

        HttpStatus response = HttpStatus.EXPECTATION_FAILED;
        Journey journey = new Journey();

        if(id == null){
            response = HttpStatus.BAD_REQUEST;
        }else{
            journey = journeyService.cancelJourney(Long.valueOf(id));
            response =HttpStatus.CREATED;
        }

        return new ResponseEntity<>(journey,response);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Journey>>  findAll(){
        return new ResponseEntity<>( journeyService.findAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/findByUser")
    public ResponseEntity<List<Journey>> getJourneyByUserId(@RequestParam(name = "id") String id){
        return new ResponseEntity<>(journeyService.findByUser(Long.parseLong(id)),HttpStatus.OK);
    }

    @GetMapping("/findByVehicleType")
    public ResponseEntity<List<Journey>> getJourneyByVehicleType(@RequestParam(name = "vehicle") String vehicle){
        return new ResponseEntity<>(journeyService.findByVehicleType(vehicle),HttpStatus.OK);
    }


    @GetMapping("/findByVehicle")
    public ResponseEntity<List<Journey>> getJourneyByVehicle(@RequestParam(name = "id") String id){
        return new ResponseEntity<>(journeyService.findByBVehicle(Long.parseLong(id)),HttpStatus.OK);
    }


    @GetMapping("/findByStatusAndDriver/{status}/{id}")
    public ResponseEntity<List<Journey>> getJourneyByStatusAndDriver(@PathVariable String status,@PathVariable String id){
        return new ResponseEntity<>(journeyService.findAllByStatusAndDriver(status,Long.parseLong(id)),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Journey> delete(@RequestParam(name = "id") long id){
        return new ResponseEntity<>(journeyService.delete(id),HttpStatus.OK);
    }




}
