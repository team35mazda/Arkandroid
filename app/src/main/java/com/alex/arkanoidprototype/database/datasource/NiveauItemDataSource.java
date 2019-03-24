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

    public static final String TABLE_NIVEAUITEM = "niveau";
    public static final String TABLE_NIVEAUITEM_COLUMN_ID = "IDNiveauItem";
    public static final String TABLE_NIVEAUITEM_COLUMN_IDNIVEAU = "IDNiveau";
    public static final String TABLE_NIVEAUITEM_COLUMN_IDITEM = "IDItem";

    public static String create(){
         return "create table "
                + TABLE_NIVEAUITEM + "(" + TABLE_NIVEAUITEM_COLUMN_ID
                + " integer primary key autoincrement, " + TABLE_NIVEAUITEM_COLUMN_IDNIVEAU
                + " integer not null, " + TABLE_NIVEAUITEM_COLUMN_IDITEM
                + " integer not null); ";
    }

    public static String drop(){
        return "DROP TABLE IF EXISTS " + TABLE_NIVEAUITEM +"; ";
    }

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {TABLE_NIVEAUITEM_COLUMN_ID, TABLE_NIVEAUITEM_COLUMN_IDNIVEAU, TABLE_NIVEAUITEM_COLUMN_IDITEM};

    public NiveauItemDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public NiveauItem createNiveauItem(long idNiveau,long idItem) {

        ContentValues values = new ContentValues();
        values.put(TABLE_NIVEAUITEM_COLUMN_IDNIVEAU, idNiveau);
        values.put(TABLE_NIVEAUITEM_COLUMN_IDITEM, idItem);
        long insertId = database.insert(TABLE_NIVEAUITEM, null, values);
        Cursor cursor = database.query(TABLE_NIVEAUITEM,
                allColumns, TABLE_NIVEAUITEM_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        NiveauItem newNiveauItem = cursorToNiveauItem(cursor);
        cursor.close();
        return newNiveauItem;
    }

    public void deleteComment(NiveauItem comment) {

        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(TABLE_NIVEAUITEM, TABLE_NIVEAUITEM_COLUMN_ID
                + " = " + id, null);
    }

    public List<NiveauItem> getAllNiveauItems() {

        List<NiveauItem> niveauItems = new ArrayList<NiveauItem>();

        Cursor cursor = database.query(TABLE_NIVEAUITEM,
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
        niveauItem.setNiveauItem(cursor.getLong(1),cursor.getLong(2));
        return niveauItem;
    }
}
