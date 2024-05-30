package com.kamilgarbacki.Travel_app.Passenger;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByEmail(String passengerEmail);
}
