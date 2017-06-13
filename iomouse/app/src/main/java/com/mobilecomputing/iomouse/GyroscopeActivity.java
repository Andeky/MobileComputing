package com.mobilecomputing.iomouse;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GyroscopeActivity extends AppCompatActivity implements SensorEventListener {


    private SensorManager sensorManager;
    private Handler handler;
    private float[] mGravity;
    private float[] mGeomagnetic;
    private Button calibrateButton;

    private float azimut = 0;
    private float pitch = 0;
    private float roll = 0;

    private float azimutCalibrate = 0;
    private float pitchCalibrate = 0;
    private float rollCalibrate = 0;

    private float azimutBound = 0.8f;
    private float pitchBound = 0.8f;

    private float xOld = 0;
    private float yOld = 0;
    private float smoothValue = 0.05f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
        handler = new Handler();

        mGravity = null;
        mGeomagnetic = null;


        calibrateButton = (Button) findViewById(R.id.CalibrateButton);
        calibrateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                azimutCalibrate = azimut;
                pitchCalibrate = pitch;
                rollCalibrate = roll;

                xOld = 50f;
                yOld = 50f;
            }
        });
    }

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
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                azimut = orientation[0]; // orientation contains: azimut, pitch and roll
                pitch = orientation[1];
                roll = orientation[2];

                //Log.d("ABC", Float.toString(azimut) + "      " + Float.toString(pitch)  + "       " + Float.toString(roll));

                float x = ((azimut - azimutCalibrate) + 0.5f) * 100;
                float y = ((pitch - pitchCalibrate) + 0.5f) * 100;

/*
                float xMap = xOld + (x - xOld) * smoothValue; // smoothing it out
                float yMap = yOld + (y - yOld) * smoothValue; // smoothing it out

                xOld = xMap;
                yOld = yMap;
*/

                MouseCommands.placeMouseByPercentage(x, y);

                mGeomagnetic = null;
                mGravity = null;
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}