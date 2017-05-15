package com.mobilecomputing.iomouse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

public class TouchPadActivity extends AppCompatActivity {

    private int xPrevious;
    private int yPrevious;
    private long timeStamp;

    private float sensitivity; // should be a value in shared preferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_pad);
        xPrevious = 0;
        yPrevious = 0;
        timeStamp = System.currentTimeMillis();
        sensitivity = 1.5f;
    }

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

        //Log.d("TAG", Long.toString(dt));
        if(dt > 50){

            // do not move mouse , implies user have lifted the finger
        }
        else{
            dx = scale(dx);
            dy = scale(dy);
            
            MouseCommands.moveMouse(dx, dy);
        }

        return false;
    }



    private int scale(int i){

        return Math.round(i*sensitivity);

    }


}
