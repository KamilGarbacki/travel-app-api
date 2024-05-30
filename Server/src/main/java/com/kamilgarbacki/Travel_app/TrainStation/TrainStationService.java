package com.kamilgarbacki.Travel_app.TrainStation;

import com.kamilgarbacki.Travel_app.City.City;
import com.kamilgarbacki.Travel_app.City.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainStationService {
    private final TrainStationRepository trainStationRepository;
    private final CityService cityService;

    public List<TrainStation> getAllTrainStations() {
        return trainStationRepository.findAll();
    }

    public void addTrainStation(NewTrainStationRequest request) {
        City city = cityService.getCityById(request.cityId());
        TrainStation trainStation = TrainStation.builder().name(request.name()).city(city).build();
        trainStationRepository.save(trainStation);
    }

    public void deleteTrainStation(Long trainStationId) {
        trainStationRepository.deleteById(trainStationId);
    }

    public void updateTrainStation(Long trainStationId, NewTrainStationRequest request) {
        TrainStation trainStation = trainStationRepository.findById(trainStationId)
                .orElseThrow(()-> new IllegalStateException("Train station with id: " + trainStationId + "does not exist"));

        if(request.name() != null && !request.name().isBlank()) {
            trainStation.setName(request.name());
        }
        if(request.cityId() != null) {
            City city = cityService.getCityById(request.cityId());
            trainStation.setCity(city);
        }
        trainStationRepository.save(trainStation);
    }

    public TrainStation getTrainStationById(Long trainStationId) {
        return trainStationRepository.findById(trainStationId)
                .orElseThrow(()-> new IllegalStateException("Train station with id: " + trainStationId + "does not exist"));
    }

    public TrainStation getTrainStationByName(String trainStationName) {
        return trainStationRepository.findByName(trainStationName)
                .orElseThrow(()-> new IllegalStateException("Train station with name: " + trainStationName + "does not exist"));
    }


}
