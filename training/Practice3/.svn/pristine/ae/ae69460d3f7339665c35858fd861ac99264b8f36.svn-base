package ua.nure.thao.Practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
	
	public static String convert(String input) {
		StringBuilder sb = new StringBuilder(input);
		String regex = "(?Um)\\W?(\\w*[^$\\W])";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sb);
		int index = 0;
		String ch;
		while (matcher.find()) {
			index = matcher.start();
			if (index == 0) index = -1;
			ch = String.valueOf(sb.charAt(index + 1));
			sb.replace(index + 1, index + 2, ch.toUpperCase());
		}
		return sb.toString();
	}

	public static void main(String[] args) {

		String input = args[0];
		System.out.println(Part3.convert(input));
	}
	
}
