package com.example.kisuke.wunderapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String stringUrl = "http://wunder.comxa.com/cars_locations.json";
    private static final String DEBUG_TAG = "Main Activity...";
    private WunderCar wunderCar;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check network connection before fetching the URL
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If the connection is ok, then download JSON file.
        if (networkInfo != null && networkInfo.isConnected()) {

            Log.i(DEBUG_TAG, "connection successful");

            new JSONFromURL(this).execute(stringUrl);


        } else {
            // If the connection is not ok, then use previous version saved into SD.
            Toast.makeText(this, "No network connection available. " +
                    "Using local information", Toast.LENGTH_LONG).show();
        }

        showList();


    }

    private void showList() {

        WunderController wunderController = new WunderController(getApplicationContext(),wunderCar);
        List<Placemark> placemarks;
        placemarks = wunderController.getInfo();

        mRecyclerView = (RecyclerView)findViewById(R.id.rv);

         // use a linear layout manager
         mLayoutManager = new LinearLayoutManager(this);
         mRecyclerView.setLayoutManager(mLayoutManager);


        // specify an adapter
        mAdapter = new RVAdapter(placemarks);
        mRecyclerView.setAdapter(mAdapter);



    }
}
