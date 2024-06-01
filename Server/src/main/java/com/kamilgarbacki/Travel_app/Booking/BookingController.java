package com.kamilgarbacki.Travel_app.Booking;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping
    public List<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }

    @PostMapping
    public void addBooking(@RequestBody NewBookingRequest request){
        bookingService.addBooking(request);
    }

    @DeleteMapping("/{bookingId}")
    public void deleteBooking(@PathVariable("bookingId") Long bookingId){
        bookingService.deleteBooking(bookingId);
    }

    @PatchMapping("/{bookingId}")
    public void updateBooking(@PathVariable("bookingId") Long bookingId,
                              @RequestBody NewBookingRequest request){
        bookingService.updateBooking(bookingId, request);
    }

    @GetMapping("/id/{bookingId}")
    public Booking getBookingById(@PathVariable("bookingId") Long bookingId){
        return bookingService.getBookingById(bookingId);
    }

    @GetMapping("/passengerId/{passengerId}")
    public List<Booking> getBookingsByPassengerId(@PathVariable("passengerId") Long passengerId){
        return bookingService.getBookingsByPassengerId(passengerId);
    }
}
