package com.alex.arkanoidprototype.database.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alex.arkanoidprototype.database.SQLiteHelper;
import com.alex.arkanoidprototype.database.datamodel.PositionItem;

import java.util.ArrayList;
import java.util.List;

public class PositionItemDataSource {

    public static final String TABLE_POSITION_ITEM = "PositionItem";
    public static final String TABLE_POSITION_ITEM_COLUMN_ID = "ID";
    public static final String TABLE_POSITION_ITEM_COLUMN_ID_POSITION = "IDPosition";
    public static final String TABLE_POSITION_ITEM_COLUMN_ID_ITEM = "IDItem";

    public static String create(){
        return "create table " + TABLE_POSITION_ITEM + "("
                + TABLE_POSITION_ITEM_COLUMN_ID + " integer primary key autoincrement, "
                + TABLE_POSITION_ITEM_COLUMN_ID_POSITION + " integer not null, "
                + TABLE_POSITION_ITEM_COLUMN_ID_ITEM + " integer not null); ";
    }

    public static String drop(){
        return "DROP TABLE IF EXISTS " + TABLE_POSITION_ITEM +"; ";
    }

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
            TABLE_POSITION_ITEM_COLUMN_ID,
            TABLE_POSITION_ITEM_COLUMN_ID_POSITION,
            TABLE_POSITION_ITEM_COLUMN_ID_ITEM
    };

    public PositionItemDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public PositionItem createPositionItem(long idPosition, long idItem) {

        ContentValues values = new ContentValues();
        values.put(TABLE_POSITION_ITEM_COLUMN_ID_POSITION, idPosition);
        values.put(TABLE_POSITION_ITEM_COLUMN_ID_ITEM, idItem);
        long insertId = database.insert(TABLE_POSITION_ITEM, null, values);
        Cursor cursor = database.query(TABLE_POSITION_ITEM,
                allColumns, TABLE_POSITION_ITEM_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        PositionItem newPositionItem = cursorToPositionItem(cursor);
        cursor.close();
        return newPositionItem;
    }

    public void deletePositionItem(PositionItem positionItem) {

        long id = positionItem.getId();
        System.out.println("PositionItem deleted with id: " + id);
        database.delete(TABLE_POSITION_ITEM, TABLE_POSITION_ITEM_COLUMN_ID
                + " = " + id, null);
    }

    public List<PositionItem> getAllPositionItems() {

        List<PositionItem> positionItems = new ArrayList<PositionItem>();

        Cursor cursor = database.query(TABLE_POSITION_ITEM,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            PositionItem positionItem = cursorToPositionItem(cursor);
            positionItems.add(positionItem);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return positionItems;
    }

    private PositionItem cursorToPositionItem(Cursor cursor) {
        PositionItem positionItem = new PositionItem();
        positionItem.setId(cursor.getLong(0));
        positionItem.setIdPosition(cursor.getLong(1));
        positionItem.setIdItem(cursor.getLong(2));
        return positionItem;
    }
}
