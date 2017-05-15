package iomouse_driver;

import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class MouseHandler  {

	private Robot robot;

	public MouseHandler() throws Exception{

		robot = new Robot();
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
		robot.mousePress(InputEvent.BUTTON2_MASK);
		robot.mouseRelease(InputEvent.BUTTON2_MASK);
	}
	
	public void thirdButton(){
		robot.mousePress(InputEvent.BUTTON3_MASK);
		robot.mouseRelease(InputEvent.BUTTON3_MASK);
	}
	
	public void scroll(int scrollValue){
		robot.mouseWheel(scrollValue);
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