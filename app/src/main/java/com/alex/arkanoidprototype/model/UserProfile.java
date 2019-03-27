package com.alex.arkanoidprototype.model;

public class UserProfile {

    private int actualLevel;
    private int life;

    public UserProfile(int actualLevel, int life){

        this.life = life;
        this.actualLevel = actualLevel;
    }

    public int getActualLevel() {
        return actualLevel;
    }

    public int getLife() {
        return life;
    }
}
