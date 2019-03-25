package com.alex.arkanoidprototype.controler;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.alex.arkanoidprototype.database.DatabaseManager;
import com.alex.arkanoidprototype.database.datasource.CouleurDataSource;
import com.alex.arkanoidprototype.database.datasource.DimensionBalleDataSource;
import com.alex.arkanoidprototype.database.datasource.DimensionBalleItemDataSource;
import com.alex.arkanoidprototype.database.datasource.DimensionBaseDataSource;
import com.alex.arkanoidprototype.database.datasource.DimensionBaseItemDataSource;
import com.alex.arkanoidprototype.database.datasource.ItemDataSource;
import com.alex.arkanoidprototype.database.datasource.ItemMouvementDataSource;
import com.alex.arkanoidprototype.database.datasource.ItemTypeDataSource;
import com.alex.arkanoidprototype.database.datasource.MouvementDataSource;
import com.alex.arkanoidprototype.database.datasource.NiveauDataSource;
import com.alex.arkanoidprototype.database.datasource.NiveauItemCurrentMouvementDataSource;
import com.alex.arkanoidprototype.database.datasource.NiveauItemCurrentPositionDataSource;
import com.alex.arkanoidprototype.database.datasource.NiveauItemCurrentVisibilityDataSource;
import com.alex.arkanoidprototype.database.datasource.NiveauItemDataSource;
import com.alex.arkanoidprototype.database.datasource.PositionDataSource;
import com.alex.arkanoidprototype.database.datasource.PositionItemDataSource;
import com.alex.arkanoidprototype.model.SensorActivity;

//public class MainActivity extends Activity {
public class MainActivity extends Activity implements SensorEventListener {

    // Declaration des DataSources

    // MOI ###############################################################
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private Sensor mRotationSensor;
    private DatabaseManager databaseManager;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    public static int tourner = 0;  // -1: gauche, 0: statique, 1: droite
    // fin MOI ###########################################################

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // MOI ###############################################################
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
        // fin MOI ###########################################################

        //Créer une instance de BD
        databaseManager = new DatabaseManager(this);

        setContentView(new GamePanel(this));
    }

    // MOI ###############################################################
    @Override
    public void onSensorChanged(SensorEvent event) {
        boolean Orientation = (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT);
        Sensor mySensor = event.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                if (Orientation == true) {
                    if (x == 0) {
                        tourner = 0;
                    } else {
                        if ((x < 0) && (y >= 0)) {
                            tourner = 1;
                        } else {
                            tourner = -1;
                        }
                    }
                    last_x = x;
                    last_y = y;
                    last_z = z;
                } else {
                    if (y == 0) {
                        tourner = 0;
                    } else {
                        if ((y < 0) && (x >= 0)) {
                            tourner = -1;
                        } else {
                            tourner = 1;
                        }

                        last_x = y;
                        last_y = x;
                        last_z = z;
                    }
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
        // Fermer la bd
        databaseManager.close();


    }
    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        //Ouvrir la bd
        databaseManager.open();

    }


    public int get_tourner() {
        return tourner;
    }
    // fin MOI ###########################################################
}