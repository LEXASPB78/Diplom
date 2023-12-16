package com.example.weatherapp;

public class WeatherData {
    public Main main;
    public Weather[] weather;
    public String name;
    public Wind wind;

    public class Main {
        public double temp;
        public int humidity;
        public double pressure;
    }

    public class Weather {
        public String description;
        public String icon;
    }

    public class Wind {
        public double speed;
    }
}