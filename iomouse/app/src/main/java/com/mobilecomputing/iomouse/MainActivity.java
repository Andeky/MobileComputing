package com.mobilecomputing.iomouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private AppCompatActivity mainActivity;
    private Button touchPadButton;
    private Button acceleratorButton;
    private Button gyroscopeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        initialiseButtons();
    }

    private void initialiseButtons(){

        touchPadButton = (Button) findViewById(R.id.TouchpadButton);
        touchPadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(mainActivity, TouchPadActivity.class);
                startActivity(intent);
                // Perform action on click
            }
        });

        acceleratorButton = (Button) findViewById(R.id.AcceleratorButton);
        acceleratorButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(mainActivity, AccelerometerActivity.class);
                startActivity(intent);
                // Perform action on click
            }
        });

        gyroscopeButton = (Button) findViewById(R.id.GyroscopeButton);
        gyroscopeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(mainActivity, GyroscopeActivity.class);
                startActivity(intent);
                // Perform action on click
            }
        });
    }


}
