package com.alex.arkanoidprototype.database.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alex.arkanoidprototype.database.SQLiteHelper;
import com.alex.arkanoidprototype.database.datamodel.Position;

import java.util.ArrayList;
import java.util.List;

public class PositionDataSource {

    public static final String TABLE_POSITION = "Position";
    public static final String TABLE_POSITION_COLUMN_ID = "ID";
    public static final String TABLE_POSITION_COLUMN_POSITION_X = "PositionX";
    public static final String TABLE_POSITION_COLUMN_POSITION_Y = "PositionY";

    public static String create(){
        return "create table " + TABLE_POSITION + "("
                + TABLE_POSITION_COLUMN_ID + " integer primary key autoincrement, "
                + TABLE_POSITION_COLUMN_POSITION_X + " integer not null, "
                + TABLE_POSITION_COLUMN_POSITION_Y + " integer not null); ";
    }

    public static String drop(){
        return "DROP TABLE IF EXISTS " + TABLE_POSITION +"; ";
    }

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
            TABLE_POSITION_COLUMN_ID,
            TABLE_POSITION_COLUMN_POSITION_X,
            TABLE_POSITION_COLUMN_POSITION_Y
    };

    public PositionDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Position createPosition(long positionX, long positionY) {

        ContentValues values = new ContentValues();
        values.put(TABLE_POSITION_COLUMN_POSITION_X, positionX);
        values.put(TABLE_POSITION_COLUMN_POSITION_Y, positionY);
        long insertId = database.insert(TABLE_POSITION, null, values);
        Cursor cursor = database.query(TABLE_POSITION,
                allColumns, TABLE_POSITION_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Position newPosition = cursorToPosition(cursor);
        cursor.close();
        return newPosition;
    }

    public void deletePosition(Position position) {

        long id = position.getId();
        System.out.println("Position deleted with id: " + id);
        database.delete(TABLE_POSITION, TABLE_POSITION_COLUMN_ID
                + " = " + id, null);
    }

    public List<Position> getAllPositions() {

        List<Position> positions = new ArrayList<Position>();

        Cursor cursor = database.query(TABLE_POSITION,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Position position = cursorToPosition(cursor);
            positions.add(position);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return positions;
    }

    private Position cursorToPosition(Cursor cursor) {

        Position position = new Position();
        position.setId(cursor.getLong(0));
        position.setPositionX(cursor.getLong(1));
        position.setPositionY(cursor.getLong(2));
        return position;
    }
}
