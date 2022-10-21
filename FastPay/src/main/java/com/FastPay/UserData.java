package com.FastPay;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserData {
	
	@Id
	Long phoneNo;
	
	String name;
	String lastName;
	double bal;
	
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public double getBal() {
		return bal;
	}
	public void setBal(double bal) {
		this.bal = bal;
	}
	public UserData(Long phoneNo, String name, String lastName, double bal) {
		super();
		this.phoneNo = phoneNo;
		this.name = name;
		this.lastName = lastName;
		this.bal = bal;
	}
	
	public UserData() {}
	

	@Override
	public String toString() {
		return "UserData [ bal=" + bal + "]";
	}
	
	
	

}
