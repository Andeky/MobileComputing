package com.mobilecomputing.iomouse;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class TouchPadActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    private String DEBUG_TAG     = "ABC";
    private int xPrevious;
    private int yPrevious;
    private long timeStamp; // updated time stamp whenever activity is touched
    private float sensitivity; // should be a value in shared preferences

    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_pad);
        xPrevious = 0;
        yPrevious = 0;
        timeStamp = System.currentTimeMillis();
        sensitivity = 1.2f;

        mDetector = new GestureDetectorCompat(this,this);
        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this);


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

/*
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
*/


        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }



    private int scale(int i){

        return Math.round(i*sensitivity);

    }


    @Override
    public boolean onDown(MotionEvent event) {
        // on touch
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        // when touch ends
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        // on movement (not two finger scroll)
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d("ABC", "onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        // Log.d("ABC", "onSingleTapUp");;
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d("ABC", "onDoubleTab");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d("ABC", "onDoubleTabEvent");
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        // Log.d("ABC", "onSingleTapConfirmed");
        return true;
    }
}
