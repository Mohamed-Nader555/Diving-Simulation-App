package com.ghada.divingsimulation.Utils;

import com.google.android.gms.maps.model.LatLng;

public interface DeviceLocationCallback {

    void deviceLocationResponse(LatLng latLng, String address);

}
