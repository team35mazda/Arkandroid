package com.alex.arkanoidprototype.database.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alex.arkanoidprototype.database.SQLiteHelper;
import com.alex.arkanoidprototype.database.datamodel.DimensionBase;

import java.util.ArrayList;
import java.util.List;

public class DimensionBaseDataSource {

    public static final String TABLE_DIMENSION_BASE = "DimensionBase";
    public static final String TABLE_DIMENSION_BASE_COLUMN_ID = "ID";
    public static final String TABLE_DIMENSION_BASE_COLUMN_LARGEUR = "Largeur";
    public static final String TABLE_DIMENSION_BASE_COLUMN_LONGUEUR = "Longueur";

    public static String create(){
        return "create table " + TABLE_DIMENSION_BASE + "("
                + TABLE_DIMENSION_BASE_COLUMN_ID + " integer primary key autoincrement, "
                + TABLE_DIMENSION_BASE_COLUMN_LARGEUR + " integer not null, "
                + TABLE_DIMENSION_BASE_COLUMN_LONGUEUR + " integer not null); ";
    }

    public static String drop(){
        return "DROP TABLE IF EXISTS " + TABLE_DIMENSION_BASE +"; ";
    }

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
            TABLE_DIMENSION_BASE_COLUMN_ID,
            TABLE_DIMENSION_BASE_COLUMN_LARGEUR,
            TABLE_DIMENSION_BASE_COLUMN_LONGUEUR
    };

    public DimensionBaseDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public DimensionBase createDimensionBase(long largeur, long longueur) {

        ContentValues values = new ContentValues();
        values.put(TABLE_DIMENSION_BASE_COLUMN_LARGEUR, largeur);
        values.put(TABLE_DIMENSION_BASE_COLUMN_LONGUEUR, longueur);
        long insertId = database.insert(TABLE_DIMENSION_BASE, null, values);
        Cursor cursor = database.query(TABLE_DIMENSION_BASE,
                allColumns, TABLE_DIMENSION_BASE_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        DimensionBase dimensionBase = cursorToDimensionBase(cursor);
        cursor.close();
        return dimensionBase;
    }

    public void deleteDimensionBase(DimensionBase dimensionBase) {

        long id = dimensionBase.getId();
        System.out.println("DimensionBase deleted with id: " + id);
        database.delete(TABLE_DIMENSION_BASE, TABLE_DIMENSION_BASE_COLUMN_ID
                + " = " + id, null);
    }

    public List<DimensionBase> getAllDimensionBases() {

        List<DimensionBase> dimensionBases = new ArrayList<DimensionBase>();

        Cursor cursor = database.query(TABLE_DIMENSION_BASE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DimensionBase dimensionBase = cursorToDimensionBase(cursor);
            dimensionBases.add(dimensionBase);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return dimensionBases;
    }

    private DimensionBase cursorToDimensionBase(Cursor cursor) {

        DimensionBase dimensionBase = new DimensionBase();
        dimensionBase.setId(cursor.getLong(0));
        dimensionBase.setLargeur(cursor.getLong(1));
        dimensionBase.setLongeur(cursor.getLong(2));
        return dimensionBase;
    }

}
