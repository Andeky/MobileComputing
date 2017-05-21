package iomouse_driver;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	/**
	 * main file of the iomouse driver
	 * @throws Exception 
	 */
	
	public static void main(String[] args) throws Exception{

		InputStreamReader isReader = new InputStreamReader(System.in);
		BufferedReader bufReader = new BufferedReader(isReader);
		Parser parser = new Parser();

		while(true){
		    try {
		        String inputStr = null;
		        if((inputStr = bufReader.readLine()) != null) {

		        	parser.parseLine(inputStr);
		        	
		        }
		        else {
		            System.out.println("iomouse driver terminated");
		            break;
		        }
		    }
		    catch (Exception e) {
		       
		    }
		}		
	}
}
