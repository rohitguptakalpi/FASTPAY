package com.FastPay;

public class Reciever {
	long phoneno;
	double amount;
	public long getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(long phoneno) {
		this.phoneno = phoneno;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Reciever(long phoneno, double amount) {
		super();
		this.phoneno = phoneno;
		this.amount = amount;
	}
	public Reciever() {}
}
