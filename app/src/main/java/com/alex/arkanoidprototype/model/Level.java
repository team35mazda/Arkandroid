package com.alex.arkanoidprototype.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.floor;
import static java.lang.Math.round;
public class Level {

    private Block[][] assembly;
    private int levelNumber;
    private int startPosition = 200;
    private static int def_LEVEL_WIDTH = 10;
    private static int def_LEVEL_HEIGHT = 7;
    private static int def_BLOCK_WIDTH = 150;
    private static int def_BLOCK_HEIGHT = 50;
    private int LEVEL_WIDTH = def_LEVEL_WIDTH;
    private int LEVEL_HEIGHT = def_LEVEL_HEIGHT;
    private int BLOCK_WIDTH = def_BLOCK_WIDTH;
    private int BLOCK_HEIGHT = def_BLOCK_HEIGHT;







    //public Level(int levelNumber) {
    // MOI ###############################################################
    public Level(int levelNumber, Point pointSize) {
    // fin MOI ###########################################################
        this.levelNumber = levelNumber;
        assembly = new Block[LEVEL_WIDTH][LEVEL_HEIGHT];

        //constructRandomLevel();
        // MOI ###############################################################
        constructLevel(levelNumber, pointSize);
        // fin MOI ###########################################################
    }

    // MOI ###############################################################
    private void constructLevel(int levelNumber, Point pointSize){
        Point point = new Point();
        float screenWidth = pointSize.x;
        float screenHeight = pointSize.y;

        int[][][] thisLevel = getLevel(levelNumber);
        int blockWidth = (int)Math.floor(screenWidth)/LEVEL_WIDTH;
        int blockHeight = (int)(Math.floor(screenHeight)*0.15/LEVEL_HEIGHT);

        for (int y=0;y<LEVEL_HEIGHT;y++){
            for (int x=0;x<LEVEL_WIDTH;x++){
                point.set((x*blockWidth)+75,(y*blockHeight)+200);

                int r = thisLevel[x][y][0];
                int g = thisLevel[x][y][1];
                int b = thisLevel[x][y][2];
                int color = Color.rgb(r,g,b);

                assembly[x][y] = new Block(new Rect(0,0,blockWidth,blockHeight),color);
                assembly[x][y].update(point);
            }
        }
    }

    public int[][][] getLevel(int levelNumber){

        switch (levelNumber){
            case 1:
                return getLevel_1();
            case 2:
                return getLevel_2();
            case 3:
                return getLevel_3();
            default:
                return getLevel_1();
        }
    }

    public int[][][] getLevel_1(){
        int [][][] level_1 = new int [LEVEL_WIDTH][LEVEL_HEIGHT][3];
        for (int i = 0; i < LEVEL_WIDTH; i++){
            for (int j = 0; j < LEVEL_HEIGHT; j++){
                if ((i <= LEVEL_WIDTH/2 && j <= LEVEL_HEIGHT/2) ||
                        (i > LEVEL_WIDTH/2 && j > LEVEL_HEIGHT/2)){
                    level_1[i][j][0] = 255;
                    level_1[i][j][1] = 255;
                    level_1[i][j][2] = 0;
                } else {
                    level_1[i][j][0] = 255;
                    level_1[i][j][1] = 0;
                    level_1[i][j][2] = 0;
                }
            }
        }
        return level_1;
    }

    public int[][][] getLevel_2(){
        int [][][] level_2 = new int [LEVEL_WIDTH][LEVEL_HEIGHT][3];
        for (int i = 0; i < LEVEL_WIDTH; i++){
            for (int j = 0; j < LEVEL_HEIGHT; j++){
                if ((j % 2 == 0)) {
                    level_2[i][j][0] = 255;
                    level_2[i][j][1] = 255;
                    level_2[i][j][2] = 0;
                    if (i % 2 == 0) {
                        level_2[i][j][0] = 255;
                        level_2[i][j][1] = 255;
                        level_2[i][j][2] = 0;
                    } else {
                        level_2[i][j][0] = 0;
                        level_2[i][j][1] = 255;
                        level_2[i][j][2] = 0;
                    }
                }
            }
        }
        return level_2;
    }

