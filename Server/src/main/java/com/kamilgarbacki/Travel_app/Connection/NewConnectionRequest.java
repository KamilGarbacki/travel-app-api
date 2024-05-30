package com.kamilgarbacki.Travel_app.Connection;

import java.time.LocalTime;

public record NewConnectionRequest(Long operatorId,
                                   Long departureId,
                                   Long destinationId,
                                   LocalTime departureTime,
                                   LocalTime arrivalTime,
                                   Integer price) {}
