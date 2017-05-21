package com.mobilecomputing.iomouse;

import android.util.Log;

/**
 * Created by Jacob on 13/05/2017.
 */

public class MouseCommands {

    private static final String appTag = "IO_MOUSE_TAG";
    private static final String moveMouse = "moveMouse ";
    private static final String leftClick = "leftClick ";
    private static final String rightClick = "rightClick";
    private static final String thirdButton = "thirdButton";
    private static final String scroll = "scroll ";
    private static final String leftClickHold = "leftClickHold";
    private static final String leftClickRelease = "leftClickRelease";


    public static void moveMouse(int dx, int dy){
        Log.d(appTag, moveMouse + Integer.toString(dx) + " " + Integer.toString(dy));
    }

    public static void leftClick() {
        Log.d(appTag, leftClick);
    }

    public static void rightClick(){
        Log.d(appTag, rightClick);
    }

    public static void thirdBurron(){
        Log.d(appTag, thirdButton);
    }

    public static void scroll(int scrollValue){
        Log.d(appTag, scroll + Integer.toString(scrollValue));
    }

    public static void leftClickHold(){
        Log.d(appTag, leftClickHold);
    }

    public static void setLeftClickRelease(){
        Log.d(appTag, leftClickRelease);
    }

    private static String timeStamp(){
        return Long.toString(System.currentTimeMillis()) + " ";
    }
}

