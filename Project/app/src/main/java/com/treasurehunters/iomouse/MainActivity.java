package com.treasurehunters.iomouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("ABC", "ABC");
    }

    @Override
    public boolean onTouchEvent(MotionEvent   event) {
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
}
