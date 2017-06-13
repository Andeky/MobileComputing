package com.mobilecomputing.iomouse;

import android.util.Log;

/**
 * Created by Jacob on 13/05/2017.
 */

public class MouseCommands {

    private static final String appTag = "IO_MOUSE_TAG";
    private static final String moveMouse = "moveMouse ";
    private static final String leftClick = "leftClick ";
    private static final String rightClick = "rightClick ";
    private static final String middleClick = "middleClick ";
    private static final String scroll = "scroll ";
    private static final String leftHold = "leftHold ";
    private static final String leftRelease = "leftRelease ";
    private static final String placeMouseByPercentage = "placeMouseByPercentage ";
    private static final String placeMouseByPixel = "placeMouseByPixel ";

    public static void moveMouse(int dx, int dy){
        Log.d(appTag, moveMouse + Integer.toString(dx) + " " + Integer.toString(dy));
    }

    public static void leftClick() {
        Log.d(appTag, leftClick);
    }

    public static void rightClick(){
        Log.d(appTag, rightClick);
    }

    public static void middleClick(){
        Log.d(appTag, middleClick);
    }

    public static void scroll(int scrollValue){
        Log.d(appTag, scroll + Integer.toString(scrollValue));
    }

    public static void placeMouseByPercentage(float x, float y){

        double xDouble = x;
        double yDouble = y;
        placeMouseByPercentage(xDouble, yDouble);
    }

    public static void placeMouseByPercentage(double x, double y){

        if(x > 100){
            x = 100;
        }
        if(x < 0){
            x = 0;
        }
        if(y > 100){
            y = 100;
        }
        if(y < 0){
            y = 0;
        }


        Log.d(appTag, placeMouseByPercentage + Double.toString(x) + " " + Double.toString(y));
    }

    public static void placeMouseByPixel(){
        Log.d(appTag, placeMouseByPixel);
    }

    public static void leftClickHold(){
        Log.d(appTag, leftHold);
    }

    public static void leftClickRelease(){
        Log.d(appTag, leftRelease);
    }



    private static String timeStamp(){
        return Long.toString(System.currentTimeMillis()) + " ";
    }
}