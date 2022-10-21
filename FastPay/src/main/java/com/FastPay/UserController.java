package com.FastPay;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {
	
	@Autowired
	private WalletService service;

//	handled the GET request for balance check
	@GetMapping("/balance/{phoneno}")
	public String bal(@PathVariable Long phoneno){
		if ( Long.toString(phoneno).length() != 10) {
			return "Invalid Mobile Number";
			
		}
		return service.balance(phoneno);		
	}
	
//	handle the GET request for user details only for ->> (testing purpose)
	@GetMapping("/userdetail/{phoneno}")
	public UserData userdetail(@PathVariable Long phoneno){
		return service.userdetatil(phoneno);
	}
	
//	handle the POST request for add new user
	@PostMapping("/add/user")
	public String addUser(@RequestBody UserData us){
		if ( Long.toString(us.phoneNo).length() != 10) {
			return "Invalid Mobile Number";
			
		}
		return service.save(us);
	}
	
	
	@PostMapping("/credit/{phoneno}")
	public String credit(@RequestBody Amount amount, @PathVariable Long phoneno ) {
		if ( Long.toString(phoneno).length() != 10) {
			return "Invalid Mobile Number";
			
		}
		return service.credit(phoneno, amount.getAmount());
	}
	
	@PostMapping("debit/{phoneno}")
	public String debit(@RequestBody Amount amount,@PathVariable Long phoneno) {
		if ( Long.toString(phoneno).length() != 10) {
			return "Invalid Mobile Number";
			
		}
		return service.debit(phoneno, amount.getAmount());
	}

	
	@PostMapping("transfer/{phoneno}")
	public String transfer(@RequestBody Reciever r, @PathVariable Long phoneno ) {
		if ( Long.toString(phoneno).length() != 10) {
			return "Invalid Sender Mobile Number ";
			
		}
		if ( Long.toString(r.phoneno).length() != 10) {
			return "Invalid Reciever Mobile Number ";
			
		}
		
		return service.transfer(phoneno, r);
	}
		
		
	
		

}
