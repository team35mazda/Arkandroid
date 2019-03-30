package com.alex.arkanoidprototype.controler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.alex.arkanoidprototype.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


    }



    protected void onPause() {
        super.onPause();

        ((GamePanel) findViewById(R.id.game_panel)).getControlListener().unregisterListener();
    }
    protected void onResume() {
        super.onResume();
        ((GamePanel) findViewById(R.id.game_panel)).getControlListener().registerListener();
    }

}