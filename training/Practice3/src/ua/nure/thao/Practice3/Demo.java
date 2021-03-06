package ua.nure.thao.Practice3;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import ua.nure.thao.Practice3.Part1;
import ua.nure.thao.Practice3.Part2;
import ua.nure.thao.Practice3.Part3;
import ua.nure.thao.Practice3.Part4;
import ua.nure.thao.Practice3.Part5;

public class Demo {
	
	private static final String[] EMPTY_ARGS = {};
	
	public static String getInput(String fileName){
		StringBuilder sb = new StringBuilder();
		try{
			Scanner scanner = new Scanner(new File(fileName),"UTF-8");
			while(scanner.hasNextLine()){
				sb.append(scanner.nextLine()).append(System.lineSeparator());
			}
			return sb.toString().trim();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}

	public static void main(String[] args) throws NoSuchAlgorithmException{
		
		System.out.println("===================Part1");
		String input = Demo.getInput("Input1.txt");
		Part1.main(new String[] {input});
		System.out.println();
		
		System.out.println("===================Part2");
		input = Demo.getInput("Input2.txt");
		
		Part2.main(new String[] {input});
		System.out.println();
		
		System.out.println("===================Part3");
		input = Demo.getInput("Input3.txt");
		Part3.main(new String[] {input});
		
		System.out.println();
		System.out.println("===================Part4");
		Part4.main(EMPTY_ARGS);
		System.out.println();
		
		System.out.println("===================Part5");
		Part5.main(EMPTY_ARGS);
	}

}
