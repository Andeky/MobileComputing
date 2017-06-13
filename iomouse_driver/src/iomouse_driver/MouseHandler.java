package iomouse_driver;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;

public class MouseHandler  {

	private Robot robot;	
    private double screenWidth;
    private double screenHeight;
    
	public MouseHandler() throws Exception{

		robot = new Robot();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = screenSize.getWidth();
		screenHeight = screenSize.getHeight();
	}

	public void moveMouse(int dx, int dy){

		int x = getX() + dx;
		int y = getY() + dy;
		robot.mouseMove(x, y);
	}
	
	public void leftClick(){
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	public void rightClick(){
		robot.mousePress(InputEvent.BUTTON3_MASK);
		robot.mouseRelease(InputEvent.BUTTON3_MASK);
	}
	
	public void thirdButton(){
	
		System.out.println("Third button does nothing");
	}
	
	public void scroll(int scrollValue){
		robot.mouseWheel(scrollValue);
	}
	
	public void placeMouseByPercentage(double x, double y){
		double z = 100;
		robot.mouseMove((int) Math.round(x * screenWidth  / z),
						(int) Math.round(y * screenHeight / z));		
	}
	
	public void placeMouseByPixel(int x, int y){
		robot.mouseMove(x, y);
	}
	
	public void leftClickHold(){
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	}
	
	public void leftClickRelease(){
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	// Access the current x-position of the cursor
	private int getX(){
		return (int) Math.round(MouseInfo.getPointerInfo().getLocation().getX());
	}

	// Access the current y-position of the cursor
	private int getY(){
		return (int) Math.round(MouseInfo.getPointerInfo().getLocation().getY());
	}
}