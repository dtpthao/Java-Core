package ua.nure.thao.Practice5;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Part5 {
	
	final static int col = 20;
	final static int row = 10;
	
	public static void main(String[] args) throws Throwable {
		
		File random = new File("part5.txt");
	    while (random.exists()) {
	    		random.delete();
	    		random = new File("part5.txt");
	    }
	    
		RandomAccessFile randomFile = new RandomAccessFile(random, "rw");
		Thread[] th = new Thread[10];
		for (int i = 0; i < row; i++) {
			final Integer iFinal = new Integer(i);
			th[i] = new Thread() {
				public void run() {
					synchronized(randomFile) {
						try {
							randomFile.seek(iFinal*(col + 1));
							for (int j = 0; j < col; j++) {
								randomFile.write('0' + iFinal);
								Thread.sleep(1);
							}
							randomFile.write('\n');
						} catch (IOException | InterruptedException e) {
							e.printStackTrace();
						}
					}
				};
			};
		}
		for (Thread iTh : th) iTh.start();
//		System.out.println("Number of active threads: " + Thread.activeCount());
		for (Thread iTh : th) iTh.join();
		randomFile.close();
		System.out.println(Part4.getInput("part5.txt"));
	}

}
