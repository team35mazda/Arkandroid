package com.alex.arkanoidprototype.database.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alex.arkanoidprototype.database.SQLiteHelper;
import com.alex.arkanoidprototype.database.datamodel.ItemMouvement;

import java.util.ArrayList;
import java.util.List;

public class ItemMouvementDataSource {

    public static final String TABLE_ITEM_MOUVEMENT = "ItemMouvement";
    public static final String TABLE_ITEM_MOUVEMENT_COLUMN_ID = "ID";
    public static final String TABLE_ITEM_MOUVEMENT_COLUMN_ID_ITEM = "IDItem";
    public static final String TABLE_ITEM_MOUVEMENT_COLUMN_ID_MOUVEMENT = "IDMouvement";

    public static String create(){
        return "create table " + TABLE_ITEM_MOUVEMENT + "("
                + TABLE_ITEM_MOUVEMENT_COLUMN_ID + " integer primary key autoincrement, "
                + TABLE_ITEM_MOUVEMENT_COLUMN_ID_ITEM + " integer not null, "
                + TABLE_ITEM_MOUVEMENT_COLUMN_ID_MOUVEMENT + " integer not null); ";
    }

    public static String drop(){
        return "DROP TABLE IF EXISTS " + TABLE_ITEM_MOUVEMENT +"; ";
    }

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
            TABLE_ITEM_MOUVEMENT_COLUMN_ID,
            TABLE_ITEM_MOUVEMENT_COLUMN_ID_ITEM,
            TABLE_ITEM_MOUVEMENT_COLUMN_ID_MOUVEMENT
    };

    public ItemMouvementDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public ItemMouvement createItemMouvement(long idItem, long idMouvement) {

        ContentValues values = new ContentValues();
        values.put(TABLE_ITEM_MOUVEMENT_COLUMN_ID_ITEM, idItem);
        values.put(TABLE_ITEM_MOUVEMENT_COLUMN_ID_MOUVEMENT, idMouvement);
        long insertId = database.insert(TABLE_ITEM_MOUVEMENT, null, values);
        Cursor cursor = database.query(TABLE_ITEM_MOUVEMENT,
                allColumns, TABLE_ITEM_MOUVEMENT_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        ItemMouvement newItemMouvement = cursorToItemMouvement(cursor);
        cursor.close();
        return newItemMouvement;
    }

    public void deleteItemMouvement(ItemMouvement itemMouvement) {

        long id = itemMouvement.getId();
        System.out.println("ItemMouvement deleted with id: " + id);
        database.delete(TABLE_ITEM_MOUVEMENT, TABLE_ITEM_MOUVEMENT_COLUMN_ID
                + " = " + id, null);
    }

    public List<ItemMouvement> getAllItemMouvements() {

        List<ItemMouvement> itemMouvements = new ArrayList<ItemMouvement>();

        Cursor cursor = database.query(TABLE_ITEM_MOUVEMENT,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ItemMouvement itemMouvement = cursorToItemMouvement(cursor);
            itemMouvements.add(itemMouvement);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return itemMouvements;
    }

    private ItemMouvement cursorToItemMouvement(Cursor cursor) {

        ItemMouvement itemMouvement = new ItemMouvement();
        itemMouvement.setId(cursor.getLong(0));
        itemMouvement.setIdItem(cursor.getLong(1));
        itemMouvement.setIdMouvement(cursor.getLong(2));
        return itemMouvement;
    }
}
