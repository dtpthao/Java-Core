package ua.nure.thao.Practice7.constants;


public enum XML {

	BANK("Bank"), DEPOSIT("Deposit"), NAME("Name"), COUNTRY("Country"), DEPOSITOR("Depositor"),
	ACCOUNTID("AccountID"), TYPE("Type"), AMOUNTONDEPOSIT("AmountOnDeposit"),
	PROFITABILITY("Profitability"), TIMECONSTRAINTS("TimeConstraints");

	private String value;

	XML(String value) {
		this.value = value;
	}
	
	public boolean equalsTo(String name) {
		return value.equals(name);
	}

	public String value() {
		return value;
	}
}
