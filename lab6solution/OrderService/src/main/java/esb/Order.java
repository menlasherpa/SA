package esb;

public class Order {
	private String orderNumber;
	private double amount;
	private boolean international;

	public Order(String orderNumber, double amount, boolean international) {
		this.orderNumber = orderNumber;
		this.amount = amount;
		this.international = international;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String toString(){
		return "order: nr="+orderNumber+" amount="+amount;
	}

	public boolean isInternational() {
		return international;
	}

	public void setInternational(boolean international) {
		this.international = international;
	}
}
