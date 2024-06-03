package com.kamilgarbacki.Travel_app.Connection;

import com.kamilgarbacki.Travel_app.City.City;
import com.kamilgarbacki.Travel_app.City.CityService;
import com.kamilgarbacki.Travel_app.Operator.Operator;
import com.kamilgarbacki.Travel_app.Operator.OperatorService;
import com.kamilgarbacki.Travel_app.TrainStation.TrainStation;
import com.kamilgarbacki.Travel_app.TrainStation.TrainStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConnectionService {

    private final ConnectionRepository connectionRepository;
    private final OperatorService operatorService;
    private final TrainStationService trainStationService;
    private final CityService cityService;

    public List<Connection> getAllConnections() {
        return connectionRepository.findAll();
    }

    public void addConnection(NewConnectionRequest request) {
        Operator operator = operatorService.getOperatorById(request.operatorId());

        TrainStation departureStation = trainStationService.getTrainStationById(request.departureId());

        TrainStation destinationStation = trainStationService.getTrainStationById(request.destinationId());

        Connection connection = Connection.builder().operator(operator).departureStation(departureStation).destinationStation(destinationStation)
                .departureTime(request.departureTime()).arrivalTime(request.arrivalTime()).price(request.price()).build();

        connectionRepository.save(connection);
    }

    public void deleteConnection(Long connectionId) {
        connectionRepository.deleteById(connectionId);
    }

    public void updateConnection(Long connectionId, NewConnectionRequest request) {
        Connection connection = connectionRepository.findById(connectionId)
                .orElseThrow(()-> new IllegalStateException("Operator with id: " + connectionId + "does not exist"));

        if(request.operatorId() != null){
            Operator operator = operatorService.getOperatorById(request.operatorId());
            connection.setOperator(operator);
        }
        if(request.departureId() != null){
            TrainStation departureStation = trainStationService.getTrainStationById(request.departureId());
            connection.setDepartureStation(departureStation);
        }
        if(request.destinationId() != null){
            TrainStation destinationStation = trainStationService.getTrainStationById(request.destinationId());
            connection.setDestinationStation(destinationStation);
        }
        if(request.arrivalTime() != null){
            connection.setArrivalTime(request.arrivalTime());
        }
        if(request.departureTime() != null){
            connection.setDepartureTime(request.departureTime());
        }
        if(request.price() != null){
            connection.setPrice(request.price());
        }
        connectionRepository.save(connection);
    }

    public Connection getConnection(Long connectionId) {
        return connectionRepository.findById(connectionId)
                .orElseThrow(()-> new IllegalStateException("Operator with id: " + connectionId + "does not exist"));
    }

    public List<Connection> getConnectionsByDepartureAndDestination(Long departureId, Long destinationId) {
        if(departureId == 0 && destinationId == 0){
            return getAllConnections();
        }

        if(departureId != 0 && destinationId == 0){
            TrainStation departureStation = trainStationService.getTrainStationById(departureId);
            return connectionRepository.findAllByDepartureStation(departureStation);
        }
        if(departureId == 0 && destinationId != 0){
            TrainStation destinationStation = trainStationService.getTrainStationById(destinationId);
            return connectionRepository.findAllByDestinationStation(destinationStation);
        }

        TrainStation departureStation = trainStationService.getTrainStationById(departureId);
        TrainStation destinationStation = trainStationService.getTrainStationById(destinationId);

        return connectionRepository.findAllByDepartureStationAndDestinationStation(departureStation, destinationStation);
    }

    public List<Connection> getConnectionsByCities(Long departureCityId, Long destinationCityId) {
        if(departureCityId == 0 && destinationCityId == 0){
            return getAllConnections();
        }

        List<Connection> connections = new ArrayList<>();

        if(departureCityId != 0 && destinationCityId == 0){
            City city = cityService.getCityById(departureCityId);
            List<TrainStation> stations = city.getTrainStations();
            for(TrainStation station : stations){
                connections.addAll(connectionRepository.findAllByDepartureStation(station));
            }
            return connections;
        }
        if(departureCityId == 0 && destinationCityId != 0){
            City city = cityService.getCityById(destinationCityId);
            List<TrainStation> stations = city.getTrainStations();
            for(TrainStation station : stations){
                connections.addAll(connectionRepository.findAllByDestinationStation(station));
            }
            return connections;
        }

        City departureCity = cityService.getCityById(departureCityId);
        City destinationCity = cityService.getCityById(destinationCityId);

        List<TrainStation> departureStations = departureCity.getTrainStations();
        List<TrainStation> destinationStations = destinationCity.getTrainStations();

        for(TrainStation departureStation : departureStations){
            for(TrainStation destinationStation : destinationStations){
                if(!(connectionRepository.findAllByDepartureStationAndDestinationStation(departureStation, destinationStation)).isEmpty()){
                    connections.addAll(connectionRepository.findAllByDepartureStationAndDestinationStation(departureStation, destinationStation));
                }
            }
        }
        return connections;
    }
}

