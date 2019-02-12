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
import com.alex.arkanoidprototype.model.Ball;

import static java.lang.Math.round;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    private Slider slider;
    private Point sliderPoint;
    private Level level;
    private Ball ball;
    private Point ballPoint;

    private static int SliderStartPosX = 500;
    private static int SliderStartPosY = 1200;

    public GamePanel(Context context){
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        slider = new Slider(new Rect(50,50,200,100), Color.rgb(255,0,0));
        sliderPoint = new Point(SliderStartPosX,SliderStartPosY);

        level = new Level(1);
        int sliderCenter;
        sliderCenter = slider.getRect().right - slider.getRect().left; //Size du slider
        sliderCenter = round(sliderCenter / 2) - Ball.rayon;

        ballPoint = new Point(sliderCenter, SliderStartPosY-Ball.rayon);
        ball = new Ball(ballPoint, Color.rgb(102,135,255)); //

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

                // ballPoint.set((int)event.getX(),(int)event.getY());  // pour débug, je positionne la balle au touchpoint...
        }
        //return super.onTouchEvent(event);
        return true;
    }

    public void update(){
        slider.update(sliderPoint);
        ball.update(ballPoint);
    }

    private void detect_collision(Canvas canvas){
        Point ballpoint;
        ballpoint = ball.getcercle();

        if (ball.getdirectionX()) {
            if (ballpoint.x<ball.getrayon()) ball.setdirectionX(false);
        }
        else{    if (ballpoint.x+ball.getrayon() > canvas.getWidth()) ball.setdirectionX(true);
        }

        if (ball.getdirectionY()) {
            if (ballpoint.y<ball.getrayon()) ball.setdirectionY(false);
        }
        else{
            if (ballpoint.y+ball.getrayon() >= slider.getRect().top){
                if (ballpoint.x>=slider.getRect().left && ballpoint.x<=slider.getRect().right){
                    ball.setdirectionY(true);
                }else {if (ballpoint.y + ball.getrayon() > canvas.getHeight()) ball.setdirectionY(true);
                }
            }
        }

    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);

        canvas.drawColor(Color.WHITE);

        slider.draw(canvas);
        level.draw(canvas);
        ball.draw(canvas);

        detect_collision(canvas);
    }

}
