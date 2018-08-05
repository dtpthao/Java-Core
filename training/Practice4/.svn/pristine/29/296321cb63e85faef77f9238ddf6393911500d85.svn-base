package ua.nure.thao.Practice4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
	
	public String Readfile(String input, String type) {
		
		String regex = "";
		if (type.equalsIgnoreCase("char") == true) {
			regex = "(?Um)\\b([\\w&&\\D])\\b";
		}
		else if (type.equalsIgnoreCase("string") == true) {
			regex = "(?Um)([\\w&&\\D]{2,})";
		}
		else if (type.equalsIgnoreCase("int") == true) {
			regex = "(?<=\\s|^)(\\d+)(?=\\s|$)";
		}
		else if (type.equalsIgnoreCase("double") == true) {
			regex = "(?Um)(\\d*\\.\\d*)";
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		StringBuilder sb = new StringBuilder();
		while (matcher.find()) {
			sb.append(matcher.group(0)).append(" ");
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException{
		
		Part3 part3 = new Part3();
		String fileInput = Demo.getInput("part3.txt");
        BufferedReader dataIn = new BufferedReader(new
        					InputStreamReader(System.in));
        String type = "";
        
		while ((type = dataIn.readLine()) != null) {
			System.out.println(part3.Readfile(fileInput, type));
		}
	}

}
