package com.alex.arkanoidprototype.database.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alex.arkanoidprototype.database.SQLiteHelper;
import com.alex.arkanoidprototype.database.datamodel.Mouvement;

import java.util.ArrayList;
import java.util.List;

public class MouvementDataSource {

    private long id;
    private long vitesseX;
    private long vitesseY;
    private long directionX;
    private long directionY;

    public static final String TABLE_MOUVEMENT = "Mouvement";
    public static final String TABLE_MOUVEMENT_COLUMN_ID = "ID";
    public static final String TABLE_MOUVEMENT_COLUMN_VITESSE_X = "VitesseX";
    public static final String TABLE_MOUVEMENT_COLUMN_VITESSE_Y = "VitesseY";
    public static final String TABLE_MOUVEMENT_COLUMN_DIRECTION_X = "DirectionX";
    public static final String TABLE_MOUVEMENT_COLUMN_DIRECTION_Y = "DirectionY";

    public static String create(){
        return "create table " + TABLE_MOUVEMENT + "("
                + TABLE_MOUVEMENT_COLUMN_ID + " integer primary key autoincrement, "
                + TABLE_MOUVEMENT_COLUMN_VITESSE_X + " integer not null, "
                + TABLE_MOUVEMENT_COLUMN_VITESSE_Y + " integer not null, "
                + TABLE_MOUVEMENT_COLUMN_DIRECTION_X + " integer not null, "
                + TABLE_MOUVEMENT_COLUMN_DIRECTION_Y + " integer not null); ";
    }

    public static String drop(){
        return "DROP TABLE IF EXISTS " + TABLE_MOUVEMENT +"; ";
    }

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
            TABLE_MOUVEMENT_COLUMN_ID,
            TABLE_MOUVEMENT_COLUMN_VITESSE_X,
            TABLE_MOUVEMENT_COLUMN_VITESSE_Y,
            TABLE_MOUVEMENT_COLUMN_DIRECTION_X,
            TABLE_MOUVEMENT_COLUMN_DIRECTION_Y
    };

    public MouvementDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Mouvement createMouvement(long vitesseX, long vitesseY, long directionX, long directionY) {

        ContentValues values = new ContentValues();
        values.put(TABLE_MOUVEMENT_COLUMN_VITESSE_X, vitesseX);
        values.put(TABLE_MOUVEMENT_COLUMN_VITESSE_Y, vitesseY);
        values.put(TABLE_MOUVEMENT_COLUMN_DIRECTION_X, directionX);
        values.put(TABLE_MOUVEMENT_COLUMN_DIRECTION_Y, directionY);
        long insertId = database.insert(TABLE_MOUVEMENT, null, values);
        Cursor cursor = database.query(TABLE_MOUVEMENT,
                allColumns, TABLE_MOUVEMENT_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Mouvement newMouvement = cursorToMouvement(cursor);
        cursor.close();
        return newMouvement;
    }

    public void deleteMouvement(Mouvement couleur) {

        long id = couleur.getId();
        System.out.println("Mouvement deleted with id: " + id);
        database.delete(TABLE_MOUVEMENT, TABLE_MOUVEMENT_COLUMN_ID
                + " = " + id, null);
    }

    public List<Mouvement> getAllMouvements() {

        List<Mouvement> mouvements = new ArrayList<Mouvement>();

        Cursor cursor = database.query(TABLE_MOUVEMENT,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Mouvement mouvement = cursorToMouvement(cursor);
            mouvements.add(mouvement);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return mouvements;
    }

    private Mouvement cursorToMouvement(Cursor cursor) {

        Mouvement mouvement = new Mouvement();
        mouvement.setId(cursor.getLong(0));
        mouvement.setVitesseX(cursor.getLong(1));
        mouvement.setVitesseY(cursor.getLong(2));
        mouvement.setDirectionX(cursor.getLong(3));
        mouvement.setDirectionY(cursor.getLong(4));
        return mouvement;
    }
}
