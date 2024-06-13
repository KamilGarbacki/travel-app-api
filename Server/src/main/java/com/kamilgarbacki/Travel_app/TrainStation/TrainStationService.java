package com.kamilgarbacki.Travel_app.TrainStation;

import com.kamilgarbacki.Travel_app.City.City;
import com.kamilgarbacki.Travel_app.City.CityService;
import com.kamilgarbacki.Travel_app.Exception.ResourceNotFound;
import com.kamilgarbacki.Travel_app.Logs.LogsController;
import com.kamilgarbacki.Travel_app.Logs.NewLogRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainStationService {
    private final TrainStationRepository trainStationRepository;
    private final CityService cityService;
    private final LogsController logsController;

    public List<TrainStation> getAllTrainStations() {
        return trainStationRepository.findAll();
    }

    public void addTrainStation(NewTrainStationRequest request) {
        City city = cityService.getCityById(request.cityId());
        TrainStation trainStation = TrainStation.builder().name(request.name()).city(city).build();
        trainStationRepository.save(trainStation);

        String message = "Created Train Station: " + trainStation;
        NewLogRequest logRequest = new NewLogRequest(message);
        logsController.addLog(logRequest);
    }

    public void deleteTrainStation(Long trainStationId) {
        TrainStation trainStation = trainStationRepository.findById(trainStationId).orElse(null);
        if (trainStation != null) {
            String message = "Deleted Train Station: " + trainStation;
            NewLogRequest logRequest = new NewLogRequest(message);
            logsController.addLog(logRequest);
        }
        trainStationRepository.deleteById(trainStationId);
    }

    public void updateTrainStation(Long trainStationId, NewTrainStationRequest request) {
        TrainStation trainStation = trainStationRepository.findById(trainStationId)
                .orElseThrow(()-> new ResourceNotFound("Train station with id: " + trainStationId + "does not exist"));

        String oldTrainStation = trainStation.toString();

        if(request.name() != null && !request.name().isBlank()) {
            trainStation.setName(request.name());
        }
        if(request.cityId() != null) {
            City city = cityService.getCityById(request.cityId());
            trainStation.setCity(city);
        }
        trainStationRepository.save(trainStation);

        String message = "Updated Train Station from:" + oldTrainStation + " to " + trainStation;
        NewLogRequest logRequest = new NewLogRequest(message);
        logsController.addLog(logRequest);
    }

    public TrainStation getTrainStationById(Long trainStationId) {
        return trainStationRepository.findById(trainStationId)
                .orElseThrow(()-> new ResourceNotFound("Train station with id: " + trainStationId + "does not exist"));
    }

    public TrainStation getTrainStationByName(String trainStationName) {
        return trainStationRepository.findByName(trainStationName)
                .orElseThrow(()-> new ResourceNotFound("Train station with name: " + trainStationName + "does not exist"));
    }

    public List<TrainStation> getTrainStationByCity(String cityName) {
        City city = cityService.getCityByName(cityName);
        return trainStationRepository.findAllByCity(city);
    }


}
