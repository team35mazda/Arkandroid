package com.alex.arkanoidprototype.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;



public class Block implements GameObject {

    private Rect rectangle;
    private int color;
    private boolean visible = true;

    public Block(Rect rectangle, int color){
        this.rectangle = rectangle;
        this.color = color;
    }

    public Rect getRect(){
        return this.rectangle;
    }

    public BlockHit ballHit(Point ballCoord, boolean directionX, boolean directionY, int MouvementX, int MouvementY, int rayon){
        BlockHit bhit = new BlockHit();

        if (!visible) return bhit;

        //double UnTierRayon = Ball.rayon*1/3;

        // Je ne peux pas utiliser Contains, j'ai besoin de savoir comment le block a été touché (côté ou haut/bas)
/*        if (rectangle.contains(ballCoord.left, ballCoord.top)
                || rectangle.contains(ballCoord.right,ballCoord.top)
                || rectangle.contains(ballCoord.left, ballCoord.bottom)
                || rectangle.contains(ballCoord.right,ballCoord.bottom))
                return true;*/

                //

        if ((!bhit.getHit()) && (ballCoord.x-rayon >= rectangle.left) && (ballCoord.x+rayon <= rectangle.right)) {
            if ((ballCoord.y - rayon <= rectangle.bottom) && (ballCoord.y - MouvementY > rectangle.bottom)) {
                bhit.setHitByY(true);
                bhit.HitByBottom = true;
            }
            if ((ballCoord.y + rayon >= rectangle.top) && (ballCoord.y + MouvementY < rectangle.top)) {
                bhit.setHitByY(true);
                bhit.HitByTop = true;
            }
        }
        if ((!bhit.getHit()) && (ballCoord.y-rayon >= rectangle.top) && (ballCoord.y+rayon <= rectangle.bottom)){
            if ((ballCoord.x + rayon >= rectangle.left) && (ballCoord.x + MouvementX < rectangle.left)) {
                bhit.setHitByX(true);
                bhit.HitByLeft = true;
            }
            if ((ballCoord.x - rayon <= rectangle.right) && (ballCoord.x - MouvementX > rectangle.right)) {
                bhit.setHitByX(true);
                bhit.HitByRigth= true;
            }
        }

/*
        else {
                    //&& (ballCoord.bottom >= rectangle.bottom)
                if ((ballCoord.top <= rectangle.bottom) ) {
                    bhit.setHitByX(true);
                    bhit.HitByBottom = true;
                }
            }
        }
*/
/*
        //on poursuit seulement si on a pas déjà fait un hit
        if (!bhit.getHit()) {
                //
            if ((ballCoord.top >= rectangle.bottom) && (ballCoord.bottom <= rectangle.top)) {
                    // && (ballCoord.right-UnTierRayon <= rectangle.left)
                if ((ballCoord.right >= rectangle.left)) {
                    bhit.setHitByY(true);
                    bhit.HitByLeft = true;
                } else {
                        //  && (ballCoord.right >= rectangle.right)
                    if ((ballCoord.left <= rectangle.right)) {
                        bhit.setHitByY(true);
                        bhit.HitByRigth= true;
                    }
                }
            }
        }*/

        if (bhit.getHit()) this.visible = false;
        return bhit;
    }

    @Override
    public void draw(Canvas canvas){

        if (visible){

            Paint paint = new Paint();
            //paint.setColor(color);
            //canvas.drawRect(rectangle,paint);

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(color);
            canvas.drawRect(rectangle, paint);

            // border
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            canvas.drawRect(rectangle, paint);
        }
    }

    @Override
    public void update(){

    }

    public void update(Point point){

        //rectangle.set(point.x - rectangle.width()/2, 1200, point.x + rectangle.width()/2, 1250);
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);
    }
}
