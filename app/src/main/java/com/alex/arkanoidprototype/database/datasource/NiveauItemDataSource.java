package com.alex.arkanoidprototype.database.datasource;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.alex.arkanoidprototype.database.SQLiteHelper;
import com.alex.arkanoidprototype.database.datamodel.NiveauItem;

public class NiveauItemDataSource {

    private long id;
    private long idNiveau;
    private long idItem;

    public static final String TABLE_NIVEAU_ITEM = "NiveauItem";
    public static final String TABLE_NIVEAU_ITEM_COLUMN_ID = "ID";
    public static final String TABLE_NIVEAU_ITEM_COLUMN_ID_NIVEAU = "IDNiveau";
    public static final String TABLE_NIVEAU_ITEM_COLUMN_ID_ITEM = "IDItem";

    public static String create(){
        return "create table " + TABLE_NIVEAU_ITEM + "("
                + TABLE_NIVEAU_ITEM_COLUMN_ID + " integer primary key autoincrement, "
                + TABLE_NIVEAU_ITEM_COLUMN_ID_NIVEAU + " integer not null, "
                + TABLE_NIVEAU_ITEM_COLUMN_ID_ITEM + " integer not null); ";
    }

    public static String drop(){
        return "DROP TABLE IF EXISTS " + TABLE_NIVEAU_ITEM +"; ";
    }

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
            TABLE_NIVEAU_ITEM_COLUMN_ID,
            TABLE_NIVEAU_ITEM_COLUMN_ID_NIVEAU,
            TABLE_NIVEAU_ITEM_COLUMN_ID_ITEM
    };

    public NiveauItemDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public NiveauItem createNiveauItem(long idNiveau, long idItem) {

        ContentValues values = new ContentValues();
        values.put(TABLE_NIVEAU_ITEM_COLUMN_ID_NIVEAU, idNiveau);
        values.put(TABLE_NIVEAU_ITEM_COLUMN_ID_ITEM, idItem);
        long insertId = database.insert(TABLE_NIVEAU_ITEM, null, values);
        Cursor cursor = database.query(TABLE_NIVEAU_ITEM,
                allColumns, TABLE_NIVEAU_ITEM_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        NiveauItem newNiveauItem = cursorToNiveauItem(cursor);
        cursor.close();
        return newNiveauItem;
    }

    public void deleteNiveauItem(NiveauItem niveauItem) {

        long id = niveauItem.getId();
        System.out.println("NiveauItem deleted with id: " + id);
        database.delete(TABLE_NIVEAU_ITEM, TABLE_NIVEAU_ITEM_COLUMN_ID
                + " = " + id, null);
    }

    public List<NiveauItem> getAllNiveauItems() {

        List<NiveauItem> niveauItems = new ArrayList<NiveauItem>();

        Cursor cursor = database.query(TABLE_NIVEAU_ITEM,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NiveauItem niveauItem = cursorToNiveauItem(cursor);
            niveauItems.add(niveauItem);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return niveauItems;
    }

    private NiveauItem cursorToNiveauItem(Cursor cursor) {
        NiveauItem niveauItem = new NiveauItem();
        niveauItem.setId(cursor.getLong(0));
        niveauItem.setIdNiveau(cursor.getLong(1));
        niveauItem.setIdItem(cursor.getLong(2));
        return niveauItem;
    }
}
