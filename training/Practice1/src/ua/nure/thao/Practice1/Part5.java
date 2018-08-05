package ua.nure.thao.Practice1;

public class Part5 {

	public static int str2int(String number) {
		int index = 0, digit = 0;
		while (index < number.length()) {
			digit *= 26;
			digit += number.charAt(index) - 64;
			index++;
		}
		return digit;
	}
	
	public static String int2str(int number) {
		StringBuilder sb = new StringBuilder("");
		int x = number, y = 0;
		while (x > 0) {
			y = x%26 > 0 ? x%26 + 64 : 90;
			sb.insert(0, String.valueOf((char)y));
			x -= y == 90 ? 26 :0;
			x /= 26;
		}
		return sb.toString();
	}
	
	public static String rightColumn(String number) {
		StringBuilder sb = new StringBuilder(number);
		int index = number.length() - 1, digit;
		while (index >= 0) {
			digit = number.charAt(index);
			if (digit != 90) {
				digit += 1;
				sb.replace(index, index+1, String.valueOf((char)digit));
				return sb.toString();
			}
			sb.replace(index, index+1, "A");
			index--;
		}
		if (index == -1) sb.append("A");
		return sb.toString();
	}
	
	private static void test(String s) {
		int number = Part5.str2int(s);
		String s1 = Part5.int2str(number);
		System.out.println(s+" ==> "+number+" ==> "+s1);
		s = Part5.rightColumn(s1);
		number = Part5.str2int(s);
		s1 = Part5.int2str(number);
		System.out.println(s+" ==> "+number+" ==> "+s1);
	}
	
	public static void main(String[] args) {
		test("A");
		test("Z");
		test("AZ");
		test("ZZ");
		
	}

}
