package com.alex.arkanoidprototype.database.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alex.arkanoidprototype.database.SQLiteHelper;
import com.alex.arkanoidprototype.database.datamodel.NiveauItemCurrentMouvement;

import java.util.ArrayList;
import java.util.List;

public class NiveauItemCurrentMouvementDataSource {

    private long id;
    private long vitesseX;
    private long vitesseY;
    private long directionX;
    private long directionY;

    public static final String TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT = "NiveauItemCurrentMouvement";
    public static final String TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_ID = "ID";
    public static final String TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_VITESSE_X = "VitesseX";
    public static final String TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_VITESSE_Y = "VitesseY";
    public static final String TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_DIRECTION_X = "DirectionX";
    public static final String TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_DIRECTION_Y = "DirectionY";

    public static String create(){
        return "create table " + TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT + "("
                + TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_ID + " integer primary key autoincrement, "
                + TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_VITESSE_X + " integer not null, "
                + TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_VITESSE_Y + " integer not null, "
                + TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_DIRECTION_X + " integer not null, "
                + TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_DIRECTION_Y + " integer not null); ";
    }

    public static String drop(){
        return "DROP TABLE IF EXISTS " + TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT +"; ";
    }

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
            TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_ID,
            TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_VITESSE_X,
            TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_VITESSE_Y,
            TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_DIRECTION_X,
            TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_DIRECTION_Y
    };

    public NiveauItemCurrentMouvementDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public NiveauItemCurrentMouvement createNiveauItemCurrentMouvement(long vitesseX, long vitesseY, long directionX, long directionY) {

        ContentValues values = new ContentValues();
        values.put(TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_VITESSE_X, vitesseX);
        values.put(TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_VITESSE_Y, vitesseY);
        values.put(TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_DIRECTION_X, directionX);
        values.put(TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_DIRECTION_Y, directionY);
        long insertId = database.insert(TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT, null, values);
        Cursor cursor = database.query(TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT,
                allColumns, TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        NiveauItemCurrentMouvement newNiveauItemCurrentMouvement = cursorToNiveauItemCurrentMouvement(cursor);
        cursor.close();
        return newNiveauItemCurrentMouvement;
    }

    public void deleteNiveauItemCurrentMouvement(NiveauItemCurrentMouvement niveauItemCurrentMouvement) {

        long id = niveauItemCurrentMouvement.getId();
        System.out.println("NiveauItemCurrentMouvement deleted with id: " + id);
        database.delete(TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT, TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT_COLUMN_ID
                + " = " + id, null);
    }

    public List<NiveauItemCurrentMouvement> getAllNiveauItemCurrentMouvements() {

        List<NiveauItemCurrentMouvement> niveauItemCurrentMouvements = new ArrayList<NiveauItemCurrentMouvement>();

        Cursor cursor = database.query(TABLE_NIVEAU_ITEM_CURRENT_MOUVEMENT,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NiveauItemCurrentMouvement niveauItemCurrentMouvement = cursorToNiveauItemCurrentMouvement(cursor);
            niveauItemCurrentMouvements.add(niveauItemCurrentMouvement);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return niveauItemCurrentMouvements;
    }

    private NiveauItemCurrentMouvement cursorToNiveauItemCurrentMouvement(Cursor cursor) {
        NiveauItemCurrentMouvement niveauItemCurrentMouvement = new NiveauItemCurrentMouvement();
        niveauItemCurrentMouvement.setId(cursor.getLong(0));
        niveauItemCurrentMouvement.setVitesseX(cursor.getLong(1));
        niveauItemCurrentMouvement.setVitesseY(cursor.getLong(2));
        niveauItemCurrentMouvement.setDirectionX(cursor.getLong(3));
        niveauItemCurrentMouvement.setDirectionY(cursor.getLong(4));
        return niveauItemCurrentMouvement;
    }
}
