package com.mobilecomputing.iomouse;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by avj on 21-05-2017.
 */

public class GyroAndAccelerometerActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    double xPrevious = 0.0;
    double yPrevious = 0.0;
    double zPrevious = 0.0;
    double ax,ay,az; // these are the acceleration in x,y and z axis
    private long timeStamp; // updated time stamp whenever activity is touched

    private float sensitivity; // should be a value in shared preferences

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyro_accelerometer);
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
            ax = event.values[0];
            ay = event.values[1];
            az = event.values[2];
            Log.d("IARRIVED", "I CRASHED HERE?`!");


            timeStamp = System.currentTimeMillis();
            sensitivity = 1.2f;

            Long dt = System.currentTimeMillis() - timeStamp;
            timeStamp = System.currentTimeMillis();

            int dx = (int) Math.round(ax - xPrevious);
            int dy = (int) Math.round(ay - yPrevious);

            xPrevious = ax;
            yPrevious = ay;

            if (dt > 50) {
                // do not move mouse , implies user have lifted the finger
            } else {
                // move mouse
                dx = scale(dx);
                dy = scale(dy);

                MouseCommands.moveMouse(dx, dy);
            }
        }
    }

    private int scale(int i){

        return Math.round(i*sensitivity);

    }
}
