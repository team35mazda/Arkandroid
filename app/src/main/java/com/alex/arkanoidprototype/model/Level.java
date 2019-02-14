package com.alex.arkanoidprototype.model;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.floor;


public class Level {

    private Block[][] assembly;
    private int levelNumber;
    private static int def_LEVEL_WIDTH = 15;
    private static int def_LEVEL_HEIGHT = 8;
    private static int def_BLOCK_WIDTH = 150;
    private static int def_BLOCK_HEIGHT = 50;
    private int LEVEL_WIDTH = def_LEVEL_WIDTH;
    private int LEVEL_HEIGHT = def_LEVEL_HEIGHT;
    private int BLOCK_WIDTH = def_BLOCK_WIDTH;
    private int BLOCK_HEIGHT = def_BLOCK_HEIGHT;

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;

        ////décallé la construction du tableau jusqu'à ce qu'on ait la taille du canvas... donc dans le 1ier draw..
        //constructRandomLevel();
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

        //décallé la construction du tableau jusqu'à ce qu'on ait la taille du canvas... donc ici..
        //Change la largeur des blocks pour afficher X block de large (à définir) et qui prend le maximum de place à l'écran
        if (assembly == null){
            this.BLOCK_WIDTH = (int)floor(canvas.getWidth()/LEVEL_WIDTH); //Arrondi par le bas pour être plus petit que l'écran sans déborder
            assembly = new Block[this.LEVEL_WIDTH][LEVEL_HEIGHT];
            constructRandomLevel();
        }

        Paint paint = new Paint();
        for (int y=0;y<LEVEL_HEIGHT;y++){
            for (int x=0;x<LEVEL_WIDTH;x++){
                assembly[x][y].draw(canvas);
            }
        }
    }

    public BlockHit DetectBlockHit(Point cercle, boolean directionX, boolean directionY, int MouvementX, int MouvementY, int rayon){
        BlockHit bhit = new BlockHit();

        search:
        {
            for (int y = 0; y < LEVEL_HEIGHT; y++) {
                for (int x = 0; x < LEVEL_WIDTH; x++) {
                    bhit = assembly[x][y].ballHit(cercle, directionX, directionY, MouvementX, MouvementY, rayon);
                    if (bhit.getHit()) {
                        Rect r = assembly[x][y].getRect();
                        Log.v("Michel", "Block[" + String.valueOf(x) + "][" + String.valueOf(y) + "] (" + String.valueOf(r.left) + "," + String.valueOf(r.top) + "," + String.valueOf(r.right) + "," + String.valueOf(r.bottom) + ")  |   Ball (" + String.valueOf(cercle.x) + "," + String.valueOf(cercle.y) + ")  |   Hit by {" + String.valueOf(bhit.HitByLeft) + " " + String.valueOf(bhit.HitByTop)  + " " + String.valueOf(bhit.HitByRigth) + " " + String.valueOf(bhit.HitByBottom) + "}");
                        break search;
                    }
                }
            }
        }

        return bhit;
    }
}
