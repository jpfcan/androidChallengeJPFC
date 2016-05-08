package com.example.juanpablo.androidchallengedemandfrontier;

import android.app.Application;
import android.location.LocationManager;

/**
 * Created by Juan Pablo on 05/05/16.
 */
public class MyApplication extends Application {

    private double latitude;
    private double longitude;
    private LocationManager locationManager;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

    public void setLocationManager(LocationManager locationManager) {
        this.locationManager = locationManager;
    }
}
