package com.kamilgarbacki.Travel_app.TrainStation;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainStationService {
    private final TrainStationRepository trainStationRepository;

    public TrainStationService(TrainStationRepository trainStationRepository) {
        this.trainStationRepository = trainStationRepository;
    }

    public List<TrainStation> getAllTrainStations() {
        return trainStationRepository.findAll();
    }

    public void addTrainStation(NewTrainStationRequest request) {
        TrainStation trainStation = new TrainStation();
        trainStation.setName(request.name());
        trainStation.setCity(request.city());
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
        if(request.city() != null && !request.city().isBlank()) {
            trainStation.setCity(request.city());
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
