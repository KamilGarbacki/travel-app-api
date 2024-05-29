package com.kamilgarbacki.Travel_app.TrainStation;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/trainStation")
public class TrainStationController {

    private final TrainStationService trainStationService;

    public TrainStationController(TrainStationService trainStationService) {
        this.trainStationService = trainStationService;
    }
    @GetMapping
    public List<TrainStation> getAllTrainStations() {
        return trainStationService.getAllTrainStations();
    }
    @PostMapping
    public void addTrainStation(@RequestBody NewTrainStationRequest request) {
        trainStationService.addTrainStation(request);
    }
    @DeleteMapping("/{trainStationId}")
    public void deleteTrainStation(@PathVariable("trainStationId") Long trainStationId){
        trainStationService.deleteTrainStation(trainStationId);
    }

    @PatchMapping("/{trainStationId}")
    public void updateTrainStation(@PathVariable("trainStationId") Long trainStationId,
                                   @RequestBody NewTrainStationRequest request) {
        trainStationService.updateTrainStation(trainStationId, request);
    }
    @GetMapping("/id/{trainStationId}")
    public TrainStation getTrainStationById(@PathVariable("trainStationId") Long trainStationId){
        return trainStationService.getTrainStationById(trainStationId);
    }

    @GetMapping("/name/{trainStationName}")
    public TrainStation getTrainStationByName(@PathVariable("trainStationName") String trainStationName) {
        return trainStationService.getTrainStationByName(trainStationName);
    }
}
