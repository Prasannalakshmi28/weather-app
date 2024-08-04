package com.weather.api.weather_api_app.service;

import com.weather.api.weather_api_app.model.WeatherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class WeatherServiceTests {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWeather() {
        // Arrange
        String location = "New York";
        WeatherResponse mockResponse = new WeatherResponse();
        WeatherResponse.Current current = new WeatherResponse.Current();
        current.setTemperature(25.0);
        current.setWeather_descriptions("Sunny");
        mockResponse.setCurrent(current);
        when(restTemplate.getForObject(anyString(), WeatherResponse.class)).thenReturn(mockResponse);

        // Act
        WeatherResponse response = weatherService.getWeather(location);

        // Assert
        assertEquals(25.0, response.getCurrent().getTemperature());
        assertEquals("Sunny", response.getCurrent().getWeather_descriptions());
    }
}
