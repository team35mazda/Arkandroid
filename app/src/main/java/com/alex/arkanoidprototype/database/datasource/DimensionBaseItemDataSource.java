package com.alex.arkanoidprototype.database.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alex.arkanoidprototype.database.SQLiteHelper;
import com.alex.arkanoidprototype.database.datamodel.DimensionBaseItem;

import java.util.ArrayList;
import java.util.List;

public class DimensionBaseItemDataSource {

    public static final String TABLE_DIMENSION_BASE_ITEM = "DimensionBaseItem";
    public static final String TABLE_DIMENSION_BASE_ITEM_COLUMN_ID = "ID";
    public static final String TABLE_DIMENSION_BASE_ITEM_COLUMN_ID_ITEM = "IDItem";
    public static final String TABLE_DIMENSION_BASE_ITEM_COLUMN_ID_DIMENSION_BASE = "IDDimensionBase";

    public static String create(){
        return "create table " + TABLE_DIMENSION_BASE_ITEM + "("
                + TABLE_DIMENSION_BASE_ITEM_COLUMN_ID + " integer primary key autoincrement, "
                + TABLE_DIMENSION_BASE_ITEM_COLUMN_ID_ITEM + " integer not null, "
                + TABLE_DIMENSION_BASE_ITEM_COLUMN_ID_DIMENSION_BASE + " integer not null); ";
    }

    public static String drop(){
        return "DROP TABLE IF EXISTS " + TABLE_DIMENSION_BASE_ITEM +"; ";
    }

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
            TABLE_DIMENSION_BASE_ITEM_COLUMN_ID,
            TABLE_DIMENSION_BASE_ITEM_COLUMN_ID_ITEM,
            TABLE_DIMENSION_BASE_ITEM_COLUMN_ID_DIMENSION_BASE
    };

    public DimensionBaseItemDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public DimensionBaseItem createDimensionBaseItem(long idItem, long idDimensionBase) {

        ContentValues values = new ContentValues();
        values.put(TABLE_DIMENSION_BASE_ITEM_COLUMN_ID_ITEM, idItem);
        values.put(TABLE_DIMENSION_BASE_ITEM_COLUMN_ID_DIMENSION_BASE, idDimensionBase);
        long insertId = database.insert(TABLE_DIMENSION_BASE_ITEM, null, values);
        Cursor cursor = database.query(TABLE_DIMENSION_BASE_ITEM,
                allColumns, TABLE_DIMENSION_BASE_ITEM_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        DimensionBaseItem dimensionBaseItem = cursorToDimensionBaseItem(cursor);
        cursor.close();
        return dimensionBaseItem;
    }

    public void deleteDimensionBaseItem(DimensionBaseItem dimensionBaseItem) {

        long id = dimensionBaseItem.getId();
        System.out.println("DimensionBaseItem deleted with id: " + id);
        database.delete(TABLE_DIMENSION_BASE_ITEM, TABLE_DIMENSION_BASE_ITEM_COLUMN_ID
                + " = " + id, null);
    }

    public List<DimensionBaseItem> getAllDimensionBaseItems() {

        List<DimensionBaseItem> dimensionBaseItems = new ArrayList<DimensionBaseItem>();

        Cursor cursor = database.query(TABLE_DIMENSION_BASE_ITEM,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DimensionBaseItem dimensionBaseItem = cursorToDimensionBaseItem(cursor);
            dimensionBaseItems.add(dimensionBaseItem);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return dimensionBaseItems;
    }

    private DimensionBaseItem cursorToDimensionBaseItem(Cursor cursor) {
        DimensionBaseItem dimensionBaseItem = new DimensionBaseItem();
        dimensionBaseItem.setId(cursor.getLong(0));
        dimensionBaseItem.setIdItem(cursor.getLong(1));
        dimensionBaseItem.setIdDimensionBase(cursor.getLong(2));
        return dimensionBaseItem;
    }
}
