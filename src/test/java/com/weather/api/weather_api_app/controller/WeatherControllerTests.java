package com.weather.api.weather_api_app.controller;


import com.weather.api.weather_api_app.model.WeatherResponse;
import com.weather.api.weather_api_app.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class WeatherControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();
    }

    @Test
    void testGetWeather() throws Exception {
        // Arrange
        String location = "New York";
        WeatherResponse mockResponse = new WeatherResponse();
        WeatherResponse.Current current = new WeatherResponse.Current();
        current.setTemperature(25.0);
        current.setWeather_descriptions("Sunny");
        mockResponse.setCurrent(current);

        when(weatherService.getWeather(location)).thenReturn(mockResponse);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/weather")
                        .param("location", location)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.current.temperature").value(25.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.current.weather_descriptions").value("Sunny"));
    }

}
