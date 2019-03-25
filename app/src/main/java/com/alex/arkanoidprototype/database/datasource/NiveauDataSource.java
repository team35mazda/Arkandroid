package com.alex.arkanoidprototype.database.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alex.arkanoidprototype.database.SQLiteHelper;
import com.alex.arkanoidprototype.database.datamodel.Niveau;

import java.util.ArrayList;
import java.util.List;

public class NiveauDataSource {

    public static final String TABLE_NIVEAU = "Niveau";
    public static final String TABLE_NIVEAU_COLUMN_ID = "ID";
    public static final String TABLE_NIVEAU_COLUMN_CODE = "Code";

    public static String create(){
        return "create table " + TABLE_NIVEAU + "("
                + TABLE_NIVEAU_COLUMN_ID + " integer primary key autoincrement, "
                + TABLE_NIVEAU_COLUMN_CODE + " string not null); ";
    }

    public static String drop(){
        return "DROP TABLE IF EXISTS " + TABLE_NIVEAU +"; ";
    }

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
            TABLE_NIVEAU_COLUMN_ID,
            TABLE_NIVEAU_COLUMN_CODE
    };

    public NiveauDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Niveau createNiveau(String code) {

        ContentValues values = new ContentValues();
        values.put(TABLE_NIVEAU_COLUMN_CODE, code);
        long insertId = database.insert(TABLE_NIVEAU, null, values);
        Cursor cursor = database.query(TABLE_NIVEAU,
                allColumns, TABLE_NIVEAU_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Niveau newNiveau = cursorToNiveau(cursor);
        cursor.close();
        return newNiveau;
    }

    public void deleteNiveau(Niveau couleur) {

        long id = couleur.getId();
        System.out.println("Niveau deleted with id: " + id);
        database.delete(TABLE_NIVEAU, TABLE_NIVEAU_COLUMN_ID
                + " = " + id, null);
    }

    public List<Niveau> getAllNiveaux() {

        List<Niveau> niveaux = new ArrayList<Niveau>();

        Cursor cursor = database.query(TABLE_NIVEAU,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Niveau niveau = cursorToNiveau(cursor);
            niveaux.add(niveau);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return niveaux;
    }

    private Niveau cursorToNiveau(Cursor cursor) {

        Niveau niveau = new Niveau();
        niveau.setId(cursor.getLong(0));
        niveau.setCode(cursor.getString(1));
        return niveau;
    }
}
