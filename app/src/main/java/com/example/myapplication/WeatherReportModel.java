package com.example.myapplication;

public class WeatherReportModel {

    private String localtime;
    private int temp;
    private int wind_kph;
    private String wind_dir;
    private float pressure_mb;
    private float precip_mm;
    private int humidity;
    private int cloud;
    private float feelslike_c;
    private float uv;
    private String condition;

    public WeatherReportModel(String localtime, int temp, int wind_kph, String wind_dir, float pressure_mb, float precip_mm, int humidity, int cloud, float feelslike_c, float uv, String condition) {
        this.localtime = localtime;
        this.temp = temp;
        this.wind_kph = wind_kph;
        this.wind_dir = wind_dir;
        this.pressure_mb = pressure_mb;
        this.precip_mm = precip_mm;
        this.humidity = humidity;
        this.cloud = cloud;
        this.feelslike_c = feelslike_c;
        this.uv = uv;
        this.condition = condition;
    }

    public WeatherReportModel() {
    }

    public String getLocaltime() {
        return localtime;
    }

    public void setLocaltime(String localtime) {
        this.localtime = localtime;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getWind_kph() {
        return wind_kph;
    }

    public void setWind_kph(int wind_kph) {
        this.wind_kph = wind_kph;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public float getPressure_mb() {
        return pressure_mb;
    }

    public void setPressure_mb(float pressure_mb) {
        this.pressure_mb = pressure_mb;
    }

    public float getPrecip_mm() {
        return precip_mm;
    }

    public void setPrecip_mm(float precip_mm) {
        this.precip_mm = precip_mm;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getCloud() {
        return cloud;
    }

    public void setCloud(int cloud) {
        this.cloud = cloud;
    }

    public float getFeelslike_c() {
        return feelslike_c;
    }

    public void setFeelslike_c(float feelslike_c) {
        this.feelslike_c = feelslike_c;
    }

    public float getUv() {
        return uv;
    }

    public void setUv(float uv) {
        this.uv = uv;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
