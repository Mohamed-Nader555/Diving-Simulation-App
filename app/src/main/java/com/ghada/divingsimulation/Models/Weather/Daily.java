package com.ghada.divingsimulation.Models.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Daily implements Serializable {

    @SerializedName("dt")
    @Expose
    private String dt;
    @SerializedName("sunrise")
    @Expose
    private String sunrise;
    @SerializedName("sunset")
    @Expose
    private String sunset;
    @SerializedName("moonrise")
    @Expose
    private String moonrise;
    @SerializedName("moonset")
    @Expose
    private String moonset;
    @SerializedName("moon_phase")
    @Expose
    private String moonPhase;
    @SerializedName("temp")
    @Expose
    private Temp temp;
    @SerializedName("feels_like")
    @Expose
    private FeelsLike feelsLike;
    @SerializedName("pressure")
    @Expose
    private String pressure;
    @SerializedName("humidity")
    @Expose
    private String humidity;
    @SerializedName("dew_point")
    @Expose
    private String dewPoint;
    @SerializedName("wind_speed")
    @Expose
    private String windSpeed;
    @SerializedName("wind_deg")
    @Expose
    private String windDeg;
    @SerializedName("wind_gust")
    @Expose
    private String windGust;
    @SerializedName("weather")
    @Expose
    private ArrayList<Weather> weather = null;
    @SerializedName("clouds")
    @Expose
    private String clouds;
    @SerializedName("pop")
    @Expose
    private String pop;
    @SerializedName("uvi")
    @Expose
    private String uvi;

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public void setMoonrise(String moonrise) {
        this.moonrise = moonrise;
    }

    public String getMoonset() {
        return moonset;
    }

    public void setMoonset(String moonset) {
        this.moonset = moonset;
    }

    public String getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(String moonPhase) {
        this.moonPhase = moonPhase;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public FeelsLike getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(FeelsLike feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(String dewPoint) {
        this.dewPoint = dewPoint;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(String windDeg) {
        this.windDeg = windDeg;
    }

    public String getWindGust() {
        return windGust;
    }

    public void setWindGust(String windGust) {
        this.windGust = windGust;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getUvi() {
        return uvi;
    }

    public void setUvi(String uvi) {
        this.uvi = uvi;
    }
}
