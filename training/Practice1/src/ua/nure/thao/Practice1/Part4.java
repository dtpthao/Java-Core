package ua.nure.thao.Practice1;

public class Part4 {

	public static void main(String[] args) {
		int x = Integer.parseInt(args[0]);
		int Sum = 0;
		while (x > 0) {
			Sum += x%10;
			x /= 10;
		}
		System.out.println(Sum);
	}

}
