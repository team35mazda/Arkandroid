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

    public void ballHit(BlockHit bhit, Point ballCoord, boolean directionX, boolean directionY, int MouvementX, int MouvementY, int rayon){

        //Todo: pour une meilleure détection, il faudrait calculer tous les points de la circonférences et valider par rapport aux coordonnée du rectangle

        DetectHIT:
        {
            //Si le block est déja tappé... donc invisible.. on ne cherche pas s'il y a un hit
            if (!visible) break DetectHIT;//return bhit;

            if ((ballCoord.x + rayon/2 >= rectangle.left) && (ballCoord.x - rayon/2 <= rectangle.right)) {
                if ((ballCoord.y - rayon <= rectangle.bottom) && (ballCoord.y > rectangle.bottom)) {
                    bhit.setHitByBottom(true);
                }
                else {
                    if ((ballCoord.y + rayon >= rectangle.top) && (ballCoord.y  < rectangle.top)) {
                        bhit.setHitByTop(true);
                    }
                }
            }
            else{
                if ((ballCoord.y + rayon/2 >= rectangle.top) && (ballCoord.y - rayon/2 <= rectangle.bottom)) {
                    if ((ballCoord.x + rayon >= rectangle.left) && (ballCoord.x < rectangle.left)) {
                        bhit.setHitByLeft(true);
                    }
                    else {
                        if ((ballCoord.x - rayon <= rectangle.right) && (ballCoord.x > rectangle.right)) {
                            bhit.setHitByRigth(true);
                        }
                    }
                }
            }

            if (bhit.getHit()) this.visible = false;
        }
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

    public boolean isVisible() {
        return visible;
    }

    public void update(Point point){

        //rectangle.set(point.x - rectangle.width()/2, 1200, point.x + rectangle.width()/2, 1250);
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);
    }
}
