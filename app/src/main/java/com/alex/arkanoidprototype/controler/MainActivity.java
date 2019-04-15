package com.alex.arkanoidprototype.controler;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.alex.arkanoidprototype.R;

public class MainActivity extends AppCompatActivity {

    private DatabaseController databaseController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseController = new DatabaseController();
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((GamePanel) findViewById(R.id.game_panel)).GamePanelRunningState(false);
        databaseController.saveState((GamePanel) findViewById(R.id.game_panel));
        ((GamePanel) findViewById(R.id.game_panel)).getControlListener().unregisterListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((GamePanel) findViewById(R.id.game_panel)).getControlListener().registerListener();
        ((GamePanel) findViewById(R.id.game_panel)).GamePanelRunningState(true);
    }

}