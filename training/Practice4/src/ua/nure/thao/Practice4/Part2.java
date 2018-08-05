package ua.nure.thao.Practice4;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

public class Part2 {
	
	public void write2File(String fileName, int[] masInt) throws IOException{
		StringBuilder sb = new StringBuilder();
		for(int i : masInt) {
			sb.append(String.valueOf(i)).append(" ");
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter
				(new FileOutputStream(fileName), "cp1251"));
		bw.write(sb.toString());
	    bw.close();
	}
	
	public int[] readIntFile(String fileName) {
		String[] sInput = Demo.getInput(fileName).split(" ");
		
		int[] masInt = new int[10];
		for(int i = 0; i < sInput.length; i++) {
			masInt[i] = Integer.valueOf(sInput[i].trim());
		}
		return masInt;
	}
	
	public void Sort(int[] masInt) {
		
		for(int i = 0; i< masInt.length - 1; i++) 
			for(int j = i; j < masInt.length; j++) {
				if (masInt[j] < masInt[i]) {
					masInt[i] -= masInt[j];
					masInt[j] += masInt[i];
					masInt[i] = masInt[j] - masInt[i];
				}
			}
	}
	
	public static void main(String[] args) throws IOException {
		
		Part2 part2 = new Part2();
		Random rd = new Random();
		int[] input = new int[10];
		
		System.out.print("Input\t==> ");
		for (int i = 0; i < 10; i++) {
			 input[i] = rd.nextInt(50);
			 System.out.print(input[i] + " ");
		}
		
		part2.write2File("part2.txt", input);
		
		int[] masInt = part2.readIntFile("part2.txt");
		
		part2.Sort(masInt);
		
		System.out.print("\nOutput\t==> ");
		for(int i : masInt) 
			System.out.print(i + " ");
		System.out.println();
		
		part2.write2File("part2_sorted.txt", masInt);
		
	}
}


