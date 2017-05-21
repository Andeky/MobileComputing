package com.mobilecomputing.iomouse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

public class TouchPadActivity extends AppCompatActivity {

    private int xPrevious;
    private int yPrevious;
    private long timeStamp; // updated time stamp whenever activity is touched

    private float sensitivity; // should be a value in shared preferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_pad);
        xPrevious = 0;
        yPrevious = 0;
        timeStamp = System.currentTimeMillis();
        sensitivity = 1.2f;
    }

    // called whenever the screen is touched within the activity
    @Override
    public boolean onTouchEvent(MotionEvent event) {


        int x = (int) event.getX();
        int y = (int) event.getY();

        Long dt = System.currentTimeMillis() - timeStamp;
        timeStamp = System.currentTimeMillis();

        int dx = x - xPrevious;
        int dy = y - yPrevious;

        xPrevious = x;
        yPrevious = y;

        if(dt > 50){
            // do not move mouse , implies user have lifted the finger
        }
        else{
            // move mouse
            dx = scale(dx);
            dy = scale(dy);

            MouseCommands.moveMouse(dx, dy);
        }

        switch(event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                // The user just touched the screen
                return false;

            case MotionEvent.ACTION_POINTER_UP:
                // Multi touch detected

                Log.d("ABC", "Multitouch detected");
                return false;

            case MotionEvent.ACTION_UP:
                // The touch just ended

                long eventDuration = event.getEventTime() - event.getDownTime();

                if(eventDuration < 100){
                    MouseCommands.leftClick();
                }

                return false;
        }


        return false;
    }

    private int scale(int i){

        return Math.round(i*sensitivity);

    }
}
