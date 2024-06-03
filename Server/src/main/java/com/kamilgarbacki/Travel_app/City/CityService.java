package com.kamilgarbacki.Travel_app.City;

import com.kamilgarbacki.Travel_app.Logs.LogsController;
import com.kamilgarbacki.Travel_app.Logs.NewLogRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final LogsController logsController;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public void addCity(NewCityRequest request) {
        City city = new City();
        city.setName(request.name());
        cityRepository.save(city);

        String message = "Created City: " + city;
        NewLogRequest logRequest = new NewLogRequest(message);
        logsController.addLog(logRequest);
    }

    public void deleteCity(Long cityId) {
        City city = cityRepository.findById(cityId).orElse(null);
        if (city != null) {
            String message = "Deleted City: " + city;
            NewLogRequest logRequest = new NewLogRequest(message);
            logsController.addLog(logRequest);
        }
        cityRepository.deleteById(cityId);
    }

    public void updateCity(Long cityId, NewCityRequest request) {
        City city = cityRepository.findById(cityId).orElseThrow(()-> new IllegalStateException("Train station with cityId: " + cityId + "does not exist"));
        String odlCity = city.toString();
        if (request.name() != null && !request.name().isBlank()) {
            city.setName(request.name());
        }
        cityRepository.save(city);
        String message = "Updated City from: " + odlCity + " to: " + city;
        NewLogRequest logRequest = new NewLogRequest(message);
        logsController.addLog(logRequest);
    }

    public City getCityById(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(()-> new IllegalStateException("Train station with cityId: " + cityId + "does not exist"));
    }

    public City getCityByName(String cityName) {
        return cityRepository.findByName(cityName).orElseThrow(()-> new IllegalStateException("Train station with cityId: " + cityName + "does not exist"));
    }
}
