package com.example.lucke.adventuresahead.models.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lucke.adventuresahead.models.TableEnum;

/**
 * Created by lucke on 6/21/2017.
 */

public class AdventuresDatabase extends SQLiteOpenHelper {
    private static final String LOG = "DatabaseHelper";

    // version and database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "adventuresDataBase";

    // tables
    private static final String TABLE_ADVENTURES = TableEnum.TABLE_ADVENTURES.getTableName();

    private static final String TABLE_LOCATIONS = TableEnum.TABLE_LOCATIONS.getTableName();
    private static final String TABLE_ADVENTURE_LOCATION = TableEnum.TABLE_ADVENTURE_LOCATION.getTableName();

    private static final String TABLE_TRANSPORTATION = TableEnum.TABLE_TRANSPORTATION.getTableName();
    private static final String TABLE_ADVENTURE_TRANSPORTATION = TableEnum.TABLE_ADVENTURE_TRANSPORTATION.getTableName();


    // column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_START_DATE = "start";
    private static final String KEY_END_DATE = "end";

    // junction column names
    private static final String FK_ADVENTURE_ID = "adventure_id";
    private static final String FK_LOCATION_ID = "location_id";
    private static final String FK_TRANSPORTATION_ID = "transportation_id";

    private static final String PK_ADVENTURE_LOCATION = "adventure_location_id";
    private static final String PK_ADVENTURE_TRANSPORTATION = "adventure_location_id";


    // create statements
    private static final String CREATE_TABLE_ADVENTURES =
            "CREATE TABLE " + TABLE_ADVENTURES +
            "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT NOT NULL, " +
                KEY_START_DATE + " TEXT NOT NULL" +
            ")";

    private static final String CREATE_TABLE_LOCATIONS =
            "CREATE TABLE " + TABLE_LOCATIONS +
            "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT NOT NULL, " +
                KEY_ADDRESS + " TEXT, " +
                KEY_START_DATE + " DATE NOT NULL, " +
                KEY_END_DATE + " DATE NOT NULL" +
            ")";

    private static final String CREATE_TABLE_TRANSPORTATION =
            "CREATE TABLE " + TABLE_TRANSPORTATION +
                "(" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_NAME + " TEXT NOT NULL, " +
                    KEY_START_DATE + " DATE NOT NULL, " +
                    KEY_END_DATE + " DATE NOT NULL" +
                ")";

    private static final String CREATE_TABLE_ADVENTURE_LOCATION =
            "CREATE TABLE " + TABLE_ADVENTURE_LOCATION +
            " (" +
                FK_ADVENTURE_ID + " INTEGER NOT NULL, " +
                FK_LOCATION_ID + " INTEGER NOT NULL, " +
                "CONSTRAINT " + PK_ADVENTURE_LOCATION + " PRIMARY KEY" +
                " (" +
                    FK_ADVENTURE_ID + ", " +
                    FK_LOCATION_ID +
                ") " +
                "FOREIGN KEY (" + FK_ADVENTURE_ID + ") " + "REFERENCES " + TABLE_ADVENTURES + " (" + KEY_ID + ") " + "ON DELETE CASCADE " +
                "FOREIGN KEY (" + FK_LOCATION_ID + ") " + "REFERENCES " + TABLE_LOCATIONS + " (" + KEY_ID + ") " + "ON DELETE CASCADE" +
            ")";

    private static final String CREATE_TABLE_ADVENTURE_TRANSPORTATION =
            "CREATE TABLE " + TABLE_ADVENTURE_TRANSPORTATION +
            " (" +
                    FK_ADVENTURE_ID + " INTEGER NOT NULL, " +
                    FK_TRANSPORTATION_ID + " INTEGER NOT NULL, " +
                    "CONSTRAINT " + PK_ADVENTURE_TRANSPORTATION + " PRIMARY KEY" +
                    " (" +
                        FK_ADVENTURE_ID + ", " +
                        FK_TRANSPORTATION_ID +
                    ") " +
                    "FOREIGN KEY (" + FK_ADVENTURE_ID + ") " + "REFERENCES " + TABLE_ADVENTURES + " (" + KEY_ID + ") " + " ON DELETE CASCADE, " +
                    "FOREIGN KEY (" + FK_TRANSPORTATION_ID + ") " + " REFERENCES " + TABLE_TRANSPORTATION + " (" + KEY_ID + ") " + " ON DELETE CASCADE" +
            ")";


    public AdventuresDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ADVENTURES);
        db.execSQL(CREATE_TABLE_LOCATIONS);
        db.execSQL(CREATE_TABLE_TRANSPORTATION);
        db.execSQL(CREATE_TABLE_ADVENTURE_LOCATION);
        db.execSQL(CREATE_TABLE_ADVENTURE_TRANSPORTATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADVENTURES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSPORTATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADVENTURE_LOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADVENTURE_TRANSPORTATION);

        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }
}
