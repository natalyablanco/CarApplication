package com.example.kisuke.wunderapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.renderscript.ScriptGroup;
import android.util.Log;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.kisuke.wunderapplication.WunderController.saveSQL;

/**
 * Created by natal on 23/09/2016.
 */
public class JSONFromURL extends AsyncTask<String, String, String> {

    private static final String DEBUG_TAG = "HttpConnection...";
    private Activity myContextRef;
    private WunderCar wunderCar;

    private WunderController wunderController;

    //Constructor
    public JSONFromURL(Activity myContextRef) {
        this.myContextRef = myContextRef;
    }


    // Download the file JSON into SD Card.
    @Override
    protected String doInBackground(String... urls) {

        try {

            //Create file with the information from the URL
            downloadUrl(urls[0]);
            Log.i(DEBUG_TAG, "JSON downloaded");

            return "";

        } catch (IOException e) {
            Log.e(DEBUG_TAG, "Unable to retrieve web page. URL may be invalid.");
            return "";
        }
    }

    // Given a URL, establishes an HttpUrlConnection and retrieves
    // the web page content. Save into database
    private String downloadUrl(String urlString) throws IOException {

        try {

            URL jsonUrl = new URL(urlString);
            ObjectMapper mapper = new ObjectMapper();
            wunderCar = new WunderCar();
            wunderCar = mapper.readValue(jsonUrl, WunderCar.class);

            Log.e(DEBUG_TAG,"SAVING JSON");

            wunderController = new WunderController(myContextRef, wunderCar);
            wunderController.saveSQL();

            return "";


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return "";

    }



    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {
        //textView.setText(result);

    }


}



