package com.kamilgarbacki.Travel_app.City;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public void addCity(NewCityRequest request) {
        City city = new City();
        city.setName(request.name());
        cityRepository.save(city);
    }

    public void deleteCity(Long cityId) {
        cityRepository.deleteById(cityId);
    }

    public void updateCity(Long cityId, NewCityRequest request) {
        City city = cityRepository.findById(cityId).orElseThrow(()-> new IllegalStateException("Train station with cityId: " + cityId + "does not exist"));
        if (request.name() != null && !request.name().isBlank()) {
            city.setName(request.name());
        }
        cityRepository.save(city);
    }

    public City getCityById(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(()-> new IllegalStateException("Train station with cityId: " + cityId + "does not exist"));
    }

    public City getCityByName(String cityName) {
        return cityRepository.findByName(cityName).orElseThrow(()-> new IllegalStateException("Train station with cityId: " + cityName + "does not exist"));
    }
}
