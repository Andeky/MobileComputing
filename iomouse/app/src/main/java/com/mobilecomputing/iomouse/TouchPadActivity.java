package com.mobilecomputing.iomouse;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class TouchPadActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    private boolean screenCurrentlyTouched = false;
    private int xPrevious;
    private int yPrevious;
    private long timeStamp; // updated time stamp whenever activity is touched
    private long multitouchTimeStamp;
    private long multitouchDuration;
    private float sensitivity; // should be a value in shared preferences

    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_pad);
        xPrevious = 0;
        yPrevious = 0;
        timeStamp = System.currentTimeMillis();
        multitouchTimeStamp = 0;
        multitouchDuration = 0;
        sensitivity = 1.2f;

        mDetector = new GestureDetectorCompat(this,this);
        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this);

    }

    // called whenever the screen is touched within the activity
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getPointerCount() != 2){

            int x = (int) event.getX();
            int y = (int) event.getY();
            if (multitouchDuration > 0 && multitouchDuration < 100){
                MouseCommands.rightClick();
                multitouchTimeStamp = System.currentTimeMillis();
            }
            multitouchDuration = 0;
        }

        if (timeStamp - multitouchTimeStamp < 50){
            timeStamp = System.currentTimeMillis();
            return false;
        }

        // one finger
        if(event.getPointerCount() == 1){
            return handleSingleTouch(event);
        }

        // two fingers
        if(event.getPointerCount() == 2){
            return handleDualTouch(event);
        }

        return false;
    }

    // is called when one finger is touching the screen
    private boolean handleSingleTouch(MotionEvent event){

        Long dt = System.currentTimeMillis() - timeStamp;
        timeStamp = System.currentTimeMillis();

        int x = (int) event.getX();
        int y = (int) event.getY();
        int dx = x - xPrevious;
        int dy = y - yPrevious;
        xPrevious = x;
        yPrevious = y;

        if(dt < 50){
            // move mouse
            dx = scale(dx);
            dy = scale(dy);

            //MouseCommands.moveMouse(dx, dy);
            if(dx == 0 && dy == 0 || Math.abs(dx) > 100 || Math.abs(dy) > 100){
                // do not print if the movement is 0
                // if dx or dy is bigger than 0 it implies faulty multitouch
            }
            else{
                MouseCommands.moveMouse(dx, dy);
            }
        }

        switch(event.getAction()) {

            case MotionEvent.ACTION_UP:
                // The touch just ended
                screenCurrentlyTouched = false;
        }
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private boolean handleDualTouch(MotionEvent event){

        Log.d("ABC", "Multitouch detected");
        Long dt = System.currentTimeMillis() - timeStamp;
        timeStamp = System.currentTimeMillis();
        multitouchDuration += dt;
        return false;
    }

    private int scale(int i){

        return Math.round(i*sensitivity);
    }

    private class DoublePressListener implements Runnable{

        private long timeStampCreatedDPL;
        private boolean pressDownNotified;

        private DoublePressListener(){
            timeStampCreatedDPL = System.currentTimeMillis();
            pressDownNotified = false;
            screenCurrentlyTouched = true;
        }


        @Override
        public void run(){

            Handler handler = new Handler();
            long timeStampDPL = System.currentTimeMillis();

            if(screenCurrentlyTouched == false){

                if(timeStampDPL - timeStampCreatedDPL < 100){
                    // valid double tap
                    MouseCommands.leftClick();
                    MouseCommands.leftClick();
                }
                else{
                    // release press down
                    MouseCommands.leftClickRelease();
                }
                // stop listener
                return;
            }

            if(timeStampDPL - timeStampCreatedDPL  >= 100 && !pressDownNotified){

                MouseCommands.leftClickHold();
                pressDownNotified = true;
            }

            handler.postDelayed(this, 50);
        }
    }

    // --------- On double tap listener overwrites ---------

    @Override
    public boolean onDown(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d("ABC", "onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        // called on single tab, also if it might lead to a double tab
        // Log.d("ABC", "onSingleTapUp");;
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d("ABC", "onDoubleTab");
        // called once on double tab
        // Log.d("ABC", "Double tab");
        new DoublePressListener().run();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d("ABC", "onDoubleTabEvent");
        // called multiple times on double tab (??)
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        // Log.d("ABC", "onSingleTapConfirmed");
        // called on single tab, and not on double tabs (use this)
        MouseCommands.leftClick();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        Log.e("", "Longpress detected");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float x, float y){
        return false;
    }
}