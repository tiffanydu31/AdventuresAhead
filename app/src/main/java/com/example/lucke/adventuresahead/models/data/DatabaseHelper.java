package com.example.lucke.adventuresahead.models.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;


import com.example.lucke.adventuresahead.models.Adventure;
import com.example.lucke.adventuresahead.models.TableEnum;
import com.example.lucke.adventuresahead.models.TripItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucke on 6/20/2017.
 */

public class DatabaseHelper {
    private static final String LOG_TAG = DatabaseHelper.class.getName();

    private AdventuresDatabase db;

    public DatabaseHelper(Context context) {
        db = new AdventuresDatabase(context);
    }

    public long addToTable(TableEnum table, List<String> inputs) {
        List<String> keys = table.getKeys();
        ContentValues values = new ContentValues();
        for (int i = 0; i < keys.size(); i++) {
            values.put(keys.get(i), inputs.get(i));
        }

        long id = db.getWritableDatabase().insert(table.getTableName(), null, values);

        return id;
    }

    public void deleteFromTable(TableEnum table, long id) {

        db.getWritableDatabase().delete(table.getTableName(), "id = ?", new String[] { String.valueOf(id)});

    }


    // Getting single adventure
    public Adventure getAdventure(long adventureId) {

        String selectQuery = "SELECT  * FROM " + TableEnum.TABLE_ADVENTURES.getTableName() + " WHERE id = " + adventureId;

        Log.v(LOG_TAG, selectQuery);

        Cursor cursor = db.getReadableDatabase().rawQuery(selectQuery, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Adventure adventure = new Adventure();

        adventure.setId(cursor.getInt(cursor.getColumnIndex("id")));
        adventure.setName(cursor.getString(cursor.getColumnIndex("name")));
        adventure.setDate(cursor.getString(cursor.getColumnIndex("start")));

        cursor.close();

        return adventure;
    }

    public List<Adventure> getAllAdventures() {
        List<Adventure> adventureList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TableEnum.TABLE_ADVENTURES.getTableName();

        Log.v(LOG_TAG, selectQuery);

        Cursor cursor = db.getReadableDatabase().rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Adventure adventure = new Adventure();
                adventure.setId(cursor.getInt(cursor.getColumnIndex("id")));
                adventure.setName(cursor.getString(cursor.getColumnIndex("name")));
                adventure.setDate(cursor.getString(cursor.getColumnIndex("start")));

                adventureList.add(adventure);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return adventureList;
    }

    public TripItem getTripItem(TableEnum table, long itemId) {

        String selectQuery = "SELECT  * FROM " + table.getTableName() + " WHERE id = " + itemId;

        Log.v(LOG_TAG, selectQuery);

        Cursor cursor = db.getReadableDatabase().rawQuery(selectQuery, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        List<String> keys = table.getKeys();


        String[] vals = new String[keys.size() + 1];

        for (int i = 0; i < keys.size() + 1; i++) {
            if (i == 0) {
                vals[i] = cursor.getString(cursor.getColumnIndex("id"));
            } else {
                vals[i] = cursor.getString(cursor.getColumnIndex(keys.get(i-1)));
            }
        }

        TripItem item = table.getTripItem(vals);

        return item;
    }


    public List<TripItem> getAllTripItemsByAdventure(TableEnum table, long adventureId) {
        List<TripItem> items = new ArrayList<>();

        TableEnum tripItemTable = table.getJunctionParents().get(1);


        String selectQuery =
            "SELECT  * FROM " + table.getTableName() + " jk, " +
            tripItemTable.getTableName() + " tt " +
            "WHERE '" + adventureId + "' = jk." + table.getKeys().get(0) +
            " AND jk." + table.getKeys().get(1) + " = tt.id";


        Log.e(LOG_TAG, selectQuery);

        Cursor cursor = db.getReadableDatabase().rawQuery(selectQuery, null);

        Log.e(LOG_TAG, String.valueOf(cursor.getCount()));

        List<String> keys = tripItemTable.getKeys();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String[] vals = new String[keys.size()+1];

                for (int i = 0; i < keys.size() + 1; i++) {
                    if(i == 0) {
                        vals[i] = cursor.getString(cursor.getColumnIndex("id"));
                    } else {
                        vals[i] = cursor.getString(cursor.getColumnIndex(keys.get(i-1)));
                    }
                }

                TripItem item = tripItemTable.getTripItem(vals);

                items.add(item);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return items;
    }

    public void removeTripItemFromAdventure(TableEnum table, long adventureId, long TripItemId) {


        String whereQuery = table.getKeys().get(0) + " = ? AND " + table.getKeys().get(1) + " = ?";

        Log.v(LOG_TAG, whereQuery);

        db.getWritableDatabase().delete(table.getTableName(), whereQuery, new String[] { String.valueOf(adventureId), String.valueOf(TripItemId)});
    }

    public long updateAdventure(Adventure adventure) {

        ContentValues values = new ContentValues();
        values.put("name", adventure.getName());

        // updating row
        return db.getWritableDatabase().update(TableEnum.TABLE_ADVENTURES.getTableName(), values, "id = ?", new String[]{String.valueOf(adventure.getId())});
    }


    public long updateTripItem(TableEnum table ,TripItem item) {

        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("start", item.getStringStartDate());
        values.put("end", item.getStringEndDate());

        return db.getWritableDatabase().update(table.getTableName(), values, "id = ?", new String[] { String.valueOf(item.getId())});
    }

    public void closeDB() {
        if (db.getReadableDatabase() != null && db.getReadableDatabase().isOpen())
            db.close();
    }

    public void clearDatabase() {
        for (TableEnum table : TableEnum.values()) {
            String clearDBQuery = "DELETE FROM " + table.getTableName();
            db.getWritableDatabase().execSQL(clearDBQuery);
        }

    }
}
