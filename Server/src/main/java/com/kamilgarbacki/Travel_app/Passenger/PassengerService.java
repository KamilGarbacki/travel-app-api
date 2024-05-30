package com.kamilgarbacki.Travel_app.Passenger;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }
    public void addPassenger(NewPassengerRequest request) {
        Passenger passenger = new Passenger();
        passenger.setFName(request.fName());
        passenger.setLName(request.lName());
        passenger.setEmail(request.email());
        passenger.setPhone(request.phone());
        passengerRepository.save(passenger);
    }

    public void deletePassenger(Long passengerId) {
        passengerRepository.deleteById(passengerId);
    }

    public void updatePassenger(Long passengerId, NewPassengerRequest request) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(()-> new IllegalStateException("Passenger with id: " + passengerId + "does not exist"));

        if(request.fName() != null && !request.fName().isBlank()){
            passenger.setFName(request.fName());
        }
        if(request.lName() != null && !request.lName().isBlank()){
            passenger.setLName(request.lName());
        }
        if(request.email() != null && !request.email().isBlank()){
            passenger.setEmail(request.email());
        }
        if(request.phone() != null && !request.phone().isBlank()){
            passenger.setPhone(request.phone());
        }
        passengerRepository.save(passenger);
    }

    public Passenger getPassengerById(Long passengerId) {
        return passengerRepository.findById(passengerId)
                        .orElseThrow(()-> new IllegalStateException("Passenger with id: " + passengerId + "does not exist"));
    }

    public Passenger getPassengerByEmail(String passengerEmail) {
        return passengerRepository.findByEmail(passengerEmail)
                .orElseThrow(()-> new IllegalStateException("Passenger with email: " + passengerEmail + "does not exist"));
    }


}
