package com.kamilgarbacki.Travel_app.Booking;

import com.kamilgarbacki.Travel_app.Passenger.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    public List<Booking> findAllByPassenger(Passenger passenger);
}
