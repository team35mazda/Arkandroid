package com.alex.arkanoidprototype.database.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alex.arkanoidprototype.database.SQLiteHelper;
import com.alex.arkanoidprototype.database.datamodel.DimensionBalleItem;

import java.util.ArrayList;
import java.util.List;

public class DimensionBalleItemDataSource {

    public static final String TABLE_DIMENSION_BALLE_ITEM = "DimensionBalleItem";
    public static final String TABLE_DIMENSION_BALLE_ITEM_COLUMN_ID = "ID";
    public static final String TABLE_DIMENSION_BALLE_ITEM_COLUMN_ID_ITEM = "IDItem";
    public static final String TABLE_DIMENSION_BALLE_ITEM_COLUMN_ID_DIMENSION_BALLE = "IDDimensionBalle";

    public static String create(){
        return "create table " + TABLE_DIMENSION_BALLE_ITEM + "("
                + TABLE_DIMENSION_BALLE_ITEM_COLUMN_ID + " integer primary key autoincrement, "
                + TABLE_DIMENSION_BALLE_ITEM_COLUMN_ID_ITEM + " integer not null, "
                + TABLE_DIMENSION_BALLE_ITEM_COLUMN_ID_DIMENSION_BALLE + " integer not null); ";
    }

    public static String drop(){
        return "DROP TABLE IF EXISTS " + TABLE_DIMENSION_BALLE_ITEM +"; ";
    }

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
            TABLE_DIMENSION_BALLE_ITEM_COLUMN_ID,
            TABLE_DIMENSION_BALLE_ITEM_COLUMN_ID_ITEM,
            TABLE_DIMENSION_BALLE_ITEM_COLUMN_ID_DIMENSION_BALLE
    };

    public DimensionBalleItemDataSource(Context context) { dbHelper = new SQLiteHelper(context); }

    public void open() throws SQLException { database = dbHelper.getWritableDatabase(); }

    public void close() {
        dbHelper.close();
    }

    public DimensionBalleItem createDimensionBalleItem(long idItem,long idDimensionBalle) {

        ContentValues values = new ContentValues();
        values.put(TABLE_DIMENSION_BALLE_ITEM_COLUMN_ID_ITEM, idItem);
        values.put(TABLE_DIMENSION_BALLE_ITEM_COLUMN_ID_DIMENSION_BALLE, idDimensionBalle);
        long insertId = database.insert(TABLE_DIMENSION_BALLE_ITEM, null, values);
        Cursor cursor = database.query(TABLE_DIMENSION_BALLE_ITEM,
                allColumns, TABLE_DIMENSION_BALLE_ITEM_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        DimensionBalleItem newDimensionBalleItem = cursorToDimensionBalleItem(cursor);
        cursor.close();
        return newDimensionBalleItem;
    }

    public void deleteDimensionBalleItem(DimensionBalleItem dimensionBalleItem) {

        long id = dimensionBalleItem.getId();
        System.out.println("DiemsionBalleItem deleted with id: " + id);
        database.delete(TABLE_DIMENSION_BALLE_ITEM, TABLE_DIMENSION_BALLE_ITEM_COLUMN_ID
                + " = " + id, null);
    }

    public List<DimensionBalleItem> getAllDimensionBalleItems() {

        List<DimensionBalleItem> dimensionBalleItems = new ArrayList<DimensionBalleItem>();

        Cursor cursor = database.query(TABLE_DIMENSION_BALLE_ITEM,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DimensionBalleItem dimensionBalleItem = cursorToDimensionBalleItem(cursor);
            dimensionBalleItems.add(dimensionBalleItem);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return dimensionBalleItems;
    }

    private DimensionBalleItem cursorToDimensionBalleItem(Cursor cursor) {

        DimensionBalleItem dimensionBalleItem = new DimensionBalleItem();
        dimensionBalleItem.setId(cursor.getLong(0));
        dimensionBalleItem.setIdItem(cursor.getLong(1));
        dimensionBalleItem.setIdDimensionBalle(cursor.getLong(2));
        return dimensionBalleItem;
    }
}
