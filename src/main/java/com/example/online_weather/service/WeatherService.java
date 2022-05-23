package com.example.online_weather.service;

import com.example.online_weather.entity.Weather;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Service


public class WeatherService {

     @Value("${api.key}")
     private String key;

    public  int responseCode;
    public String content;

     public Weather currentWeather(String location){

         try {

             String inputLine;
             String url = "http://api.openweathermap.org/data/2.5/weather?q="+location+"&appid="+key;
             URL obj = new URL(url);
             HttpURLConnection con = (HttpURLConnection) obj.openConnection();
             responseCode = con.getResponseCode();
             BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

             StringBuilder response = new StringBuilder();
             while ((inputLine = in.readLine()) != null) {
                 response.append(inputLine);
             }

             in.close();
             content = response.toString();

         }

         catch (Exception e) {
             System.out.print("ERROR : "+e);
             return new Weather("Not Found","Not Found","Not Found","Not Found",0, 0.f);
         }

         JSONObject root = new JSONObject(content);
         JSONObject main = root.getJSONObject("main");
         //system
         JSONObject sys = root.getJSONObject("sys");
         JSONObject wind = root.getJSONObject("wind");

         //weather
         JSONArray wea = root.getJSONArray("weather");
//         JSONArray wind = root.getJSONArray("wind");
         JSONObject weas = wea.getJSONObject(0);
//         System.out.println(temp);
         System.out.println(root);
         return new Weather(
                 weas.getString("main"),
                 weas.getString("description"),
                 sys.getString("country"),
                 root.getString("name"),
                 (int) (main.getFloat("temp")-273.15),
                 wind.getFloat("speed")
         );

     }




}
