package com.alex.arkanoidprototype.database.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alex.arkanoidprototype.database.SQLiteHelper;
import com.alex.arkanoidprototype.database.datamodel.ItemType;

import java.util.ArrayList;
import java.util.List;

public class ItemTypeDataSource {

    public static final String TABLE_ITEM_TYPE = "ItemType";
    public static final String TABLE_ITEM_TYPE_COLUMN_ID = "ID";
    public static final String TABLE_ITEM_TYPE_COLUMN_CODE = "Code";

    public static String create(){
        return "create table " + TABLE_ITEM_TYPE + "("
                + TABLE_ITEM_TYPE_COLUMN_ID + " integer primary key autoincrement, "
                + TABLE_ITEM_TYPE_COLUMN_CODE + " string not null); ";
    }

    public static String drop(){
        return "DROP TABLE IF EXISTS " + TABLE_ITEM_TYPE +"; ";
    }

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
            TABLE_ITEM_TYPE_COLUMN_ID,
            TABLE_ITEM_TYPE_COLUMN_CODE
    };

    public ItemTypeDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public ItemType createItemType(String code) {

        ContentValues values = new ContentValues();
        values.put(TABLE_ITEM_TYPE_COLUMN_CODE, code);
        long insertId = database.insert(TABLE_ITEM_TYPE, null, values);
        Cursor cursor = database.query(TABLE_ITEM_TYPE,
                allColumns, TABLE_ITEM_TYPE_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        ItemType newItemType = cursorToItemType(cursor);
        cursor.close();
        return newItemType;
    }

    public void deleteItemType(ItemType itemType) {

        long id = itemType.getId();
        System.out.println("ItemType deleted with id: " + id);
        database.delete(TABLE_ITEM_TYPE, TABLE_ITEM_TYPE_COLUMN_ID
                + " = " + id, null);
    }

    public List<ItemType> getAllItemTypes() {

        List<ItemType> itemTypes = new ArrayList<ItemType>();

        Cursor cursor = database.query(TABLE_ITEM_TYPE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ItemType itemType = cursorToItemType(cursor);
            itemTypes.add(itemType);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return itemTypes;
    }

    private ItemType cursorToItemType(Cursor cursor) {

        ItemType itemType = new ItemType();
        itemType.setId(cursor.getLong(0));
        itemType.setCode(cursor.getString(1));
        return itemType;
    }
}
