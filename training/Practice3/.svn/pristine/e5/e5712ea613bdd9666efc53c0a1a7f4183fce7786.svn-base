package ua.nure.thao.Practice3;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

	public static String convert1(String input) {
		
		StringBuilder sb = new StringBuilder(""); 
		String  regex = "(?Um)(\\w*);[\\w ]*;(.*@.*)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		
		while (matcher.find()) {
			sb.append(matcher.group(1)).append(" ==> ")
				.append(matcher.group(2)).append("\n");
		}
		
		return sb.delete(sb.length() - 1, sb.length()).toString();
	}
	
	public static String convert2(String input) {
		
		StringBuilder sb = new StringBuilder(""); 
		String  regex = "(?Um).*;(\\w*) (\\w*);(.*@.*)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		
		while (matcher.find()) {
			sb.append(matcher.group(2)).append(' ').append(matcher.group(1))
			.append(" (email: ").append(matcher.group(3)).append(")\n");
		}

		return sb.delete(sb.length() - 1, sb.length()).toString();
	}
	
	public static String convert3(String input) {
		
		StringBuilder sb = new StringBuilder(""); 
		String  regex = "(?Um)(\\w*);[\\w ]*;.*@(.*)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		int index = 0;
		while (matcher.find()) {
			index = sb.indexOf(matcher.group(2));
			if (index == -1) {
				sb.append(matcher.group(2)).append(" ==> ")
					.append(matcher.group(1)).append("\n");
			}
			else {
				index = sb.indexOf("\n", index);
				if (index == -1) index = sb.length();
				sb.insert(index, ", " + matcher.group(1));
			}
		}
		
		return sb.delete(sb.length() - 1, sb.length()).toString();
	}
	
	public static String convert4(String input) {
		StringBuilder sb = new StringBuilder(input); 
		int index = sb.indexOf("\n");
		int pass;
		Random rd = new Random();
		sb.insert(index, ";Password");
		index = sb.indexOf("\n", index + 10);
		while (index > -1) {
			pass = 1000 + rd.nextInt(8999);
			sb.insert(index, ";" + pass);
			index = sb.indexOf("\n", index + 6);
		}
		pass = 1000 + rd.nextInt(8999);
		
		return sb.append(";" + pass + "\n").toString();
	}
	
public static void main(String[] args) {
		
		String input = args[0];
		
		System.out.println("====Convert 1:");
		System.out.println(Part1.convert1(input));
		System.out.println();
		
		System.out.println("====Convert 2:");
		System.out.println(Part1.convert2(input));
		System.out.println();
		
		System.out.println("====Convert 3:");
		System.out.println(Part1.convert3(input));
		System.out.println();

		System.out.println("====Convert 4:");
		System.out.println(Part1.convert4(input));
	}

}

