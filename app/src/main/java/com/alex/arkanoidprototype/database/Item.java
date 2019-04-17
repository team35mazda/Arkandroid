package com.alex.arkanoidprototype.database;

import com.orm.SugarRecord;

public class Item extends SugarRecord {
    private ItemType itemType;
    private Couleur couleur;

    public Item() {
    }

    public Item(ItemType itemType, Couleur couleur) {
        this.itemType = itemType;
        this.couleur = couleur;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }
}
