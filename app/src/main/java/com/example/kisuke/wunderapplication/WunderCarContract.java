package com.example.kisuke.wunderapplication;

import android.provider.BaseColumns;

import static com.example.kisuke.wunderapplication.WunderCarContract.PlacemarkEntry.COLUMN_VIN;


/**
 * Created by natal on 24/09/2016.
 */
public  class WunderCarContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private WunderCarContract() {}

    /* Inner class for placemarks entries.*/
    public static class PlacemarkEntry implements BaseColumns {
        public static final String TABLE_NAME = "placemark";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_COORDINATES = "coordenates_id";
        public static final String COLUMN_ENGINE_TYPE = "engineType";
        public static final String COLUMN_EXTERIOR = "exterior";
        public static final String COLUMN_FUEL = "fuel";
        public static final String COLUMN_INTERIOR = "interior";
        public static final String COLUMN_VIN = "vin";
    }

}