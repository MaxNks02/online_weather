package com.example.online_weather.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

        private String weather;
        private String description;
        private String country;
        private String city;
        private Integer temp;
        private Float wind;

}
