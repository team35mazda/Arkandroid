package com.alex.arkanoidprototype.database;

import android.content.Context;

import com.alex.arkanoidprototype.database.datamodel.NiveauItemCurrentVisibility;
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

public class DatabaseManager {

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

    public DatabaseManager(Context context) {
        couleurDataSource = new CouleurDataSource(context);
        dimensionBalleDataSource = new DimensionBalleDataSource(context);
        dimensionBalleItemDataSource = new DimensionBalleItemDataSource(context);
        dimensionBaseDataSource = new DimensionBaseDataSource(context);
        dimensionBaseItemDataSource = new DimensionBaseItemDataSource(context);
        itemDataSource = new ItemDataSource(context);
        itemMouvementDataSource = new ItemMouvementDataSource(context);
        itemTypeDataSource = new ItemTypeDataSource(context);
        mouvementDataSource = new MouvementDataSource(context);
        niveauDataSource = new NiveauDataSource(context);
        niveauItemCurrentMouvementDataSource = new NiveauItemCurrentMouvementDataSource(context);
        niveauItemCurrentPositionDataSource = new NiveauItemCurrentPositionDataSource(context);
        niveauItemCurrentVisibilityDataSource = new NiveauItemCurrentVisibilityDataSource(context);
        niveauItemDataSource = new NiveauItemDataSource(context);
        positionDataSource = new PositionDataSource(context);
        positionItemDataSource = new PositionItemDataSource(context);
        this.open();
    }

    public void open() {
        couleurDataSource.open();
        dimensionBalleDataSource.open();
        dimensionBalleItemDataSource.open();
        dimensionBaseDataSource.open();
        dimensionBaseItemDataSource.open();
        itemDataSource.open();
        itemMouvementDataSource.open();
        itemTypeDataSource.open();
        mouvementDataSource.open();
        niveauDataSource.open();
        niveauItemCurrentMouvementDataSource.open();
        niveauItemCurrentPositionDataSource.open();
        niveauItemCurrentVisibilityDataSource.open();
        niveauItemDataSource.open();
        positionDataSource.open();
        positionItemDataSource.open();
    }

    public void close() {
        couleurDataSource.close();
        dimensionBalleDataSource.close();
        dimensionBalleItemDataSource.close();
        dimensionBaseDataSource.close();
        dimensionBaseItemDataSource.close();
        itemDataSource.close();
        itemMouvementDataSource.close();
        itemTypeDataSource.close();
        mouvementDataSource.close();
        niveauDataSource.close();
        niveauItemCurrentMouvementDataSource.close();
        niveauItemCurrentPositionDataSource.close();
        niveauItemCurrentVisibilityDataSource.close();
        niveauItemDataSource.close();
        positionDataSource.close();
        positionItemDataSource.close();
    }

    public CouleurDataSource getCouleurDataSource(){ return this.couleurDataSource;}
    public DimensionBalleDataSource getDimensionBalleDataSource(){ return this.dimensionBalleDataSource;}
    public DimensionBalleItemDataSource getDimensionBalleItemDataSource(){ return this.dimensionBalleItemDataSource;}
    public DimensionBaseDataSource getDimensionBaseDataSource(){ return this.dimensionBaseDataSource;}
    public DimensionBaseItemDataSource getDimensionBaseItemDataSource(){ return this.dimensionBaseItemDataSource;}
    public ItemDataSource getItemDataSource(){ return this.itemDataSource;}
    public ItemMouvementDataSource getItemMouvementDataSource(){ return this.itemMouvementDataSource;}
    public ItemTypeDataSource getItemTypeDataSource(){ return this.itemTypeDataSource;}
    public MouvementDataSource getMouvementDataSource(){ return this.mouvementDataSource;}
    public NiveauDataSource getNiveauDataSource(){ return this.niveauDataSource;}
    public NiveauItemCurrentMouvementDataSource getNiveauItemCurrentMouvementDataSource(){ return this.niveauItemCurrentMouvementDataSource;}
    public NiveauItemCurrentPositionDataSource getNiveauItemCurrentPositionDataSource(){ return this.niveauItemCurrentPositionDataSource;}
    public NiveauItemCurrentVisibilityDataSource getNiveauItemCurrentVisibility(){ return this.niveauItemCurrentVisibilityDataSource;}
    public NiveauItemDataSource getNiveauItemDataSource(){ return this.niveauItemDataSource;}
    public PositionDataSource getPositionDataSource(){ return this.positionDataSource;}
    public PositionItemDataSource getPositionItemDataSource(){ return this.positionItemDataSource;}





}
