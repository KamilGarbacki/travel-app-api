package com.kamilgarbacki.Travel_app.City;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> getAllCities(){
        return cityService.getAllCities();
    }

    @PostMapping
    public void addCity(@RequestBody NewCityRequest request){
        cityService.addCity(request);
    }

    @DeleteMapping("/{cityId}")
    public void deleteCity(@PathVariable("cityId") Long cityId){
        cityService.deleteCity(cityId);
    }
    @PatchMapping("/{cityId}")
    public void updateCity(@PathVariable("cityId") Long cityId,
                           @RequestBody NewCityRequest request){
        cityService.updateCity(cityId, request);
    }

    @GetMapping("/id/{cityId}")
    public City getCityById(@PathVariable("cityId") Long cityId){
        return cityService.getCityById(cityId);
    }

    @GetMapping("/name/{cityName}")
    public City getCityByName(@PathVariable("cityName") String cityName){
        return cityService.getCityByName(cityName);
    }
}