    public int[][][] getLevel_3(){
        int [][][] level_2 = new int [LEVEL_WIDTH][LEVEL_HEIGHT][3];
        for (int i = 0; i < LEVEL_WIDTH; i++){
            for (int j = 0; j < LEVEL_HEIGHT; j++){
                if (LEVEL_WIDTH >= 10 && LEVEL_HEIGHT >= 5) {
                    if ((i == 0 && j == 0) || (i == 2 && j == 0) || (i == 4 && j == 0) || (i == 5 && j == 0) || (i == 7 && j == 0) ||  (i == 9 && j == 0) ||
                            (i == 0 && j == 1) || (i == 2 && j == 1) || (i == 4 && j == 1) || (i == 7 && j == 1) ||  (i == 9 && j == 1) ||
                            (i == 0 && j == 2) || (i == 1 && j == 2) || (i == 2 && j == 2) || (i == 4 && j == 2) || (i == 5 && j == 2) || (i == 8 && j == 2) ||
                            (i == 0 && j == 3) || (i == 2 && j == 3) || (i == 4 && j == 3) || (i == 8&& j == 3) ||
                            (i == 0 && j == 4) || (i == 2 && j == 4) || (i == 4 && j == 4) || (i == 5 && j == 4) || (i == 8 && j == 4)
                    ) {
                        level_2[i][j][0] = 0;
                        level_2[i][j][1] = 0;
                        level_2[i][j][2] = 255;
                    }
                } else {
                    level_2[i][j][0] = 0;
                    level_2[i][j][1] = 0;
                    level_2[i][j][2] = 255;
                }
            }
        }
        return level_2;
    }




























    private void constructRandomLevel(){

        Point point = new Point();

        for (int y=0;y<LEVEL_HEIGHT;y++){
            for (int x=0;x<LEVEL_WIDTH;x++){
                point.set((x*BLOCK_WIDTH)+75,(y*BLOCK_HEIGHT)+startPosition);
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

        //d�call� la construction du tableau jusqu'� ce qu'on ait la taille du canvas... donc ici..
        //Change la largeur des blocks pour afficher X block de large (� d�finir) et qui prend le maximum de place � l'�cran
        if (assembly == null){
            this.BLOCK_WIDTH = round(canvas.getWidth()/LEVEL_WIDTH) + 1; //Arrondi par le bas pour �tre plus petit que l'�cran sans d�border
            this.BLOCK_HEIGHT = round(this.BLOCK_WIDTH/3);  // hauteur = arrondi � 1/3 de la largeur
            assembly = new Block[this.LEVEL_WIDTH][LEVEL_HEIGHT];

            this.startPosition = (int)round(canvas.getHeight()*0.1); // ON arriche � 10% de la hauteur
            constructRandomLevel();
        }

        Paint paint = new Paint();
        for (int y=0;y<LEVEL_HEIGHT;y++){
            for (int x=0;x<LEVEL_WIDTH;x++){
                assembly[x][y].draw(canvas);
            }
        }
    }

    public BlockHit DetectBlockHit(BlockHit bhit, Point cercle, boolean directionX, boolean directionY, int MouvementX, int MouvementY, int rayon){
        boolean hit = false;

        search:
        {
            for (int y = 0; y < LEVEL_HEIGHT; y++) {
                for (int x = 0; x < LEVEL_WIDTH; x++) {
                    assembly[x][y].ballHit(bhit, cercle, directionX, directionY, MouvementX, MouvementY, rayon);
                    if (bhit.getHit()) {
                        Rect r = assembly[x][y].getRect();
                        Log.v("Arkanoid - Level", "Block[" + String.valueOf(x) + "][" + String.valueOf(y) + "] (" + String.valueOf(r.left) + "," + String.valueOf(r.top) + "," + String.valueOf(r.right) + "," + String.valueOf(r.bottom) + ")  |   Ball (" + String.valueOf(cercle.x) + "," + String.valueOf(cercle.y) + ")  |   Hit by {" + String.valueOf(bhit.getHitByLeft()) + " " + String.valueOf(bhit.getHitByTop())  + " " + String.valueOf(bhit.getHitByRigth()) + " " + String.valueOf(bhit.getHitByBottom()) + "}");
                        hit = true;
                        break search;
                    }
                }
            }
        }

        return bhit;
    }
}
