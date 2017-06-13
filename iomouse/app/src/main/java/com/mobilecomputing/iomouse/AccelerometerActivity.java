package com.mobilecomputing.iomouse;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

/**
 * Created by avj on 21-05-2017.
 */

public class AccelerometerActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private double dx, dy = 0;

    private double ax,ay; // these are the acceleration in x,y and z axis
    private boolean xMoving = false;
    private boolean yMoving = false;
    private double axCalibrate, ayCalibrate;
    private int counter = 0;
    private Handler handler;
    private MouseMover mouseMover;

    private float[] mGravity;
    private float[] mGeomagnetic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        sensorManager =(SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
        handler = new Handler();
        mouseMover = new MouseMover();
        mouseMover.run();
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            mGravity = event.values;
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            mGeomagnetic = event.values;
        if (mGravity != null && mGeomagnetic != null) {

            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success) {

                if(counter < 10){
                    axCalibrate += mGravity[0];
                    ayCalibrate += mGravity[1];
                    counter++;
                    return;
                }
                else if(counter == 10){
                    axCalibrate = axCalibrate / 10;
                    ayCalibrate = ayCalibrate / 10;
                    counter++;
                    Log.d("Accelerometer", "Done calibrating");
                }

                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);

                ax = mGravity[0] - axCalibrate;
                ay = mGravity[1] - ayCalibrate;

                if(Math.abs(ax) < 0.05){
                    // no longer moving along x-axis
                    xMoving = false;
                    dx = 0;
                }

                if(ax > 0.1 && !xMoving){
                    // user moved the mouse to the right
                    xMoving = true;
                    dx = ax * 5;
                }

                if(ax < -0.1 && !xMoving){
                    // user moved the mouse to the left
                    xMoving = true;
                    dx = ax * 5;
                }

                if(Math.abs(ay) < 0.02){
                    yMoving = false;
                    dy = 0;
                }

                if(ay > 0.1 && !yMoving){
                    yMoving = true;
                    dy = -ay * 5;
                }

                if(ay < -0.1 && !yMoving){
                    yMoving = true;
                    dy = -ay * 5;
                }

                mGeomagnetic = null;
                mGravity = null;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.post(mouseMover);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(mouseMover);
    }

    private class MouseMover implements Runnable{

        @Override
        public void run(){

            if(xMoving || yMoving){
                MouseCommands.moveMouse( (int) Math.round(dx), (int) Math.round(dy));
            }
            handler.postDelayed(this, 25);
        }
    }
}
