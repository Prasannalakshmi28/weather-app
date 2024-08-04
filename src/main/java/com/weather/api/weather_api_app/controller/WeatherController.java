package com.weather.api.weather_api_app.controller;

import com.weather.api.weather_api_app.model.WeatherResponse;
import com.weather.api.weather_api_app.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public WeatherResponse getWeather(@RequestParam String location) {
        return weatherService.getWeather(location);
    }
}
