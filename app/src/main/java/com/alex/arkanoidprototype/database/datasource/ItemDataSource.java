package com.alex.arkanoidprototype.database.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alex.arkanoidprototype.database.SQLiteHelper;
import com.alex.arkanoidprototype.database.datamodel.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemDataSource {

    public static final String TABLE_ITEM = "Item";
    public static final String TABLE_ITEM_COLUMN_ID = "ID";
    public static final String TABLE_ITEM_COLUMN_ID_ITEM_TYPE = "IDItemType";
    public static final String TABLE_ITEM_COLUMN_ID_POSITION = "IDPosition";
    public static final String TABLE_ITEM_COLUMN_ID_COULEUR = "IDCouleur";
    public static final String TABLE_ITEM_COLUMN_IS_VISIBLE = "IsVisible";

    public static String create(){
        return "create table " + TABLE_ITEM + "("
                + TABLE_ITEM_COLUMN_ID + " integer primary key autoincrement, "
                + TABLE_ITEM_COLUMN_ID_ITEM_TYPE + " integer not null, "
                + TABLE_ITEM_COLUMN_ID_POSITION + " integer not null, "
                + TABLE_ITEM_COLUMN_ID_COULEUR + " integer not null, "
                + TABLE_ITEM_COLUMN_IS_VISIBLE + " integer not null); ";
    }

    public static String drop(){
        return "DROP TABLE IF EXISTS " + TABLE_ITEM +"; ";
    }

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
            TABLE_ITEM_COLUMN_ID,
            TABLE_ITEM_COLUMN_ID_ITEM_TYPE,
            TABLE_ITEM_COLUMN_ID_POSITION,
            TABLE_ITEM_COLUMN_ID_COULEUR,
            TABLE_ITEM_COLUMN_IS_VISIBLE
    };

    public ItemDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Item createItem(long idItemType, long idPosition, long idCouleur, short isVisible) {

        ContentValues values = new ContentValues();
        values.put(TABLE_ITEM_COLUMN_ID_ITEM_TYPE, idItemType);
        values.put(TABLE_ITEM_COLUMN_ID_POSITION, idPosition);
        values.put(TABLE_ITEM_COLUMN_ID_COULEUR, idCouleur);
        values.put(TABLE_ITEM_COLUMN_IS_VISIBLE, isVisible);
        long insertId = database.insert(TABLE_ITEM, null, values);
        Cursor cursor = database.query(TABLE_ITEM,
                allColumns, TABLE_ITEM_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Item newItem = cursorToItem(cursor);
        cursor.close();
        return newItem;
    }

    public void deleteItem(Item item) {

        long id = item.getId();
        System.out.println("Item deleted with id: " + id);
        database.delete(TABLE_ITEM, TABLE_ITEM_COLUMN_ID
                + " = " + id, null);
    }

    public List<Item> getAllItems() {

        List<Item> items = new ArrayList<Item>();

        Cursor cursor = database.query(TABLE_ITEM,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Item item = cursorToItem(cursor);
            items.add(item);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return items;
    }

    private Item cursorToItem(Cursor cursor) {

        Item item = new Item();
        item.setId(cursor.getLong(0));
        item.setIdItemType(cursor.getLong(1));
        item.setIdPosition(cursor.getLong(2));
        item.setIdCouleur(cursor.getLong(3));
        item.setIsVisible(cursor.getShort(4));
        return item;
    }
}
