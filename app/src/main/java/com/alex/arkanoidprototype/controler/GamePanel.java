package com.alex.arkanoidprototype.controler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.alex.arkanoidprototype.model.Level;
import com.alex.arkanoidprototype.model.Slider;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    private Slider slider;
    private Point sliderPoint;
    private Level level;

    public GamePanel(Context context){
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

         slider = new Slider(new Rect(50,50,200,100), Color.rgb(255,0,0));
         sliderPoint = new Point(500,1200);

         level = new Level(1);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;

        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
                retry = false;
            }catch(Exception e){e.printStackTrace();}
         }
    }

    @Override
    public boolean onTouchEvent (MotionEvent event){

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                sliderPoint.set((int)event.getX(),(int)event.getY());
        }
        //return super.onTouchEvent(event);
        return true;
    }

    public void update(){
        slider.update(sliderPoint);
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);

        canvas.drawColor(Color.WHITE);

        slider.draw(canvas);
        level.draw(canvas);
    }

}
