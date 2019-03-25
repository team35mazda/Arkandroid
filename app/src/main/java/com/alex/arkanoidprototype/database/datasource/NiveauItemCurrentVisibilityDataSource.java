package com.alex.arkanoidprototype.database.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alex.arkanoidprototype.database.SQLiteHelper;
import com.alex.arkanoidprototype.database.datamodel.NiveauItemCurrentVisibility;

import java.util.ArrayList;
import java.util.List;

public class NiveauItemCurrentVisibilityDataSource {

    private long id;
    private short isVisible;

    public static final String TABLE_NIVEAU_ITEM_CURRENT_VISIBILITY = "NiveauItemCurrentVisibility";
    public static final String TABLE_NIVEAU_ITEM_CURRENT_VISIBILITY_COLUMN_ID = "ID";
    public static final String TABLE_NIVEAU_ITEM_CURRENT_VISIBILITY_COLUMN_IS_VISIBLE = "IsVisible";

    public static String create(){
        return "create table " + TABLE_NIVEAU_ITEM_CURRENT_VISIBILITY + "("
                + TABLE_NIVEAU_ITEM_CURRENT_VISIBILITY_COLUMN_ID + " integer primary key autoincrement, "
                + TABLE_NIVEAU_ITEM_CURRENT_VISIBILITY_COLUMN_IS_VISIBLE + " integer not null); ";
    }

    public static String drop(){
        return "DROP TABLE IF EXISTS " + TABLE_NIVEAU_ITEM_CURRENT_VISIBILITY +"; ";
    }

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
            TABLE_NIVEAU_ITEM_CURRENT_VISIBILITY_COLUMN_ID,
            TABLE_NIVEAU_ITEM_CURRENT_VISIBILITY_COLUMN_IS_VISIBLE
    };

    public NiveauItemCurrentVisibilityDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public NiveauItemCurrentVisibility createNiveauItemCurrentVisibility(short isVisible) {

        ContentValues values = new ContentValues();
        values.put(TABLE_NIVEAU_ITEM_CURRENT_VISIBILITY_COLUMN_IS_VISIBLE, isVisible);
        long insertId = database.insert(TABLE_NIVEAU_ITEM_CURRENT_VISIBILITY, null, values);
        Cursor cursor = database.query(TABLE_NIVEAU_ITEM_CURRENT_VISIBILITY,
                allColumns, TABLE_NIVEAU_ITEM_CURRENT_VISIBILITY_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        NiveauItemCurrentVisibility newNiveauItemCurrentVisibility = cursorToNiveauItemCurrentVisibility(cursor);
        cursor.close();
        return newNiveauItemCurrentVisibility;
    }

    public void deleteNiveauItemCurrentVisibility(NiveauItemCurrentVisibility couleur) {

        long id = couleur.getId();
        System.out.println("NiveauItemCurrentVisibility deleted with id: " + id);
        database.delete(TABLE_NIVEAU_ITEM_CURRENT_VISIBILITY, TABLE_NIVEAU_ITEM_CURRENT_VISIBILITY_COLUMN_ID
                + " = " + id, null);
    }

    public List<NiveauItemCurrentVisibility> getAllNiveauItemCurrentVisibilities() {

        List<NiveauItemCurrentVisibility> niveauItemCurrentVisibilities = new ArrayList<NiveauItemCurrentVisibility>();

        Cursor cursor = database.query(TABLE_NIVEAU_ITEM_CURRENT_VISIBILITY,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NiveauItemCurrentVisibility niveauItemCurrentVisibility = cursorToNiveauItemCurrentVisibility(cursor);
            niveauItemCurrentVisibilities.add(niveauItemCurrentVisibility);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return niveauItemCurrentVisibilities;
    }

    private NiveauItemCurrentVisibility cursorToNiveauItemCurrentVisibility(Cursor cursor) {

        NiveauItemCurrentVisibility niveauItemCurrentVisibility = new NiveauItemCurrentVisibility();
        niveauItemCurrentVisibility.setId(cursor.getLong(0));
        niveauItemCurrentVisibility.setIsVisible(cursor.getShort(1));
        return niveauItemCurrentVisibility;
    }
}
