package com.alex.arkanoidprototype.database;

import com.orm.SugarRecord;

public class Position  extends SugarRecord {
    private long positionX;
    private long positionY;

    public Position() {
    }

    public Position(long positionX, long positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public long getPositionX() {
        return positionX;
    }

    public void setPositionX(long positionX) {
        this.positionX = positionX;
    }

    public long getPositionY() {
        return positionY;
    }

    public void setPositionY(long positionY) {
        this.positionY = positionY;
    }
}
