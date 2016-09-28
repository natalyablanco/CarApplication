package com.example.kisuke.wunderapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.kisuke.wunderapplication.WunderCarContract.PlacemarkEntry.COLUMN_VIN;

/**
 * Created by natalya blanco on 24/09/2016.
 * natalyablanco@gmail.com
 * <p>
 * This class implements the methods to maintain the database and the tables.
 */
public class WunderCarDBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "WunderCar.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_PLACEMARK_ENTRIES =
            "CREATE TABLE " + WunderCarContract.PlacemarkEntry.TABLE_NAME + " (" +
                    WunderCarContract.PlacemarkEntry._ID + " INTEGER PRIMARY KEY," +
                    WunderCarContract.PlacemarkEntry.COLUMN_ADDRESS + TEXT_TYPE + COMMA_SEP +
                    WunderCarContract.PlacemarkEntry.COLUMN_COORDINATES + TEXT_TYPE + COMMA_SEP +
                    WunderCarContract.PlacemarkEntry.COLUMN_ENGINE_TYPE + TEXT_TYPE + COMMA_SEP +
                    WunderCarContract.PlacemarkEntry.COLUMN_EXTERIOR + TEXT_TYPE + COMMA_SEP +
                    WunderCarContract.PlacemarkEntry.COLUMN_FUEL + TEXT_TYPE + COMMA_SEP +
                    WunderCarContract.PlacemarkEntry.COLUMN_INTERIOR + TEXT_TYPE + COMMA_SEP +
                    WunderCarContract.PlacemarkEntry.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    COLUMN_VIN + TEXT_TYPE + " )";

    private static final String SQL_DELETE_PLACEMARK_ENTRIES =
            "DROP TABLE IF EXISTS " + WunderCarContract.PlacemarkEntry.TABLE_NAME;


    //Constructor
    public WunderCarDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * @param db
     * @desc Creates the table "placemark"
     */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PLACEMARK_ENTRIES);
    }

    /**
     * @param db
     * @param oldVersion
     * @param newVersion
     * @desc Used when the database needs to be upgraded.
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_PLACEMARK_ENTRIES);
        onCreate(db);
    }

    /**
     * @param db
     * @desc deletes "placemark" table.
     */
    public void deleteContent(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_PLACEMARK_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
