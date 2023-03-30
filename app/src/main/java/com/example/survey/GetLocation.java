package com.example.survey;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Looper;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.List;
import java.util.Locale;

public class GetLocation {

    private static final String TAG = "Getlocation";
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    LocationCallback locationCallback;

    public static String longtitude = "";
    public static String latitude = "";
    public static String address = "";

    Activity activity;

    public GetLocation(Activity activity) {
        this.activity = activity;
    }


    public void getCurrentLocation() {
        locationRequest = new LocationRequest();

        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);


        locationRequest.setFastestInterval(1);
        locationRequest.setInterval(1);


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity.getApplicationContext());


        locationCallback = new LocationCallback() {

            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                Location location = locationResult.getLastLocation();
                try {

                    if (location != null) {

                        Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        longtitude = String.valueOf(addresses.get(0).getLongitude());
                        latitude = String.valueOf(addresses.get(0).getLatitude());

//                        address = "Latitude: " + location.getLatitude() + "\n" + "Longtitude: " + location.getLongitude() + "\n\n" + String.valueOf(addresses.get(0).getAddressLine(0));

                        address = String.valueOf(addresses.get(0).getAddressLine(0));

                        if (address.equals("")) {
                            address = "Latitude: " + location.getLatitude() + "Longtitude: " + location.getLongitude();
                        }


                    } else {
                        askNewLocation(activity);
                    }
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }

            }
        };

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        } else {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        }

    }

    public void askNewLocation(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        } else {

            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        }
    }

    public void stopAskingLocation(Activity activity) {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }
}
