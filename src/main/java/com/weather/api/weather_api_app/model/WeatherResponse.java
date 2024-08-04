package com.weather.api.weather_api_app.model;

import lombok.Data;

@Data
public class WeatherResponse {

    private Current current;

    @Data
    public static class Current {
        private double temperature;
        private String weather_descriptions;
    }
}
