package com.weather.api.weather_api_app.service;

import com.weather.api.weather_api_app.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class WeatherService {

    @Value("${weatherstack.api.key}")
    private String apiKey;

    @Value("${weatherstack.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponse getWeather(String location) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("access_key", apiKey)
                .queryParam("query", location)
                .toUriString();

        return restTemplate.getForObject(url, WeatherResponse.class);
    }
}
