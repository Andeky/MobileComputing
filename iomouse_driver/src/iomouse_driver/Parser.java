package iomouse_driver;

public class Parser {
	
	private MouseHandler mouseHandler;
	
	public Parser() throws Exception{
		mouseHandler = new MouseHandler();
	}
	
	public void parseLine(String string){
		
		String[] parts;
		
		if( (parts = string.split("IO_MOUSE_TAG: ")).length > 1){
			// Mouse tag has been registered 
			
			String command = parts[1];
			parseMouseCommand(command);
			
		}
		else{
			// Given string is noise to the driver
			
		}
			
	}
	
	private void parseMouseCommand(String command){
		
		String[] parts;
		
		if( (parts = command.split("moveMouse ")).length > 1){
			
			String moveCommand = parts[1];
			parseMoveMouse(moveCommand);
		}
		
		else if( (parts = command.split("leftClick")).length > 1){
			
			System.out.println("left click");
			mouseHandler.leftClick();
		}
		
		else if( (parts = command.split("rightClick")).length > 1){
			
			System.out.println("right click");
			mouseHandler.rightClick();
		}
		
		else if( (parts = command.split("thirdButton")).length > 1){
			
			System.out.println("third button");
			mouseHandler.thirdButton();
		}
		
		else if( (parts = command.split("scroll ")).length > 1){
			
			String scrollCommand = parts[1];
			parseScroll(scrollCommand);
		}
		
		else{
			System.out.println("Error - command: \"" + command + "\" was not recognized");
		}
	}
	
	private void parseMoveMouse(String command){
		
		String[] parts = command.split(" ");
		
		int dx = Integer.parseInt(parts[0]);
		int dy = Integer.parseInt(parts[1]);
		
		System.out.println("dx = " + dx + "   dy = " + dy);
		
		mouseHandler.moveMouse(dx,dy);
	}
	
	private void parseScroll(String command){
		
		String[] parts = command.split(" ");
		
		int scrollValue = Integer.parseInt(parts[0]);
		mouseHandler.scroll(scrollValue);
		
		System.out.println("scroll: " + scrollValue);
	}
}
