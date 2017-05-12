package com.chesnowitz.mappinglocations;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission
                    (this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
            locationManager.requestLocationUpdates
                    (LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            Location lastLocation =
                    locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            centerOnLocation(lastLocation, "Location");
        }
    }

    public void centerOnLocation(Location location, String title) {
        LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());

        mMap.clear();

        if (title != "Last Location") {
            mMap.addMarker(new MarkerOptions().position(userLocation).title(title));
        }


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 9));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapLongClickListener(this);



        Intent intent = getIntent();

        if(intent.getIntExtra("LocationID", 0) == 0) {

            locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);

            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    centerOnLocation(location, "Location");
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };

            if (Build.VERSION.SDK_INT < 23) {
                locationManager.requestLocationUpdates
                        (LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            } else {
                if (ContextCompat.checkSelfPermission
                        (this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates
                            (LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                    Location lastLocation =
                            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    centerOnLocation(lastLocation, "Last Location");
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
            }
        } else {

            Location theLocation = new Location(LocationManager.GPS_PROVIDER);

            theLocation.setLatitude
                    (MainActivity.latLngs.get(intent.getIntExtra("LocationID", 0)).latitude);
            theLocation.setLongitude 
                    (MainActivity.latLngs.get(intent.getIntExtra("LocationID", 0)).longitude);

            centerOnLocation
                    (theLocation, MainActivity.locations.get(intent.getIntExtra("LocationID", 0)));

        }
    }



    @Override
    public void onMapLongClick(LatLng latLng) {

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        String address = "";

        try {
            List<Address> listAddresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

            if (listAddresses != null && listAddresses.size() > 0) {
                if (listAddresses.get(0).getThoroughfare() != null) {
                    if (listAddresses.get(0).getSubThoroughfare() != null) {
                        address += listAddresses.get(0).getSubThoroughfare() + " ";
                    }
                    address += listAddresses.get(0).getThoroughfare();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (address == "") {
            SimpleDateFormat simpleDate = new SimpleDateFormat("MM:dd:yyyy_HH:mm:ss");
            address = simpleDate.format(new Date());
        }

        mMap.addMarker(new MarkerOptions().position(latLng).title(address));

        MainActivity.arrayAdapter.notifyDataSetChanged();
        MainActivity.locations.add(address);
        MainActivity.latLngs.add(latLng);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.chesnowitz.mappinglocations",
                Context.MODE_PRIVATE);

        try {
            ArrayList<String> lats = new ArrayList<>();
            ArrayList<String> lngs = new ArrayList<>();

            for (LatLng coordinates : MainActivity.latLngs) {

                lats.add(Double.toString(coordinates.latitude));
                lngs.add(Double.toString(coordinates.longitude));
            }
            sharedPreferences.edit().putString("locations", ObjectSerializer.serialize(MainActivity.locations)).apply();
            sharedPreferences.edit().putString("lats", ObjectSerializer.serialize(lats)).apply();
            sharedPreferences.edit().putString("lngs", ObjectSerializer.serialize(lngs)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Location Saved", Toast.LENGTH_SHORT).show();
    }
}
