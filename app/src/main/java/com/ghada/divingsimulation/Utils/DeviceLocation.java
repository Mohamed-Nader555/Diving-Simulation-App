package com.ghada.divingsimulation.Utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DeviceLocation {
    public static LatLng latLng;

    public static void getDeviceLocation(Context context, DeviceLocationCallback deviceLocationCallback) {

        try {
            FusedLocationProviderClient mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
            Task location = mFusedLocationProviderClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Log.e("Success", "done getting location in map ");

                        Location currentLocation = (Location) task.getResult();

                        latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                        Log.e("Success", "onResponse:Lat " + latLng.latitude);
                        Log.e("Success", "onResponse:Lon " + latLng.longitude);


                        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                        List<Address> addresses = null;
                        String address = "";
                        try {
                            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                            address = addresses.get(0).getCountryName() + "/" + addresses.get(0).getAdminArea();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        deviceLocationCallback.deviceLocationResponse(latLng, address);

                    } else {
                        String message = task.getException().toString();
                        Log.e("Error:", message);
                    }
                }
            });


        } catch (SecurityException e) {
            Log.e("Failed", "onResponse:getLocation Error:  " + e.getMessage());
        }
    }

}
