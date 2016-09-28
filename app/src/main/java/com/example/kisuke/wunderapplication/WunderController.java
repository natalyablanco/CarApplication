package com.example.kisuke.wunderapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Natalya Blanco on 9/25/2016.
 * natalyablanco@gmail.com
 * <p>
 * WunderController class executes operations in the database.
 */

public class WunderController {

    private static WunderCarDBHelper mDbHelper;

    /**
     * @desc Save WunderCar object into the database.
     * It will delete the old information to update the information
     */
    public static void saveSQL(Context myContextRef, WunderCar wunderCar) {

        // Gets the data repository in write mode
        mDbHelper = new WunderCarDBHelper(myContextRef);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //discard the data and to start over
        mDbHelper.deleteContent(db);

        int len = wunderCar.getPlacemarks().size();
        int counter = 0;
        while (counter < len) {

            Placemark pc = wunderCar.getPlacemarks().get(counter);

            ContentValues placemark = new ContentValues();

            placemark.put(WunderCarContract.PlacemarkEntry.COLUMN_ADDRESS, pc.getAddress());
            placemark.put(WunderCarContract.PlacemarkEntry.COLUMN_COORDINATES, pc.getCoordinates().toString());
            placemark.put(WunderCarContract.PlacemarkEntry.COLUMN_ENGINE_TYPE, pc.getEngineType());
            placemark.put(WunderCarContract.PlacemarkEntry.COLUMN_EXTERIOR, pc.getExterior());
            placemark.put(WunderCarContract.PlacemarkEntry.COLUMN_FUEL, pc.getFuel());
            placemark.put(WunderCarContract.PlacemarkEntry.COLUMN_INTERIOR, pc.getInterior());
            placemark.put(WunderCarContract.PlacemarkEntry.COLUMN_NAME, pc.getName());
            placemark.put(WunderCarContract.PlacemarkEntry.COLUMN_VIN, pc.getVin());

            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(WunderCarContract.PlacemarkEntry.TABLE_NAME, null, placemark);

            counter++;
            //Log.i("JSON in SQLITE: ", placemark.toString());
        }

    }

    //Get the info from the database. return list of placemarks
    public static List<Placemark> getInfo(Context myContextRef) {

        List<Placemark> placemarks = new ArrayList<Placemark>();

        // Gets the data repository in read mode
        mDbHelper = new WunderCarDBHelper(myContextRef);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();


        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                WunderCarContract.PlacemarkEntry._ID,
                WunderCarContract.PlacemarkEntry.COLUMN_ADDRESS,
                WunderCarContract.PlacemarkEntry.COLUMN_COORDINATES,
                WunderCarContract.PlacemarkEntry.COLUMN_ENGINE_TYPE,
                WunderCarContract.PlacemarkEntry.COLUMN_EXTERIOR,
                WunderCarContract.PlacemarkEntry.COLUMN_FUEL,
                WunderCarContract.PlacemarkEntry.COLUMN_INTERIOR,
                WunderCarContract.PlacemarkEntry.COLUMN_NAME,
                WunderCarContract.PlacemarkEntry.COLUMN_VIN,
        };


        Cursor c = db.query(
                WunderCarContract.PlacemarkEntry.TABLE_NAME, // The table to query
                projection,                                  // The columns to return
                null,                                        // The columns for the WHERE clause
                null,                                        // The values for the WHERE clause
                null,                                        // don't group the rows
                null,                                        // don't filter by row groups
                null                                         // The sort order
        );

        c.moveToFirst();

        Log.i("Object lenght: ", c.getCount() + "");
        while (!c.isAfterLast()) {

            List<Double> coord = new ArrayList<>();
            String num = c.getString(c.getColumnIndex(WunderCarContract.PlacemarkEntry.COLUMN_COORDINATES));
            //Log.i("DOUBLE: ", num);
            String[] doubles = num.split(",");
            coord.add(Double.parseDouble(doubles[0].replace("[", "")));
            // coord.add(Double.parseDouble(doubles[1]));


            Placemark pm = new Placemark(
                    c.getString(c.getColumnIndex(WunderCarContract.PlacemarkEntry.COLUMN_ADDRESS)),
                    coord,
                    c.getString(c.getColumnIndex(WunderCarContract.PlacemarkEntry.COLUMN_ENGINE_TYPE)),
                    c.getString(c.getColumnIndex(WunderCarContract.PlacemarkEntry.COLUMN_EXTERIOR)),
                    c.getInt(c.getColumnIndex(WunderCarContract.PlacemarkEntry.COLUMN_FUEL)),
                    c.getString(c.getColumnIndex(WunderCarContract.PlacemarkEntry.COLUMN_INTERIOR)),
                    c.getString(c.getColumnIndex(WunderCarContract.PlacemarkEntry.COLUMN_NAME)),
                    c.getString(c.getColumnIndex(WunderCarContract.PlacemarkEntry.COLUMN_VIN)));

            placemarks.add(c.getPosition(), pm);

            c.moveToNext();
        }
        c.close();
        return placemarks;
    }
}
