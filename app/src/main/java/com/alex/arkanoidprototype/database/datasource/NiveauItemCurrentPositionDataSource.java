package com.alex.arkanoidprototype.database.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alex.arkanoidprototype.database.SQLiteHelper;
import com.alex.arkanoidprototype.database.datamodel.NiveauItemCurrentPosition;

import java.util.ArrayList;
import java.util.List;

public class NiveauItemCurrentPositionDataSource {

    private long id;
    private long positionX;
    private long positionY;

    public static final String TABLE_NIVEAU_ITEM_CURRENT_POSITION = "NiveauItemCurrentPosition";
    public static final String TABLE_NIVEAU_ITEM_CURRENT_POSITION_COLUMN_ID = "ID";
    public static final String TABLE_NIVEAU_ITEM_CURRENT_POSITION_COLUMN_POSITION_X = "PositionX";
    public static final String TABLE_NIVEAU_ITEM_CURRENT_POSITION_COLUMN_POSITION_Y = "PositionY";

    public static String create(){
        return "create table " + TABLE_NIVEAU_ITEM_CURRENT_POSITION + "("
                + TABLE_NIVEAU_ITEM_CURRENT_POSITION_COLUMN_ID + " integer primary key autoincrement, "
                + TABLE_NIVEAU_ITEM_CURRENT_POSITION_COLUMN_POSITION_X + " integer not null, "
                + TABLE_NIVEAU_ITEM_CURRENT_POSITION_COLUMN_POSITION_Y + " integer not null); ";
    }

    public static String drop(){
        return "DROP TABLE IF EXISTS " + TABLE_NIVEAU_ITEM_CURRENT_POSITION +"; ";
    }

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
            TABLE_NIVEAU_ITEM_CURRENT_POSITION_COLUMN_ID,
            TABLE_NIVEAU_ITEM_CURRENT_POSITION_COLUMN_POSITION_X,
            TABLE_NIVEAU_ITEM_CURRENT_POSITION_COLUMN_POSITION_Y
    };

    public NiveauItemCurrentPositionDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public NiveauItemCurrentPosition createNiveauItemCurrentPosition(long positionX, long positionY) {

        ContentValues values = new ContentValues();
        values.put(TABLE_NIVEAU_ITEM_CURRENT_POSITION_COLUMN_POSITION_X, positionX);
        values.put(TABLE_NIVEAU_ITEM_CURRENT_POSITION_COLUMN_POSITION_Y, positionY);
        long insertId = database.insert(TABLE_NIVEAU_ITEM_CURRENT_POSITION, null, values);
        Cursor cursor = database.query(TABLE_NIVEAU_ITEM_CURRENT_POSITION,
                allColumns, TABLE_NIVEAU_ITEM_CURRENT_POSITION_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        NiveauItemCurrentPosition newNiveauItemCurrentPosition = cursorToNiveauItemCurrentPosition(cursor);
        cursor.close();
        return newNiveauItemCurrentPosition;
    }

    public void deleteNiveauItemCurrentPosition(NiveauItemCurrentPosition niveauItemCurrentPosition) {

        long id = niveauItemCurrentPosition.getId();
        System.out.println("NiveauItemCurrentPosition deleted with id: " + id);
        database.delete(TABLE_NIVEAU_ITEM_CURRENT_POSITION, TABLE_NIVEAU_ITEM_CURRENT_POSITION_COLUMN_ID
                + " = " + id, null);
    }

    public List<NiveauItemCurrentPosition> getAllNiveauItemCurrentPositions() {

        List<NiveauItemCurrentPosition> niveauItemCurrentPositions = new ArrayList<NiveauItemCurrentPosition>();

        Cursor cursor = database.query(TABLE_NIVEAU_ITEM_CURRENT_POSITION,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NiveauItemCurrentPosition niveauItemCurrentPosition = cursorToNiveauItemCurrentPosition(cursor);
            niveauItemCurrentPositions.add(niveauItemCurrentPosition);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return niveauItemCurrentPositions;
    }

    private NiveauItemCurrentPosition cursorToNiveauItemCurrentPosition(Cursor cursor) {
        NiveauItemCurrentPosition niveauItemCurrentPosition = new NiveauItemCurrentPosition();
        niveauItemCurrentPosition.setId(cursor.getLong(0));
        niveauItemCurrentPosition.setPositionX(cursor.getLong(1));
        niveauItemCurrentPosition.setPositionY(cursor.getLong(2));
        return niveauItemCurrentPosition;
    }
}
