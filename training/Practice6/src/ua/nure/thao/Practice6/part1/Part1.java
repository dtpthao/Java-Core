package ua.nure.thao.Practice6.part1;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Part1 {
	
	private static final InputStream STD_IN = System.in;
	
	private static final String ENCODING = "UTF-8";
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		System.setIn(new ByteArrayInputStream("asd 43 asdf asd 43, 434, asdsf asdf stop".getBytes(ENCODING)));
		
		WordContainer.main(null);
		System.setIn(STD_IN);
	}

}
