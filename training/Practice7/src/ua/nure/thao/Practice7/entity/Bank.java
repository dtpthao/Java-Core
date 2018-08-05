package ua.nure.thao.Practice7.entity;

import java.util.ArrayList;
import java.util.List;

public class Bank {

	private List<Deposit> deposits;

	public List<Deposit> getDeposits() {
		if (deposits == null) {
			deposits = new ArrayList<Deposit>();
		}
		return deposits;
	}
	
	@Override
	public String toString() {
		if (deposits == null || deposits.size() == 0) {
			return "Bank contains no deposits";
		}
		StringBuilder result = new StringBuilder("");
		for (Deposit deposit : deposits) {
			result.append(deposit).append("\n\n");
		}
		return result.delete(result.length()-2, result.length()).toString();
	}

}