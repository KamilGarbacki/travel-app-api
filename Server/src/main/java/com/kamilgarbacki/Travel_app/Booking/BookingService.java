package com.kamilgarbacki.Travel_app.Booking;

import com.kamilgarbacki.Travel_app.Connection.Connection;
import com.kamilgarbacki.Travel_app.Connection.ConnectionService;
import com.kamilgarbacki.Travel_app.Exception.ResourceNotFound;
import com.kamilgarbacki.Travel_app.Logs.LogsController;
import com.kamilgarbacki.Travel_app.Logs.NewLogRequest;
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
    private final LogsController logsController;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void addBooking(NewBookingRequest request) {
        Passenger passenger = passengerService.getPassengerById(request.passengerId());
        Connection connection = connectionService.getConnection(request.connectionId());

        Booking booking = Booking.builder().passenger(passenger).connection(connection).bookingDate(request.bookingDate()).build();
        bookingRepository.save(booking);

        String message = "Created Booking: " + booking;
        NewLogRequest logRequest = new NewLogRequest(message);
        logsController.addLog(logRequest);
    }

    public void deleteBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking != null) {
            String message = "Created Booking: " + booking;
            NewLogRequest logRequest = new NewLogRequest(message);
            logsController.addLog(logRequest);
        }
        bookingRepository.deleteById(bookingId);
    }

    public void updateBooking(Long bookingId, NewBookingRequest request) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()-> new ResourceNotFound("Booking with id: " + bookingId + "does not exist"));

        String oldBooking = booking.toString();
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
        String message = "Updated Booking form: " + oldBooking + " to " + booking;
        NewLogRequest logRequest = new NewLogRequest(message);
        logsController.addLog(logRequest);
    }

    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(()-> new ResourceNotFound("Booking with id: " + bookingId + "does not exist"));
    }

    public List<Booking> getBookingsByPassengerId(Long passengerId) {
        Passenger passenger = passengerService.getPassengerById(passengerId);
        return bookingRepository.findAllByPassenger(passenger);
    }
}
