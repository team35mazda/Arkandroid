package com.alex.arkanoidprototype.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.alex.arkanoidprototype.database.datasource.CouleurDataSource;
import com.alex.arkanoidprototype.database.datasource.DimensionBalleDataSource;
import com.alex.arkanoidprototype.database.datasource.DimensionBalleItemDataSource;
import com.alex.arkanoidprototype.database.datasource.DimensionBaseDataSource;
import com.alex.arkanoidprototype.database.datasource.DimensionBaseItemDataSource;
import com.alex.arkanoidprototype.database.datasource.ItemDataSource;
import com.alex.arkanoidprototype.database.datasource.ItemMouvementDataSource;
import com.alex.arkanoidprototype.database.datasource.ItemTypeDataSource;
import com.alex.arkanoidprototype.database.datasource.MouvementDataSource;
import com.alex.arkanoidprototype.database.datasource.NiveauDataSource;
import com.alex.arkanoidprototype.database.datasource.NiveauItemCurrentMouvementDataSource;
import com.alex.arkanoidprototype.database.datasource.NiveauItemCurrentPositionDataSource;
import com.alex.arkanoidprototype.database.datasource.NiveauItemCurrentVisibilityDataSource;
import com.alex.arkanoidprototype.database.datasource.NiveauItemDataSource;
import com.alex.arkanoidprototype.database.datasource.PositionDataSource;
import com.alex.arkanoidprototype.database.datasource.PositionItemDataSource;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "arkandroid.db";
    private static final int DATABASE_VERSION = 2;

    private CouleurDataSource couleurDataSource;
    private DimensionBalleDataSource dimensionBalleDataSource;
    private DimensionBalleItemDataSource dimensionBalleItemDataSource;
    private DimensionBaseDataSource dimensionBaseDataSource;
    private DimensionBaseItemDataSource dimensionBaseItemDataSource;
    private ItemDataSource itemDataSource;
    private ItemMouvementDataSource itemMouvementDataSource;
    private ItemTypeDataSource itemTypeDataSource;
    private MouvementDataSource mouvementDataSource;
    private NiveauDataSource niveauDataSource;
    private NiveauItemCurrentMouvementDataSource niveauItemCurrentMouvementDataSource;
    private NiveauItemCurrentPositionDataSource niveauItemCurrentPositionDataSource;
    private NiveauItemCurrentVisibilityDataSource niveauItemCurrentVisibilityDataSource;
    private NiveauItemDataSource niveauItemDataSource;
    private PositionDataSource positionDataSource;
    private PositionItemDataSource positionItemDataSource;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CouleurDataSource.create());
        database.execSQL(DimensionBalleDataSource.create());
        database.execSQL(DimensionBalleItemDataSource.create());
        database.execSQL(DimensionBaseDataSource.create());
        database.execSQL(DimensionBaseItemDataSource.create());
        database.execSQL(ItemDataSource.create());
        database.execSQL(ItemMouvementDataSource.create());
        database.execSQL(ItemTypeDataSource.create());
        database.execSQL(MouvementDataSource.create());
        database.execSQL(NiveauDataSource.create());
        database.execSQL(NiveauItemCurrentMouvementDataSource.create());
        database.execSQL(NiveauItemCurrentPositionDataSource.create());
        database.execSQL(NiveauItemCurrentVisibilityDataSource.create());
        database.execSQL(NiveauItemDataSource.create());
        database.execSQL(PositionDataSource.create());
        database.execSQL(PositionItemDataSource.create());
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        database.execSQL(CouleurDataSource.drop());
        database.execSQL(DimensionBalleDataSource.drop());
        database.execSQL(DimensionBalleItemDataSource.drop());
        database.execSQL(DimensionBaseDataSource.drop());
        database.execSQL(DimensionBaseItemDataSource.drop());
        database.execSQL(ItemDataSource.drop());
        database.execSQL(ItemMouvementDataSource.drop());
        database.execSQL(ItemTypeDataSource.drop());
        database.execSQL(MouvementDataSource.drop());
        database.execSQL(NiveauDataSource.drop());
        database.execSQL(NiveauItemCurrentMouvementDataSource.drop());
        database.execSQL(NiveauItemCurrentPositionDataSource.drop());
        database.execSQL(NiveauItemCurrentVisibilityDataSource.drop());
        database.execSQL(NiveauItemDataSource.drop());
        database.execSQL(PositionDataSource.drop());
        database.execSQL(PositionItemDataSource.drop());
        onCreate(database);
    }
}

