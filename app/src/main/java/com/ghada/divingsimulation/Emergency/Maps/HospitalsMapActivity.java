package com.ghada.divingsimulation.Emergency.Maps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.ghada.divingsimulation.Chart.Dialogs.MapResultDialog;
import com.ghada.divingsimulation.Chart.Maps.MarkerData;
import com.ghada.divingsimulation.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class HospitalsMapActivity extends FragmentActivity implements OnMapReadyCallback, Serializable/*, HospitalDialogFragment.getDataDialogListener */ {

    final String TAG = "HospitalsMapActivity";
    FusedLocationProviderClient mFusedLocationProviderClient;
    float DEFALT_ZOOM = 15f;
    double deviceLat, deviceLon;
    Bundle bundle = new Bundle();
    MapResultDialog mapResultDialog = new MapResultDialog();
    private GoogleMap mMap;
    private PlacesClient placesClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals_map);
        // Initialize the Places API
        Places.initialize(getApplicationContext(), getString(R.string.google_maps_api_key));
        placesClient = Places.createClient(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_hospitals);
        mapFragment.getMapAsync(this);
        checkPermissions();
        statusBarColor();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getDeviceLocation();

        mMap.setMyLocationEnabled(true);

//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//                MarkerData mMarkerData = new MarkerData(latLng.latitude, latLng.longitude, "Me", getAddress(latLng), R.drawable.pin);
//                mMap.clear();
//                createMarker(mMarkerData.getLatitude(), mMarkerData.getLongitude(), mMarkerData.getTitle(), mMarkerData.getSnippet(), mMarkerData.getIconResId());
//                Log.e("HospitalsMapActivity", "This is the location of the res");
//            }
//        });
    }

    private void statusBarColor() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.main_color));
        }
    }

    public void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            Task location = mFusedLocationProviderClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Log.e("Success", "done getting location in map ");
                        android.location.Location currentLocation = (android.location.Location) task.getResult();
                        deviceLat = currentLocation.getLatitude();
                        deviceLon = currentLocation.getLongitude();
                        moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFALT_ZOOM);


                        LatLng latlong = new LatLng(deviceLat, deviceLon);
                        MarkerData mMarkerData = new MarkerData(deviceLat, deviceLon, "Me", getAddress(latlong), R.drawable.dive_pin);
                        mMap.clear();
                        createMarker(mMarkerData.getLatitude(), mMarkerData.getLongitude(), mMarkerData.getTitle(), mMarkerData.getSnippet(), mMarkerData.getIconResId());
                        Log.e("HospitalsMapActivity", "This is the location of the res");

                        getNearbyHospitals(deviceLat, deviceLon, getString(R.string.google_maps_api_key));
                    } else {
                        String message = task.getException().toString();
                        Log.e("Error:", message);
                    }
                }
            });
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void moveCamera(LatLng latLng, float zoom) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    protected GoogleMap createMarker(double latitude, double longitude, String title, String snippet, int iconID) {
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(title).snippet(snippet).icon(BitmapDescriptorFactory.fromResource(iconID)));
        moveCamera(new LatLng(latitude, longitude), DEFALT_ZOOM);
        return mMap;
    }

    @AfterPermissionGranted(1111)
    private boolean checkPermissions() {
        String[] locationPermissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.hasPermissions(this, locationPermissions)) {
            return true;
        } else {
            EasyPermissions.requestPermissions(this, "Location Access", 1111, locationPermissions);
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            String editTextString = data.getStringExtra("ADD");
            Log.e("MAPSDATA", "onActivityResult: " + editTextString);
        }
    }

    private String getAddress(LatLng latLng) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            String address = addresses.get(0).getCountryName() + "/" + addresses.get(0).getAdminArea();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag("dialog");
//            if (prev == null) {
//                ft.addToBackStack(null);
//                DialogFragment dialogFragment = new HospitalDialogFragment();
//                Bundle args = new Bundle();
//                args.putDouble("lat", latLng.latitude);
//                args.putDouble("long", latLng.longitude);
//                args.putString("address", address);
//                dialogFragment.setArguments(args);
//                dialogFragment.show(ft, "dialog");
//            }
            return address;
        } catch (IOException e) {
            e.printStackTrace();
            return "No Address Found";
        }
    }

//    @Override
//    public void onFinishDialog(double lat, double lon, String Address) {
//        bundle.putDouble("selectedLat", lat);
//        bundle.putDouble("selectedLong", lon);
//        bundle.putString("selectedAddress", Address);
//        mapResultDialog.setArguments(bundle);
//        mapResultDialog.show(((FragmentActivity) this).getSupportFragmentManager(), "MapResultDialog");
//    }


    public void getNearbyHospitals(double latitude, double longitude, String apiKey) {
        String location = latitude + "," + longitude;
        int radius = 5000; // 5 km radius
        String type = "hospital";

        String urlString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" +
                "location=" + location +
                "&radius=" + radius +
                "&type=" + type +
                "&key=" + apiKey;
        Log.e(TAG, "getNearbyHospitals: " + urlString);
        new FetchPlacesTask().execute(urlString);
    }

    private void getPlaceDetails(String placeId) {
        String apiKey = getString(R.string.google_maps_api_key); // Replace with your API key
        String urlString = "https://maps.googleapis.com/maps/api/place/details/json?" +
                "place_id=" + placeId +
                "&key=" + apiKey;

        new FetchPlaceDetailsTask().execute(urlString);
    }

    private class FetchPlacesTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            Log.e(TAG, "doInBackground: helooo ");
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                response = stringBuilder.toString();
                Log.e(TAG, "doInBackground: " + response);
                reader.close();
                urlConnection.disconnect();
            } catch (Exception e) {
                Log.e(TAG, "Error fetching places: ", e);
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray resultsArray = jsonObject.getJSONArray("results");
                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject placeObject = resultsArray.getJSONObject(i);
                    String placeId = placeObject.getString("place_id");
                    getPlaceDetails(placeId);
                }
            } catch (JSONException e) {
                Log.e(TAG, "Error parsing JSON: ", e);
            }
        }
    }

    private class FetchPlaceDetailsTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                response = stringBuilder.toString();
                reader.close();
                urlConnection.disconnect();
            } catch (Exception e) {
                Log.e(TAG, "Error fetching place details: ", e);
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONObject resultObject = jsonObject.getJSONObject("result");

                String name = resultObject.getString("name");
                String address = resultObject.getString("formatted_address");
                String phoneNumber = resultObject.optString("formatted_phone_number", "N/A");
                double rating = resultObject.optDouble("rating", 0.0);
                JSONObject geometry = resultObject.getJSONObject("geometry");
                JSONObject location = geometry.getJSONObject("location");
                double lat = location.getDouble("lat");
                double lng = location.getDouble("lng");

                // Add a marker on the map
                LatLng placeLatLng = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions()
                        .position(placeLatLng)
                        .title(name)
                        .snippet("Rating: " + rating + " Phone: " + phoneNumber)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital_pin)));

                Log.e(TAG, "Place details: " + name + ", " + address + ", " + phoneNumber + ", " + rating);

            } catch (JSONException e) {
                Log.e(TAG, "Error parsing place details JSON: ", e);
            }
        }
    }


}
