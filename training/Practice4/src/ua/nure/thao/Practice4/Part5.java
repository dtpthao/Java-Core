package ua.nure.thao.Practice4;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {
	
	public String translate(String key, String locale) {
	    Locale local = new Locale(locale);
	    ResourceBundle resourceBundle = ResourceBundle.getBundle("resources", local);
	    return resourceBundle.getString(key);
	}
	
	public static void main(String[] args) throws IOException {
        
		Part5 part5 = new Part5();
        Scanner sc = new Scanner(System.in);
        String key = null, local = null;
        while (sc.hasNextLine()) {
        		key = sc.next();
        		if (key.contains("stop")) {
        			break;
        		}
        		local = sc.next();
        		System.out.println(part5.translate(key,local));
        }
	}
}
