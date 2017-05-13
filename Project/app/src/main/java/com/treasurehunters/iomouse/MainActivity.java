package com.treasurehunters.iomouse;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("ABC", "ABC");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        Log.d("", Integer.toString(x));
        Log.d("", Integer.toString(y) + "\n");

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
        }
        return false;
    }

    private SensorManager mSensorManager;

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener((SensorEventListener) this);
    }

    public void GonClick(View v) {
        Button button = (Button) v;
        string strTest = "Test";

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor gyro = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if (gyro != null){
            // Success! There's a gyroscope sensor.
            Log.d("", Integer.toString(gyro));
        }
        else {
            // Failure! No gyroscope sensor.
        }

        ((Button) v).setText((string) strTest);



    }
}
