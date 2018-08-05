package ua.nure.thao.Practice3;
import java.security.*; 
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 {
	
	public static String hash(String input, String algorithm) throws NoSuchAlgorithmException{ 
		
		MessageDigest digest = MessageDigest.getInstance(algorithm); 
		digest.update(input.getBytes());
		byte[] hash = digest.digest();
		return convert2Hex(hash);
	}
	
	private static String convert2Hex(byte[] hash) {
		
		String regex = "(-?\\d{1,3})\\D";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(Arrays.toString(hash));
		
		String sNumber = "";
		int iNumber;
		StringBuilder sb = new StringBuilder();
		while (matcher.find()) {
			sNumber = matcher.group(1);
			iNumber = Integer.parseInt(sNumber);
			sNumber = String.valueOf(Integer.toHexString((iNumber & 0xf0) >> 4));
			sb.append(sNumber);
			sNumber = String.valueOf(Integer.toHexString(iNumber & 0xf));
			sb.append(sNumber);
		}
		return sb.toString().toUpperCase();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException{ 
		System.out.println("SHA256(\"password\") = ");
		System.out.println(hash("password","SHA-256")); 
		System.out.println();
		System.out.println("SHA256(\"passwort\") = ");
		System.out.println(hash("passwort","SHA-256"));
	}
}
