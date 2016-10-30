package com.example.car.application;

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
 * Controller class executes operations in the database.
 */

public class CarController {

    private static CarDBHelper mDbHelper;
    private static  SQLiteDatabase db;

    /**
     * @desc Save Car object into the database.
     * It will delete the old information to update the information
     */
    public static void saveSQL(Context myContextRef, ObjectCar car) {

        // Gets the data repository in write mode
        mDbHelper = new CarDBHelper(myContextRef);
        db = mDbHelper.getWritableDatabase();

        //discard the data and to start over
        mDbHelper.deleteContent(db);

        int len = car.getPlacemarks().size();
        int counter = 0;
        while (counter < len) {

            Placemark pc = car.getPlacemarks().get(counter);

            ContentValues placemark = new ContentValues();
           // Log.i("Save COORD ", pc.getCoordinates().toString());
            placemark.put(CarContract.PlacemarkEntry.COLUMN_ADDRESS, pc.getAddress());
            placemark.put(CarContract.PlacemarkEntry.COLUMN_COORDINATES, pc.getCoordinates().toString());
            placemark.put(CarContract.PlacemarkEntry.COLUMN_ENGINE_TYPE, pc.getEngineType());
            placemark.put(CarContract.PlacemarkEntry.COLUMN_EXTERIOR, pc.getExterior());
            placemark.put(CarContract.PlacemarkEntry.COLUMN_FUEL, pc.getFuel());
            placemark.put(CarContract.PlacemarkEntry.COLUMN_INTERIOR, pc.getInterior());
            placemark.put(CarContract.PlacemarkEntry.COLUMN_NAME, pc.getName());
            placemark.put(CarContract.PlacemarkEntry.COLUMN_VIN, pc.getVin());

            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(CarContract.PlacemarkEntry.TABLE_NAME, null, placemark);

            counter++;
            //Log.i("JSON in SQLITE: ", placemark.toString());
        }

    }

    //Get the info from the database. return list of placemarks
    public static List<Placemark> getInfo(Context myContextRef) {

        List<Placemark> placemarks = new ArrayList<Placemark>();

        // Gets the data repository in read mode
        mDbHelper = new CarDBHelper(myContextRef);
        db = mDbHelper.getReadableDatabase();


        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                CarContract.PlacemarkEntry._ID,
                CarContract.PlacemarkEntry.COLUMN_ADDRESS,
                CarContract.PlacemarkEntry.COLUMN_COORDINATES,
                CarContract.PlacemarkEntry.COLUMN_ENGINE_TYPE,
                CarContract.PlacemarkEntry.COLUMN_EXTERIOR,
                CarContract.PlacemarkEntry.COLUMN_FUEL,
                CarContract.PlacemarkEntry.COLUMN_INTERIOR,
                CarContract.PlacemarkEntry.COLUMN_NAME,
                CarContract.PlacemarkEntry.COLUMN_VIN,
        };


        Cursor c = db.query(
                CarContract.PlacemarkEntry.TABLE_NAME, // The table to query
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
            String num = c.getString(c.getColumnIndex(CarContract.PlacemarkEntry.COLUMN_COORDINATES));
            String[] doubles = num.split(",");
            coord.add(Double.parseDouble(doubles[0].replace("[", "")));
         //   Log.i("DOUBLE: 1 ", Double.parseDouble(doubles[0].replace("[", ""))+"");
            coord.add(Double.parseDouble(doubles[1]));
       //     Log.i("DOUBLE: 2 ",Double.parseDouble(doubles[1])+"");

            Placemark pm = new Placemark(
                    c.getString(c.getColumnIndex(CarContract.PlacemarkEntry.COLUMN_ADDRESS)),
                    coord,
                    c.getString(c.getColumnIndex(CarContract.PlacemarkEntry.COLUMN_ENGINE_TYPE)),
                    c.getString(c.getColumnIndex(CarContract.PlacemarkEntry.COLUMN_EXTERIOR)),
                    c.getInt(c.getColumnIndex(CarContract.PlacemarkEntry.COLUMN_FUEL)),
                    c.getString(c.getColumnIndex(CarContract.PlacemarkEntry.COLUMN_INTERIOR)),
                    c.getString(c.getColumnIndex(CarContract.PlacemarkEntry.COLUMN_NAME)),
                    c.getString(c.getColumnIndex(CarContract.PlacemarkEntry.COLUMN_VIN)));

            placemarks.add(c.getPosition(), pm);

            c.moveToNext();
        }
        c.close();
        return placemarks;
    }
}
