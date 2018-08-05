package ua.nure.thao.Practice3;

public class Part5 {
	
	public static String decimal2Roman(int x){
		
		StringBuilder sb = new StringBuilder("");
		String[] roman = {"I", "V", "X", "L", "C"};
		int powOf10 = 1, div = 0, index = 0;
		while (x/powOf10 >= 10) {
			powOf10 *= 10;
			index += 2;
		}
		while (x > 0) {
			div = x / powOf10;
			if (div == 9)  {
				sb.append(roman[index]).append(roman[index + 2]);
			}
			else {
				if (div == 4) {
					sb.append(roman[index]).append(roman[index + 1]);
				}
				else {
					if (div >= 5) {
						sb.append(roman[index + 1]);
						div -= 5;
					}
					while (div > 0) {
						sb.append(roman[index]);
						div--;
					}
				}
			}
			x %= powOf10;
			powOf10 /= 10;
			index -= 2;
		}
		return sb.toString();
	} 
	
	
	public static int roman2Decimal(String s){
		
		String roman = "IVXLC";
		int[] dec = {1, 5, 10, 50, 100};
		int number = 0, index = 0;
		while (index < s.length()) {
			if ((index + 1) < s.length() && (roman.indexOf(s.charAt(index))
					 < roman.indexOf(s.charAt(index + 1))))
				number -= dec[roman.indexOf(s.charAt(index))];
			else number += dec[roman.indexOf(s.charAt(index))];
			index++;
		}
		
		return number;
	}

	public static void main(String[] args) {

		String s = "";
		int x;
		for (int i = 1; i < 101; i++) {
			s = Part5.decimal2Roman(i);
			x = Part5.roman2Decimal(s);
			System.out.println(i + " ====> " + s + " ====> " + x);

		}
	}
}