package ua.nure.thao.Practice4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
	
	public String convert(String input) {
		StringBuilder sb = new StringBuilder(input);
		String regex = "(?Um)([\\w]{4,})";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sb.toString());
		
		String word;
		while (matcher.find()) {
			word = matcher.group(1);
			sb.replace(matcher.start(), word.length() 
					+ matcher.start(), word.toUpperCase());
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		
		Part1 part1 = new Part1();
		String input = Demo.getInput("part1.txt");
		System.out.println(part1.convert(input));
	}
}
