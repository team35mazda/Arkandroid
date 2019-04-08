package com.alex.arkanoidprototype.model;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alex.arkanoidprototype.R;

public class UserProfile {

    private int actualLevel;
    private int maxLife;
    private int life;

    public UserProfile(int actualLevel, int life){
        this.maxLife = life;
        this.life = this.maxLife;
        this.actualLevel = actualLevel;
    }

    public int getActualLevel() {
        return actualLevel;
    }

    public boolean lostLife(){
        life--;

        return (life==0);
    }

    public int getLife() {
        return life;
    }

    public void draw(View view){

        TextView lifeView = view.findViewById(R.id.life);

        TextView levelView = view.findViewById(R.id.level);

        lifeView.setText(Integer.toString(this.life));
        levelView.setText(Integer.toString(this.actualLevel));
    }

    public void levelUp(){
        actualLevel++;
    }

    public void resetLife(){
        this.life = this.maxLife;
    }
}
