package ua.nure.thao.Practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
	
	public static String convert(String input) {
		
		StringBuilder output = new StringBuilder("");
		StringBuilder sMin = new StringBuilder("");
		StringBuilder sMax = new StringBuilder("");
		String regex = "(?Um)\\W?(\\w*[^$\\W])";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		int min = input.length(), max = 0;
		while (matcher.find()) {
			if (matcher.group(1).length() > max) {
				max = matcher.group(1).length();
			}
			else if (matcher.group(1).length() < min) {
				min = matcher.group(1).length();
			}
		}
		
		matcher = pattern.matcher(input);
		String s = "";
		while (matcher.find()) {
			s = matcher.group(1);
			if (s.length() == min && sMin.indexOf(s) == -1) {
				sMin.append(s).append(", ");
			}
			else if (s.length() == max && sMax.indexOf(s) == -1) {
				sMax.append(s).append(", ");
			}
		}
		sMax.delete(sMax.length() - 2, sMax.length());
		sMin.delete(sMin.length() - 2, sMin.length());
		
		return output.append("Min: ").append(sMin)
				.append("\nMax: ").append(sMax).toString();
	}
	
	public static void main(String[] args) {
		
		String input = args[0];
		System.out.println(Part2.convert(input));
	}
}
