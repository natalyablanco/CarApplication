package com.example.kisuke.wunderapplication;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import static com.example.kisuke.wunderapplication.R.id.map;
import static com.example.kisuke.wunderapplication.R.id.title;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener,
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
     * We fill up the map with the coordinates saved into WunderCar
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        showWunderCar();
    }

    private void showWunderCar(){

        placemarks =  WunderController.getInfo(getApplicationContext());
        sizePlacemarks = placemarks.size();
        allMarkers = new ArrayList<>();
        Log.i("MAP size ", sizePlacemarks+"");
        int count = 0;

        while (count < sizePlacemarks){

            List<Double> lat = placemarks.get(count).getCoordinates();
            //Log.i("lat values ", lat.toString()+"");

            LatLng latLngCar = new LatLng(lat.get(1),lat.get(0));

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


    }

    /** Called when the user clicks a marker.
     *
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Log.i("MAP: ", "marker pressed" + marker.getTitle());
        int count = 0;

        while(count<sizePlacemarks){
            if (allMarkers.get(count).getTitle() != marker.getTitle())
                allMarkers.get(count).setAlpha(0);

            count++;
        }

        oneVisible = true;
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        oneVisible = false;
        int count = 0;

        while(count<sizePlacemarks){
            allMarkers.get(count).setAlpha(1);
            count++;
        }

        marker.hideInfoWindow();

    }
}
