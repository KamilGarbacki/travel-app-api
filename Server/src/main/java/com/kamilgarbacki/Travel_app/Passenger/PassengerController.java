package com.kamilgarbacki.Travel_app.Passenger;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/passenger")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public List<Passenger> getAllPassengers(){
        return passengerService.getAllPassengers();
    }

    @PostMapping
    public void addPassenger(@RequestBody NewPassengerRequest request){
        passengerService.addPassenger(request);
    }

    @DeleteMapping("/{passengerId}")
    public void deletePassenger(@PathVariable("passengerId") Long passengerId){
        passengerService.deletePassenger(passengerId);
    }

    @PatchMapping("/{passengerId}")
    public void updatePassenger(@PathVariable("passengerId") Long passengerId,
                                @RequestBody NewPassengerRequest request){
        passengerService.updatePassenger(passengerId, request);
    }

    @GetMapping("/id/{passengerId}")
    public Passenger getPassengerById(@PathVariable("passengerId") Long passengerId){
        return passengerService.getPassengerById(passengerId);
    }

    @GetMapping("/email/{passengerEmail}")
    public Passenger getPassengerByEmail(@PathVariable("passengerEmail") String passengerEmail){
        return passengerService.getPassengerByEmail(passengerEmail);
    }
}
