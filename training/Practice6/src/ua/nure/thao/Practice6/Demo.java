package ua.nure.thao.Practice6;

import java.io.UnsupportedEncodingException;

import ua.nure.thao.Practice6.part1.Part1;
import ua.nure.thao.Practice6.part2.Part2;
import ua.nure.thao.Practice6.part3.Part3;
import ua.nure.thao.Practice6.part4.Part4;
import ua.nure.thao.Practice6.part5.Part5;
import ua.nure.thao.Practice6.part6.Part6;
import ua.nure.thao.Practice6.part7.Part7;

public class Demo {
	
	private static final String[] EMPTY_ARGS = {};

	public static void main(String[] args) throws UnsupportedEncodingException {

		System.out.println("=========================== PART1");         
		Part1.main(EMPTY_ARGS);
		System.out.println();
		
		System.out.println("=========================== PART2");  
		Part2.main(EMPTY_ARGS);
		System.out.println();
		
		System.out.println("=========================== PART3");  
		Part3.main(EMPTY_ARGS);         
		System.out.println();
		
		System.out.println("=========================== PART4");   
		Part4.main(EMPTY_ARGS);      
		System.out.println();
		
		System.out.println("=========================== PART5");         
		Part5.main(EMPTY_ARGS);        
		System.out.println();

		System.out.println("=========================== PART6");         
		Part6.main(new String[] {"--input", "part6.txt", "--task", "length"});
		Part6.main(new String[] {"--input", "part6.txt", "--task", "frequency"});
		Part6.main(new String[] {"--input", "part6.txt", "--task", "duplicate"});
		System.out.println();
		
		System.out.println("=========================== PART7");        
		Part7.main(EMPTY_ARGS);
	}

}
