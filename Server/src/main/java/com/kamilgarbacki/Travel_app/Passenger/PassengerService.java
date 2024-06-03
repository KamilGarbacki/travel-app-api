package com.kamilgarbacki.Travel_app.Passenger;

import com.kamilgarbacki.Travel_app.Logs.LogsController;
import com.kamilgarbacki.Travel_app.Logs.NewLogRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final LogsController logsController;

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

        String message = "Created Passenger: " + passenger;
        NewLogRequest logRequest = new NewLogRequest(message);
        logsController.addLog(logRequest);
    }

    public void deletePassenger(Long passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId).get();
        String message = "Deleted Passenger: " + passenger;
        NewLogRequest logRequest = new NewLogRequest(message);
        logsController.addLog(logRequest);
        passengerRepository.deleteById(passengerId);
    }

    public void updatePassenger(Long passengerId, NewPassengerRequest request) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(()-> new IllegalStateException("Passenger with id: " + passengerId + "does not exist"));

        String oldPassenger = passenger.toString();

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

        String message = "Updated Passenger from: " + oldPassenger + " to " + passenger.toString();
        NewLogRequest logRequest = new NewLogRequest(message);
        logsController.addLog(logRequest);
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
