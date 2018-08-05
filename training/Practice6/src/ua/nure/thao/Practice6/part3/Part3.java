package ua.nure.thao.Practice6.part3;

import ua.nure.thao.Practice6.part3.Parking.PlaceDoesNotExist;

public class Part3 {
	
	final static int size = 10;
	
	private static void printParking(Parking car, int number) {
		
		System.out.print("No." + number + ": ");
		int result = car.park(number);
		if (result == number) {
			System.out.println("Parking is done.");
		}
		else if (result == size) {
			System.out.println("Could not find any free lot.");
		}
		else {
			System.out.println("It's busy! Parked "
					+ "in place No. " + result + ".");
		}
	}
	
	private static void printLeave(Parking car, int number) {
		
		System.out.print("No." + number + ": ");
		if (car.leave(number)) {
			System.out.println("A car left.");
		}
		else System.out.println("No car here.");
	}
	
	public static void main(String[] args) {
		
		Parking car = new Parking(10);
		System.out.println("======Parking");
		printParking(car, 0);
		printParking(car, 3);
		printParking(car, 8);
		printParking(car, 6);
		printParking(car, 9);
		printParking(car, 3);
		printParking(car, 8);
		try {
            car.park(size);
        } catch (PlaceDoesNotExist ex) {
        		System.out.println("No.10: Couldn't find that place.");
        }
		System.out.println();
		
		System.out.println("======Leave");
		printLeave(car, 9);
		printLeave(car, 5);
		printLeave(car, 9);
		System.out.println();
		
		System.out.println("======Status");
		System.out.println("No.1 :" + car.status(1));
		System.out.println("No.3 :" + car.status(3));
		System.out.println("No.9 :" + car.status(9));
	}

}
