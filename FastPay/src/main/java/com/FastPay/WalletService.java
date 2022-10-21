package com.FastPay;


import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.Optional;
import java.util.Calendar;  


@Service
public class WalletService {
	@Autowired
	private UserDataRepo repo;
	public String save(UserData ud) {
			repo.save(ud);
			return "Sucessfully added";
	}
	public String debit(Long phoneno ,double debitbal) {
		try {
			UserData userdata= repo.findById(phoneno).get();
			double exsistingbal =userdata.bal;
//Minimum balance according to the need
			double minbal=0;
			
			if (exsistingbal + minbal > debitbal) {
				if (debitbal<0) {
					return "Invalid Debit Amount";
				}
				exsistingbal-=debitbal;
				UserData u=new UserData();
				u.setName(userdata.name);
				u.setLastName(userdata.lastName);
				u.setPhoneNo(userdata.phoneNo);
				u.setBal(exsistingbal);
				repo.save(u);
				
				final DateTimeFormatter formatter = 
					    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				
				    Date date = Calendar.getInstance().getTime();  
	                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
	                String strDate = dateFormat.format(date);

					
				
				LogWriter lw = new LogWriter();
				lw.writer("Amount "+ Double.toString(debitbal) +" debited from " +Long.toString(phoneno)+" at "+ strDate + "\n");
				return "Debit Sucessfull using card";
				}
			else {
				return "Low Balance";
			}
			} catch (Exception e) {
			return "User not registered or Invalid number";
			}
	}
	public String credit (Long id, double creditbal) {
		
		try {
			UserData userdata= repo.findById(id).get();
			double bal=userdata.getBal();
			
			if (creditbal>0) {
				UserData u=new UserData();
				u.setName(userdata.name);
				u.setLastName(userdata.lastName);
				u.setPhoneNo(userdata.phoneNo);
				u.setBal(bal + creditbal);
				repo.save(u);
				
				
				final DateTimeFormatter formatter = 
					    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				
				    Date date = Calendar.getInstance().getTime();  
	                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
	                String strDate = dateFormat.format(date);
				LogWriter lw = new LogWriter();
				lw.writer("Amount "+ Double.toString(creditbal) +" credited to " +Long.toString(id)+" at "+ strDate + "\n");
				return "Credit Sucessfull";
			}
			else {
				return "Invalid Credit Amount";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			return "User not registered or Invalid number";
		}
		
		
		
	}

	public String balance(Long Phoneno) {
		try {
			double bal = repo.findById(Phoneno).get().bal;
			return Double.toString(bal);
		} catch (Exception e) {
			// TODO: handle exception
			return "User not registered or Invalid number";
		}
	}

	public UserData userdetatil(Long phoneno) {
		// TODO Auto-generated method stub
		try {
			return repo.findById(phoneno).get();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public String transfer(Long phoneno, Reciever r) {
//		debit(phoneno,r.amount);
		boolean flag= true, creditflag=true;
		try {
			UserData userdata= repo.findById(phoneno).get();
			double exsistingbal =userdata.bal;
			double minbal=0;
			if (exsistingbal + minbal > r.amount) {
				if (r.amount<0) {
					flag=false;
					return "Invalid Debit Amount";
				}
				exsistingbal-=r.amount;
				UserData u=new UserData();
				u.setName(userdata.name);
				u.setLastName(userdata.lastName);
				u.setPhoneNo(userdata.phoneNo);
				u.setBal(exsistingbal);
				repo.save(u);
			    }
			else {
				return "Low balance";
			}
		}catch (Exception e) {
						flag =false;
						return "Sender not registered or Invalid number";
						}
		
//		credit(r.phoneno,r.amount);		
		if (flag==true) {
			try {
				UserData userdata= repo.findById(r.phoneno).get();
				double bal=userdata.getBal();
				
				if (r.amount>0) {
					UserData u=new UserData();
					u.setName(userdata.name);
					u.setLastName(userdata.lastName);
					u.setPhoneNo(userdata.phoneNo);
					u.setBal(bal + r.amount);
					repo.save(u);
					}
				else {
					creditflag=false;
					refund(phoneno,r.amount);
					return "Invalid Amount";
				}
			}catch (Exception e) {
						// TODO: handle exception
						creditflag=false;
						refund(phoneno,r.amount);
						return "Reciever not registered or Invalid number";
					}
		}
//log writting
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        String strDate = dateFormat.format(date);
        LogWriter lw = new LogWriter();
        lw.writer("Amount "+ Double.toString(r.amount) +" transferred to " +Long.toString(r.phoneno)+ " from " + Long.toString(phoneno) + " at "+ strDate + "\n");
		return "Sucessfully transferred amount "+ Double.toString(r.amount) +" to "+ Long.toString(r.phoneno) + " from " + Long.toString(phoneno);
	}

	private void refund(Long phoneno, double amount) {
		// TODO Auto-generated method stub
			UserData userdata= repo.findById(phoneno).get();
			double bal=userdata.getBal();
			UserData u=new UserData();
			u.setName(userdata.name);
			u.setLastName(userdata.lastName);
			u.setPhoneNo(userdata.phoneNo);
			u.setBal(bal + amount);
			repo.save(u);
	}
}
	
	
	
	
	


