package com.example.borek.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    // Button object
    Button navButtonMap;
    Button goTrackedButton;
    MapView runningMapView;

    Boolean locationPermission = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        navButtonMap = (Button) findViewById(R.id.navButtonMap);
        goTrackedButton = (Button) findViewById(R.id.goTrackedButton);
        runningMapView = (MapView) findViewById(R.id.runningMapView);


        initGoogleMap(savedInstanceState);
    }

    private void initGoogleMap(Bundle savedInstanceState) {
        // MapView MUST be passed Bundle that contain ONLY MapView SDK
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BllUNDLE_KEY);
        }
        runningMapView.onCreate(mapViewBundle);

        runningMapView.getMapAsync(this);
    }

    public void trackedRunningActivity(View running) {
        Intent runningIntent = new Intent(this, RunningActivity.class);
        startActivity(runningIntent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }
        runningMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        runningMapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        runningMapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        runningMapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("You are here"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        runningMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        runningMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        runningMapView.onLowMemory();
    }
}
