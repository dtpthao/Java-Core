package ua.nure.thao.Practice5;

import ua.nure.thao.Practice5.part3.Counter;
import ua.nure.thao.Practice5.part3.NoneSynchronizedThread;
import ua.nure.thao.Practice5.part3.SynchronizedThread;

public class Part3 {
	
	final static int len = 30;
	
	public static void main(String[] args) throws InterruptedException {
		Counter c = new Counter(3, 3);
		Counter sc = new Counter(3, 3);
		Thread[] th = new Thread[len];
		Thread[] sth = new Thread[len];
		for (int i = 0; i < len; i++) {
			th[i] = new NoneSynchronizedThread(c, i);
			sth[i] = new SynchronizedThread(sc, i);
		}
		
		for (Thread t : th) t.start();
		Thread.sleep(1000);
		for (Thread t : sth) t.start();
		for(int i = 0; i< len; i++) {
			th[i].join();
			sth[i].join();
		}
	}
	

}
