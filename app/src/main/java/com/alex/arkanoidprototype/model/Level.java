package com.alex.arkanoidprototype.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.concurrent.ThreadLocalRandom;

public class Level {

    private Block[][] assembly;
    private int levelNumber;
    private static int LEVEL_WIDTH = 7;
    private static int LEVEL_HEIGHT = 4;
    private static int BLOCK_WIDTH = 150;
    private static int BLOCK_HEIGHT = 50;

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        assembly = new Block[LEVEL_WIDTH][LEVEL_HEIGHT];

        constructRandomLevel();

    }

    private void constructRandomLevel(){

        Point point = new Point();

        for (int y=0;y<LEVEL_HEIGHT;y++){
            for (int x=0;x<LEVEL_WIDTH;x++){
                point.set((x*BLOCK_WIDTH)+75,(y*BLOCK_HEIGHT)+200);
                int r = ThreadLocalRandom.current().nextInt(0, 254 + 1);
                int g = ThreadLocalRandom.current().nextInt(0, 254 + 1);
                int b = ThreadLocalRandom.current().nextInt(0, 254 + 1);

                int color = Color.rgb(r,g,b);

                assembly[x][y] = new Block(new Rect(0,0,BLOCK_WIDTH,BLOCK_HEIGHT),color);
                assembly[x][y].update(point);
            }
        }
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        for (int y=0;y<LEVEL_HEIGHT;y++){
            for (int x=0;x<LEVEL_WIDTH;x++){
                assembly[x][y].draw(canvas);
            }
        }
    }
}
