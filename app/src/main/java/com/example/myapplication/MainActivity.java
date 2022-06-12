package com.example.myapplication;


import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_cityCoord, btn_getWeather;
    EditText et_dataInput;
    ListView lv_weatherReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //id pentru fiecare control din layout
        btn_cityCoord = findViewById(R.id.btn_getCityCoord);
        btn_getWeather = findViewById(R.id.btn_getWeatherByCoord);

        et_dataInput = findViewById(R.id.et_dataInput);
        lv_weatherReports = findViewById(R.id.lv_weatherReports);

        final WeatherData weatherData = new WeatherData(MainActivity.this);

        //click listeners pentru butoane
        btn_getWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherData.getWeather(et_dataInput.getText().toString(), new WeatherData.WeatherByCoordResponse() {  //callback
                    @Override
                    public void onError(String Message) {
                        Toast.makeText(MainActivity.this,Message,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(WeatherReportModel weatherReportModel) {

                        ArrayList<Object> weatherReportModels = new ArrayList<Object>();
                        weatherReportModels.add(weatherReportModel.getCondition()+", Local time: "+weatherReportModel.getLocaltime());
                        weatherReportModels.add("Current Temp: "+weatherReportModel.getTemp()+" °C");
                        weatherReportModels.add("Feelslike: "+weatherReportModel.getFeelslike_c()+" °C");
                        weatherReportModels.add("Humidity: "+weatherReportModel.getHumidity()+"%");
                        weatherReportModels.add("Precipitation: "+weatherReportModel.getPrecip_mm()+" mm");
                        weatherReportModels.add("Air pressure: "+weatherReportModel.getPressure_mb()+" mb");
                        weatherReportModels.add("Wind direction: "+weatherReportModel.getWind_dir());
                        weatherReportModels.add("Wind speed: "+weatherReportModel.getWind_kph()+" kph");
                        weatherReportModels.add("UV level: "+weatherReportModel.getUv());

                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModels);
                        lv_weatherReports.setAdapter(arrayAdapter);
                    }
                });
            }
        });

        btn_cityCoord.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                weatherData.getCityCoord(et_dataInput.getText().toString(), new WeatherData.VolleyResponseListener() {  //callback
                    @Override
                    public void onError(String Message) {
                        Toast.makeText(MainActivity.this,Message,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String coord) {
                        Toast.makeText(MainActivity.this, "Coordonatele orasului sunt "+coord,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}