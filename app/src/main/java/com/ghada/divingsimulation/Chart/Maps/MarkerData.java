package com.ghada.divingsimulation.Chart.Maps;

import java.io.Serializable;

public class MarkerData implements Serializable {

    private double latitude;
    private double longitude;
    private String title;
    private String snippet;
    private int iconResId;

    public MarkerData(double latitude, double longitude, String title, String snippet, int iconResId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.snippet = snippet;
        this.iconResId = iconResId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTitle() {
        return title;
    }

    public String getSnippet() {
        return snippet;
    }

    public int getIconResId() {
        return iconResId;
    }
}
