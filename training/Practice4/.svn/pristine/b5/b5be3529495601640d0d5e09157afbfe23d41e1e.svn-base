package ua.nure.thao.Practice4;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

import ua.nure.thao.Practice4.Part1; 
import ua.nure.thao.Practice4.Part2; 
import ua.nure.thao.Practice4.Part3; 
import ua.nure.thao.Practice4.Part4; 
import ua.nure.thao.Practice4.Part5;

public class Demo {     
	private static final InputStream STD_IN = System.in;     
	private static final String ENCODING = "Cp1251";     
	
	public static String getInput(String fileName){
		StringBuilder sb = new StringBuilder();
		try{
			Scanner scanner = new Scanner(new File(fileName),"Cp1251");
			while(scanner.hasNextLine()){
				sb.append(scanner.nextLine()).append(System.lineSeparator());
			}
			return sb.toString().trim();
		} catch (Exception ex) {}
		return sb.toString();
	}
	
	public static void main(String[] args) throws Throwable {   
		
		System.out.println("=========================== PART1"); 
		Part1.main(args);
		System.out.println();
		
		System.out.println("=========================== PART2");         
		Part2.main(args);
		System.out.println();
		
		System.out.println("=========================== PART3");       
		System.setIn(new ByteArrayInputStream(
				"char\nString\nint\ndouble".getBytes(ENCODING)));         
		Part3.main(args);         
		System.setIn(STD_IN);           
		System.out.println();
		
		System.out.println("=========================== PART4");    
		Part4.main(args);  
		System.out.println();
		
		System.out.println("=========================== PART5");   
		System.setIn(new ByteArrayInputStream(
				"table ru\ntable en\napple ru\nstopskld\ntable ru".getBytes(ENCODING)));         
		Part5.main(args);         
		System.setIn(STD_IN);
	}
}
