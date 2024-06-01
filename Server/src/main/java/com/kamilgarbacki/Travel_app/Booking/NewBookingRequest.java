package com.kamilgarbacki.Travel_app.Booking;

import java.time.LocalDate;

public record NewBookingRequest(Long passengerId,
                                Long connectionId,
                                LocalDate bookingDate) {
}
