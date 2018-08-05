package ua.nure.thao.Practice6.part3;

import java.util.ArrayList;
import java.util.List;

public class Parking {

	static List<Boolean> lot;
	
	Parking (int size) {
		lot = new ArrayList<Boolean>(size);
		for(int i = 0; i < size; i++) {
			lot.add(true);
		}
	}
	
	@SuppressWarnings("serial")
	public static class PlaceDoesNotExist extends RuntimeException {
		
		public PlaceDoesNotExist() {}
	}
	
	public int park(int place) {
		
		if (place >= lot.size()) {
			throw new PlaceDoesNotExist();
		}
		if (lot.get(place)) {
			lot.set(place, false);
			return place;
		}
		if (place == lot.size() - 1) {
			return lot.size();
		}
		return this.park(++place);
	}
	
	public boolean leave(int place) {
		
		if (place >= lot.size()) {
			throw new PlaceDoesNotExist();
		}
		if (lot.get(place)) {
			return false;
		}
		lot.set(place, true);
		return true;
	}
	
	public String status(int place) {
		
		if (place >= lot.size()) {
			throw new PlaceDoesNotExist();
		}
		return lot.get(place) ? "Free" : "Busy";
	}
}
