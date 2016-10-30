package com.example.car.application;


import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by natalya blanco on 23/09/2016.
 * natalyablanco@gmail.com
 * <p>
 * This class extends AsyncTask to create a thread.
 * This thread is responsible to get download the JSON from the Url
 * and then call the controller to save the information into de database.
 * <p>
 * TODO: use a ORM like SugarORM
 */
public class JSONFromURL extends AsyncTask<String, String, Integer> {

    private static final String DEBUG_TAG = "HttpConnection...";
    private Activity myContextRef;
    private ObjectCar car;

    //Constructor
    public JSONFromURL(Activity myContextRef) {
        this.myContextRef = myContextRef;
    }


    // Download the file JSON into SD Card.
    @Override
    protected Integer doInBackground(String... urls) {

        try {

            //download json
            downloadUrl(urls[0]);


            return Log.i(DEBUG_TAG, "JSON downloaded");

        } catch (IOException e) {

            return Log.e(DEBUG_TAG, "Unable to retrieve web page. URL may be invalid.");
        }
    }


    /**
     * @param urlString
     * @throws IOException
     * @desc Given the URL, maps the JSON into a Java object
     * that then is saved into the data base.
     */
    private void downloadUrl(String urlString) throws IOException {

        try {

            //used Jackson library to map the JSON from URL
            URL jsonUrl = new URL(urlString);
            ObjectMapper mapper = new ObjectMapper();
            car = new ObjectCar();
            car = mapper.readValue(jsonUrl, ObjectCar.class);

            CarController.saveSQL(myContextRef, car);
            Log.i(DEBUG_TAG, "JSON saved into DB");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}



