package ua.nure.thao.Practice5;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Part2 {
	
	private static final InputStream STD_IN = System.in;     
	private static final String ENCODING = "Cp1251";     

	public static void main(String[] args) throws UnsupportedEncodingException {
		
		final byte[] bytes = "kadjflsdjflsdkjf\n\n".getBytes(ENCODING);
		
		InputStream is = new InputStream() {

			private int cur = 0;
			@Override
			public int read() throws IOException {
				if (cur == 0) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				return cur < bytes.length ? bytes[cur++] : -1;
			}
		};
		System.setIn(is);
		
		Spam.main(args);
		System.setIn(STD_IN);  
	}
}
