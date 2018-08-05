package ua.nure.thao.Practice5.part3;

public class NoneSynchronizedThread extends Thread {
	
	private Counter c;
	private int index;
	
	public NoneSynchronizedThread(Counter c, int index) {
		this.c = c;
		this.index = index;
	}
	
	@Override
	public void run() {
		StringBuffer sb = new StringBuffer("NoneSynThread#")
				.append(index).append(" : ")
				.append(c.c1).append((c.c1 == c.c2) ? " = " : " != ")
				.append(c.c2);
		System.out.println(sb.toString());
		c.c1++;
		try {
			Thread.sleep(10);
		} catch (InterruptedException ie) {
		}
		c.c2++;
	}

	

}


