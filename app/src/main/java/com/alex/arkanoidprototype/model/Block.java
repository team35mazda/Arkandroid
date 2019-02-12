package com.alex.arkanoidprototype.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;


public class Block implements GameObject {

    private Rect rectangle;
    private int color;

    public Block(Rect rectangle, int color){
        this.rectangle = rectangle;
        this.color = color;
    }

    public boolean ballHit(Ball ball){
/*        if (rectangle.contains((int)ball.getOval().left, (int)ball.getOval().top)
                || rectangle.contains((int)ball.getOval().right,(int)ball.getOval().top)
                || rectangle.contains((int)ball.getOval().left, (int)ball.getOval().bottom)
                || rectangle.contains((int)ball.getOval().right,(int)ball.getOval().bottom))
                return true;
*/
        return false;

    }

    @Override
    public void draw(Canvas canvas){
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

    @Override
    public void update(){

    }

    public void update(Point point){

        //rectangle.set(point.x - rectangle.width()/2, 1200, point.x + rectangle.width()/2, 1250);
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);
    }
}
