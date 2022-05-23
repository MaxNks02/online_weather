package com.example.online_weather.controller;

import com.example.online_weather.entity.Weather;
import com.example.online_weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class WeatherController {

    private  final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("cccc dd MMMM yyyy");

    private String newCity="Nukus";

    @Autowired
    WeatherService weatherService;

    @GetMapping("/index")
    public String get(Model model){
        LocalDate date = LocalDate.now();
        String text = date.format(formatter);
        model.addAttribute("date", text);
        model.addAttribute("weather", weatherService.currentWeather(newCity));
        return "index";



    }

    @PostMapping("/index")
    public String getWeather(@RequestParam String city){
        newCity=city;
         weatherService.currentWeather(city);
         return "redirect:/index";
    }

}
