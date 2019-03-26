package com.alex.arkanoidprototype.controler;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;

import com.alex.arkanoidprototype.model.SensorActivity;

//public class MainActivity extends Activity {
public class MainActivity extends Activity {


    GamePanel gamePanel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gamePanel = new GamePanel(this);

        setContentView(gamePanel);
    }



    protected void onPause() {
        super.onPause();
        gamePanel.getControlListener().unregisterListener();
    }
    protected void onResume() {
        super.onResume();
        gamePanel.getControlListener().registerListener();
    }

}