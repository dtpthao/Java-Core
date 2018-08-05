package ua.nure.thao.Practice7.util;

import java.util.Collections;
import java.util.Comparator;

import ua.nure.thao.Practice7.constants.Constants;
import ua.nure.thao.Practice7.controller.DOMController;
import ua.nure.thao.Practice7.entity.Bank;
import ua.nure.thao.Practice7.entity.Deposit;

public class Sorter {

	public static final Comparator<Deposit> SORT_DEPOSITS_BY_NAME = 
			new Comparator<Deposit>() {
				@Override
				public int compare(Deposit o1, Deposit o2) {
					return o1.getName().compareTo(o2.getName());
				}
	};

	public static final Comparator<Deposit> SORT_DEPOSITS_BY_ACCOUNTID = 
			new Comparator<Deposit>() {
				@Override
				public int compare(Deposit o1, Deposit o2) {
					return o1.getAccountId().compareTo(o2.getAccountId());
				}
	};

	public static final Comparator<Deposit> SORT_DEPOSITS_BY_TYPE = 
			new Comparator<Deposit>() {
				@Override
				public int compare(Deposit o1, Deposit o2) {
					return o1.getType().compareTo(o2.getType());
				}
	};

	public static final void sortDepositsByName(Bank bank) {
		Collections.sort(bank.getDeposits(), SORT_DEPOSITS_BY_NAME);
	}

	public static final void sortDepositsByAccountId(Bank bank) {
		Collections.sort(bank.getDeposits(), SORT_DEPOSITS_BY_ACCOUNTID);
	}

	public static final void sortDepositsByType(Bank bank) {
		Collections.sort(bank.getDeposits(), SORT_DEPOSITS_BY_TYPE);
	}

	public static void main(String[] args) throws Exception {
		
		DOMController domController = new DOMController(
				Constants.VALID_XML_FILE);
		domController.parse(false);
		Bank bank = domController.getBank();

		System.out.println("====================================");
		System.out.println(bank);
		System.out.println("====================================");

		System.out.println("====================================");
		Sorter.sortDepositsByName(bank);
		System.out.println(bank);
		System.out.println("====================================");

		System.out.println("====================================");
		Sorter.sortDepositsByAccountId(bank);
		System.out.println(bank);
		
		System.out.println("====================================");
		Sorter.sortDepositsByType(bank);
		System.out.println(bank);
	}
}