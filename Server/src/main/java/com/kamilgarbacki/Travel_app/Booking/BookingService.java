package com.kamilgarbacki.Travel_app.Booking;

import com.kamilgarbacki.Travel_app.Connection.Connection;
import com.kamilgarbacki.Travel_app.Connection.ConnectionService;
import com.kamilgarbacki.Travel_app.Passenger.Passenger;
import com.kamilgarbacki.Travel_app.Passenger.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PassengerService passengerService;
    private final ConnectionService connectionService;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void addBooking(NewBookingRequest request) {
        Passenger passenger = passengerService.getPassengerById(request.passengerId());
        Connection connection = connectionService.getConnection(request.connectionId());

        Booking booking = Booking.builder().passenger(passenger).connection(connection).bookingDate(request.bookingDate()).build();
        bookingRepository.save(booking);
    }

    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    public void updateBooking(Long bookingId, NewBookingRequest request) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()-> new IllegalStateException("Booking with id: " + bookingId + "does not exist"));
        if(request.passengerId() != null){
            Passenger passenger = passengerService.getPassengerById(request.passengerId());
            booking.setPassenger(passenger);
        }
        if(request.connectionId() != null){
            Connection connection = connectionService.getConnection(request.connectionId());
            booking.setConnection(connection);
        }
        if(request.bookingDate() != null){
            booking.setBookingDate(request.bookingDate());
        }
        bookingRepository.save(booking);
    }

    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(()-> new IllegalStateException("Booking with id: " + bookingId + "does not exist"));
    }

    public List<Booking> getBookingsByPassengerId(Long passengerId) {
        Passenger passenger = passengerService.getPassengerById(passengerId);
        return bookingRepository.findAllByPassenger(passenger);
    }
}
