package com.kamilgarbacki.Travel_app.TrainStation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainStationRepository extends JpaRepository<TrainStation, Long> {
    Optional<TrainStation> findByName(String trainStationName);
}
