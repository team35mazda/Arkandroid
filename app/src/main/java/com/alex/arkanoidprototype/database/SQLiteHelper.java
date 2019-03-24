package com.alex.arkanoidprototype.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.alex.arkanoidprototype.database.datamodel.NiveauItem;
import com.alex.arkanoidprototype.database.datasource.NiveauItemDataSource;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "commments.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(NiveauItemDataSource.create());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL(NiveauItemDataSource.drop());
        onCreate(db);
    }
}

