package ua.nure.thao.Practice5;

import java.util.Scanner;

public class Spam {
	
	private Thread[] th;
	private int len = 0;
	
	void start() {
		for(Thread t : th) t.start();
	}
	
	void stop() {
		for(Thread t : th) t.interrupt();
	}
	
	Spam(int[] times, String[] message) {
		
		len = times.length;
		th = new Thread[len];
		for(int i = 0; i < len; i++) {
			final Integer iFinal = new Integer(i);
			this.th[i] = new Thread() {
				public void run() {
					while(true && this.isInterrupted() == false) {
						System.out.println(message[iFinal]);
						 try {                
				             Thread.sleep(times[iFinal]);
				         } catch (InterruptedException ie) {
				             return;
				         }
					}
				};
			};
		}
	}
	
	public static void main(String[] args) {
		Spam spam = new Spam(new int[] {333, 555},
				new String[] {"AAAAA", "bbb"});
		spam.start();
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			if (s.equals("")) {
				spam.stop();
				break;
			}
		}
		sc.close();
	}

}
