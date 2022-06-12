package com.example.myapplication;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherData {

    String coord;

    public WeatherData(Context ctx) {
        this.ctx = ctx;
    }

    Context ctx;

    //interfata pentru callback din mainactivity
    public interface VolleyResponseListener {
        void onError(String Message);

        void onResponse(String coord);
    }

    public void getCityCoord(String city,VolleyResponseListener volleyResponseListener){

        String url = "https://api.weatherapi.com/v1/current.json?key=dc42a72f088446ce89b180918222405&q="+city+"&aqi=no";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                         coord = "";
                        try {
                            JSONObject location = response.getJSONObject("location");
                            coord = location.getString("lat")+","+location.getString("lon");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        volleyResponseListener.onResponse(coord);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("Eroare!");
            }
        });
        MySingleton.getInstance(ctx).addToRequestQueue(request);

        //return coord;
    }

    public interface WeatherByCoordResponse {
        void onError(String Message);

        void onResponse(WeatherReportModel weatherReportModel);
    }

    public void getWeather(String coord, WeatherByCoordResponse weatherByCoordResponse){

        //List<WeatherReportModel> forecast = new ArrayList<>();
        WeatherReportModel forecast = new WeatherReportModel();

        String url = "https://api.weatherapi.com/v1/current.json?key=dc42a72f088446ce89b180918222405&q="+coord+"&aqi=no";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject current = response.getJSONObject("current");
                            JSONObject condition = current.getJSONObject("condition");
                            JSONObject location = response.getJSONObject("location");

                            forecast.setLocaltime(location.getString("localtime"));
                            forecast.setTemp(current.getInt("temp_c"));
                            forecast.setCloud(current.getInt("cloud"));
                            forecast.setFeelslike_c((float) current.getDouble("feelslike_c"));
                            forecast.setHumidity(current.getInt("humidity"));
                            forecast.setPrecip_mm((float) current.getDouble("precip_mm"));
                            forecast.setPressure_mb((float) current.getDouble("pressure_mb"));
                            forecast.setUv((float) current.getDouble("uv"));
                            forecast.setWind_dir(current.getString("wind_dir"));
                            forecast.setWind_kph(current.getInt("wind_kph"));
                            forecast.setCondition(condition.getString("text"));

                            weatherByCoordResponse.onResponse(forecast);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                weatherByCoordResponse.onError("Eroare");
            }
        });
        MySingleton.getInstance(ctx).addToRequestQueue(request);
    }

}
