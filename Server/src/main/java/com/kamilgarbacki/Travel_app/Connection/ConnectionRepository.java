package com.kamilgarbacki.Travel_app.Connection;

import com.kamilgarbacki.Travel_app.TrainStation.TrainStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    List<Connection> findAllByDepartureStationAndDestinationStation(TrainStation departureStation, TrainStation destinationStation);
    List<Connection> findAllByDepartureStation(TrainStation departureStation);
    List<Connection> findAllByDestinationStation(TrainStation destinationStation);
}
