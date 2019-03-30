package com.alex.arkanoidprototype.controler;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class ControlListener implements SensorEventListener {

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private Context context;
    private GamePanel gamepanel;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private  int tourner = 0;  // -1: gauche, 0: statique, 1: droite

    public ControlListener(Context context, GamePanel gamePanel){
        this.context = context;
        this.gamepanel = gamePanel;
        senSensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        boolean portrait = (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT);
        Sensor mySensor = event.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                //float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;//PAS UTILISÉ?!?!

                if (portrait){
                    if (x > 0) {
                        tourner = -1;
                    } else {
                        if (x < 0) {
                            tourner = 1;
                        } else {
                            tourner = 0;
                        }
                    }
                    last_x = x;
                    last_y = y;
                    last_z = z;
                } else {
                    if (y > 0) {
                        tourner = 1;
                    } else {
                        if (y < 0) {
                            tourner = -1;
                        } else {
                            tourner = 0;
                        }

                        last_x = y;
                        last_y = x;
                        last_z = z;
                    }
                }
            }
        }

        this.gamepanel.sliderPointUpdate(tourner);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void unregisterListener(){
        senSensorManager.unregisterListener(this);
    }

    public void registerListener(){
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public int get_tourner() {
        return tourner;
    }
}
