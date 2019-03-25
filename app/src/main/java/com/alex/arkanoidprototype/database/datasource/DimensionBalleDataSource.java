package com.alex.arkanoidprototype.database.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alex.arkanoidprototype.database.SQLiteHelper;
import com.alex.arkanoidprototype.database.datamodel.DimensionBalle;
import com.alex.arkanoidprototype.database.datamodel.NiveauItem;

import java.util.ArrayList;
import java.util.List;

public class DimensionBalleDataSource {

    public static final String TABLE_DIMENSIONBALLE = "DimensionBalle";
    public static final String TABLE_DIMENSIONBALLE_COLUMN_ID = "ID";
    public static final String TABLE_DIMENSIONBALLE_COLUMN_RAYON = "Rayon";


    public static String create(){
        return "create table " + TABLE_DIMENSIONBALLE + "("
                + TABLE_DIMENSIONBALLE_COLUMN_ID + " integer primary key autoincrement, "
                + TABLE_DIMENSIONBALLE_COLUMN_RAYON + " integer not null); ";
    }

    public static String drop(){
        return "DROP TABLE IF EXISTS " + TABLE_DIMENSIONBALLE +"; ";
    }

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
            TABLE_DIMENSIONBALLE_COLUMN_ID,
            TABLE_DIMENSIONBALLE_COLUMN_RAYON
    };

    public DimensionBalleDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public DimensionBalle createDimensionBalle(long rayon) {

        ContentValues values = new ContentValues();
        values.put(TABLE_DIMENSIONBALLE_COLUMN_RAYON, rayon);
        long insertId = database.insert(TABLE_DIMENSIONBALLE, null, values);
        Cursor cursor = database.query(TABLE_DIMENSIONBALLE,
                allColumns, TABLE_DIMENSIONBALLE_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        DimensionBalle newDimensionBalle = cursorToDimensionBalle(cursor);
        cursor.close();
        return newDimensionBalle;
    }

    public void deleteDimensionBalle(DimensionBalle dimensionBalle) {

        long id = dimensionBalle.getId();
        System.out.println("DimensionBalle deleted with id: " + id);
        database.delete(TABLE_DIMENSIONBALLE, TABLE_DIMENSIONBALLE_COLUMN_ID
                + " = " + id, null);
    }

    public List<DimensionBalle> getAllDimensionBalles() {

        List<DimensionBalle> dimensionBalles = new ArrayList<DimensionBalle>();

        Cursor cursor = database.query(TABLE_DIMENSIONBALLE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DimensionBalle dimensionBalle = cursorToDimensionBalle(cursor);
            dimensionBalles.add(dimensionBalle);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return dimensionBalles;
    }

    private DimensionBalle cursorToDimensionBalle(Cursor cursor) {

        DimensionBalle dimensionBalle = new DimensionBalle();
        dimensionBalle.setId(cursor.getLong(0));
        dimensionBalle.setRayon(cursor.getLong(1));
        return dimensionBalle;
    }
}
