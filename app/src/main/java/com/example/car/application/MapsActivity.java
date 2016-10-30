package com.example.car.application;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.List;
import static com.example.car.application.R.id.map;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMapClickListener,
        OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Placemark> placemarks;
    private int sizePlacemarks;
    private ArrayList<Marker> allMarkers;
    private boolean oneVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * We fill up the map with the coordinates saved into Car
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        showCar();
    }

    private void showCar() {

        placemarks = CarController.getInfo(getApplicationContext());
        sizePlacemarks = placemarks.size();
        allMarkers = new ArrayList<>();
        Log.i("MAP size ", sizePlacemarks + "");
        int count = 0;

        while (count < sizePlacemarks) {

            List<Double> lat = placemarks.get(count).getCoordinates();
            //Log.i("lat values ", lat.toString()+"");

            LatLng latLngCar = new LatLng(lat.get(1), lat.get(0));

            allMarkers.add(mMap.addMarker(new MarkerOptions().position(latLngCar)
                    .title(placemarks.get(count).getName())
                    .snippet(placemarks.get(count).getAddress())));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngCar, 10));
            count++;
        }


        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);
        // Set a listener for info window events.
        mMap.setOnInfoWindowClickListener(this);

        LatLng latlng;
        mMap.setOnMapClickListener(this);


    }

    /**
     * Called when the user clicks a marker.
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.

        int count = 0;

        marker.setAlpha(1);
        Log.i("MAP: ", "marker pressed " + marker.getTitle());
        while (count < sizePlacemarks) {
            if (!allMarkers.get(count).getTitle().equals(marker.getTitle()))
                allMarkers.get(count).setAlpha(0);

            count++;
        }

            oneVisible = true;

        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        showAllMarkers();
        marker.hideInfoWindow();

    }

    @Override
    public void onMapClick(LatLng latLng) {

        if(oneVisible)
            oneVisible = false;

        showAllMarkers();


    }

    private void showAllMarkers(){

        int count = 0;

        oneVisible = false;
        while (count < sizePlacemarks) {
            allMarkers.get(count).setAlpha(1);
            count++;
        }

    }

}
