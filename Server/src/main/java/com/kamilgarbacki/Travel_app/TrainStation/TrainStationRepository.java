package com.kamilgarbacki.Travel_app.TrainStation;

import com.kamilgarbacki.Travel_app.City.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainStationRepository extends JpaRepository<TrainStation, Long> {
    Optional<TrainStation> findByName(String trainStationName);
    List<TrainStation> findAllByCity(City city);
}
