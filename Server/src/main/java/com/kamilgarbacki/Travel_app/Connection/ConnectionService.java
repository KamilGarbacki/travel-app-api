package com.kamilgarbacki.Travel_app.Connection;

import com.kamilgarbacki.Travel_app.Operator.Operator;
import com.kamilgarbacki.Travel_app.Operator.OperatorService;
import com.kamilgarbacki.Travel_app.TrainStation.TrainStation;
import com.kamilgarbacki.Travel_app.TrainStation.TrainStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConnectionService {

    private final ConnectionRepository connectionRepository;
    private final OperatorService operatorService;
    private final TrainStationService trainStationService;

    public List<Connection> getAllConnections() {
        return connectionRepository.findAll();
    }

    public void addConnection(NewConnectionRequest request) {
        Operator operator = operatorService.getOperatorById(request.operatorId());

        TrainStation departureStation = trainStationService.getTrainStationById(request.departureId());

        TrainStation destinationStation = trainStationService.getTrainStationById(request.destinationId());

        Connection connection = Connection.builder().operator(operator).departureStation(departureStation).destinationStation(destinationStation)
                .departureTime(request.departureTime()).price(request.price()).build();

        connectionRepository.save(connection);
    }

    public void deleteConnection(Long connectionId) {
        connectionRepository.deleteById(connectionId);
    }

    public void updateConnection(Long connectionId, NewConnectionRequest request) {
        Connection connection = connectionRepository.findById(connectionId)
                .orElseThrow(()-> new IllegalStateException("Operator with id: " + connectionId + "does not exist"));
    }

    public Connection getConnection(Long connectionId) {
        return connectionRepository.findById(connectionId)
                .orElseThrow(()-> new IllegalStateException("Operator with id: " + connectionId + "does not exist"));
    }

    public List<Connection> getConnectionByDepartureAndDestination(Long departureId, Long destinationId) {
        if(departureId != null && destinationId == null){
            TrainStation departureStation = trainStationService.getTrainStationById(departureId);
            return connectionRepository.findAllByDepartureStation(departureStation);
        }
        if(departureId == null && destinationId != null){
            TrainStation destinationStation = trainStationService.getTrainStationById(destinationId);
            return connectionRepository.findAllByDestinationStation(destinationStation);
        }

        TrainStation departureStation = trainStationService.getTrainStationById(departureId);
        TrainStation destinationStation = trainStationService.getTrainStationById(destinationId);

        return connectionRepository.findAllByDepartureStationAndDestinationStation(departureStation, destinationStation);
    }
}
