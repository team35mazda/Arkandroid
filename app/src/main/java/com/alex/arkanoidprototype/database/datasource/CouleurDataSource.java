package com.alex.arkanoidprototype.database.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alex.arkanoidprototype.database.SQLiteHelper;
import com.alex.arkanoidprototype.database.datamodel.Couleur;
import com.alex.arkanoidprototype.database.datamodel.NiveauItem;

import java.util.ArrayList;
import java.util.List;

public class CouleurDataSource {

    public static final String TABLE_COULEUR = "Couleur";
    public static final String TABLE_COULEUR_COLUMN_ID = "ID";
    public static final String TABLE_COULEUR_COLUMN_CODE = "Code";
    public static final String TABLE_COULEUR_COLUMN_ROUGE = "Rouge";
    public static final String TABLE_COULEUR_COLUMN_VERT = "Vert";
    public static final String TABLE_COULEUR_COLUMN_BLEU = "Bleu";

    public static String create(){
         return "create table " + TABLE_COULEUR + "("
                 + TABLE_COULEUR_COLUMN_ID + " integer primary key autoincrement, "
                 + TABLE_COULEUR_COLUMN_CODE + " string not null, "
                 + TABLE_COULEUR_COLUMN_ROUGE + " integer not null, "
                 + TABLE_COULEUR_COLUMN_VERT + " integer not null, "
                 + TABLE_COULEUR_COLUMN_BLEU + " integer not null); ";
    }

    public static String drop(){
        return "DROP TABLE IF EXISTS " + TABLE_COULEUR +"; ";
    }

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
            TABLE_COULEUR_COLUMN_ID,
            TABLE_COULEUR_COLUMN_CODE,
            TABLE_COULEUR_COLUMN_ROUGE,
            TABLE_COULEUR_COLUMN_VERT,
            TABLE_COULEUR_COLUMN_BLEU
    };

    public CouleurDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Couleur createCouleur(String code, long rouge, long vert, long bleu) {

        ContentValues values = new ContentValues();
        values.put(TABLE_COULEUR_COLUMN_CODE, code);
        values.put(TABLE_COULEUR_COLUMN_ROUGE, rouge);
        values.put(TABLE_COULEUR_COLUMN_VERT, vert);
        values.put(TABLE_COULEUR_COLUMN_BLEU, bleu);
        long insertId = database.insert(TABLE_COULEUR, null, values);
        Cursor cursor = database.query(TABLE_COULEUR,
                allColumns, TABLE_COULEUR_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Couleur newCouleur = cursorToCouleur(cursor);
        cursor.close();
        return newCouleur;
    }

    public void deleteComment(Couleur couleur) {

        long id = couleur.getId();
        System.out.println("Couleur deleted with id: " + id);
        database.delete(TABLE_COULEUR, TABLE_COULEUR_COLUMN_ID
                + " = " + id, null);
    }

    public List<Couleur> getAllCouleurs() {

        List<Couleur> couleurs = new ArrayList<Couleur>();

        Cursor cursor = database.query(TABLE_COULEUR,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Couleur couleur = cursorToCouleur(cursor);
            couleurs.add(couleur);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return couleurs;
    }

    private Couleur cursorToCouleur(Cursor cursor) {

        Couleur couleur = new Couleur();
        couleur.setId(cursor.getLong(0));
        couleur.setCode(cursor.getString(1));
        couleur.setRouge(cursor.getLong(2));
        couleur.setVert(cursor.getLong(3));
        couleur.setBleu(cursor.getLong(4));
        return couleur;
    }
}
