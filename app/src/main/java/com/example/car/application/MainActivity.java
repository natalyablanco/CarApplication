package com.example.car.application;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String stringUrl = "http://natalyablanco.000webhostapp.com/placemarks.json";
    private static final String DEBUG_TAG = "Main Activity...";

    //To show the list of placemarks in the layout
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //button to call MapsActivity
    private Button bMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set listener to the button
        bMap =(Button)findViewById(R.id.button_map);
        bMap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
               showMap(view);

            }
        } );

        //check network connection before fetching the URL
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If the connection is ok, then download JSON file.
        if (networkInfo != null && networkInfo.isConnected()) {

            Log.i(DEBUG_TAG, "connection successful");
            new JSONFromURL(this).execute(stringUrl);

        } else {
            // If the connection is not ok, then use information saved into the database.
            Toast.makeText(this, "No network connection available. " +
                    "Using local information", Toast.LENGTH_LONG).show();
        }

        showList();
    }

    /**
     *  Displays list of placemarks in the layout using a RecyclerView
     */
    private void showList() {

        List<Placemark> placemarks;
        placemarks = CarController.getInfo(getApplicationContext());

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);

        // set linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify the adapter
        mAdapter = new RVAdapter(placemarks);
        mRecyclerView.setAdapter(mAdapter);

    }

    /**
     *  calls the next activity: MapsActivity.
     */
    private void showMap(View view) {

        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);

    }
}
