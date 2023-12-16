package com.example.weatherapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultActivity extends AppCompatActivity {

    private ImageView imageWeather;
    private ImageView imageHumidity;
    private ImageView imagePressure;
    private ImageView imageWind;
    private TextView cityName;
    private TextView temperature;
    private TextView description;
    private TextView humidityNumber;
    private TextView pressureNumber;
    private TextView windSpeed;

    private Button backButton;
    private final String apiKey = "e44fdd50626cc7af9cbae08bd14636a3";
    private final String lang = "ru";
    private Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String city = intent.getStringExtra("userInputCity");

        cityName = findViewById(R.id.city_name);
        temperature = findViewById(R.id.temperature);
        description = findViewById(R.id.description);
        imageWeather = findViewById(R.id.image_weather);
        backButton = findViewById(R.id.back_button);
        humidityNumber = findViewById(R.id.humidity_number);
        pressureNumber = findViewById(R.id.pressure_number);
        windSpeed = findViewById(R.id.wind_speed);
        imageHumidity = findViewById(R.id.image_humidity);
        imagePressure = findViewById(R.id.image_pressure);
        imageWind = findViewById(R.id.image_wind);

        getWeatherData(city);

        backButton.setOnClickListener(view -> {
            finish();
        });
    }

    private void getWeatherData(String city) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call<WeatherData> call = weatherApi.getWeatherData(city, lang, apiKey, "metric");

        call.enqueue(new Callback<WeatherData>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if (!response.isSuccessful()) {
                        handleApiError(response);
                } else {
                    WeatherData data = response.body();
                    if (data != null) {
                        cityName.setText(data.name);
                        temperature.setText((int) Math.round(data.main.temp) + "°C");
                        description.setText(data.weather[0].description);
                        humidityNumber.setText((int) Math.round(data.main.humidity) + "%");
                        windSpeed.setText(data.wind.speed + "м/с");
                        pressureNumber.setText((int) Math.round(data.main.pressure) + "гПа");
                        setDescriptionImage(data);
                        setIcons();
                    } else {
                        // For valid inout city name, but API do not have weather info
                        description.setText(R.string.error_not_have_info);
                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                handleError(t);
            }
        });
    }

    private void setDescriptionImage(WeatherData data) {
        String iconName = "im" + data.weather[0].icon;
        int iconId = getResources().getIdentifier(iconName, "drawable", getPackageName());
        if (iconId != 0) {
            imageWeather.setImageResource(iconId);
        } else {
            imageWeather.setImageResource(R.drawable.im01d);
        }
    }
    private void setIcons() {
        imageWind.setImageResource(R.drawable.wind);
        imageHumidity.setImageResource(R.drawable.humidity);
        imagePressure.setImageResource(R.drawable.pressure);
    }

    private void handleApiError(Response<WeatherData> response) {
        if (response.code() == 404) {
            // For invalid input city name
            description.setText(R.string.error_not_found_city);
        } else {
            String errorMessage = "Ошибка API: " + response.code();
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    private void handleError(Throwable t) {
        String errorMessage = "Ошибка сети: " + t.getLocalizedMessage();
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
