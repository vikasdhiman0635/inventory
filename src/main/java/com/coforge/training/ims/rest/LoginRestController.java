package com.coforge.training.ims.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coforge.training.ims.exception.ResourceNotFoundException;
import com.coforge.training.ims.model.Address;
import com.coforge.training.ims.model.Dealer;
import com.coforge.training.ims.model.DealerAddress;
import com.coforge.training.ims.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api")
public class LoginRestController {

	@Autowired
	private UserRepository uRepo;
	
	@PostMapping("/dealers")
	public DealerAddress createDealer(@Validated @RequestBody DealerAddress dealer)
	{
		Dealer d=new Dealer();
		d.setEmail(dealer.getEmail());
		d.setDob(dealer.getDob());
		d.setFname(dealer.getFname());
		d.setLname(dealer.getLname());
		d.setPassword(dealer.getPassword());
		d.setPhoneNo(dealer.getPhoneNo());
		
		Address a=new Address();
		a.setStreet(dealer.getStreet());
		a.setCity(dealer.getCity());
		a.setPincode(dealer.getPincode());
		
		d.setAddres(a);
		a.setDealer(d);
		
		uRepo.save(d);
		
		return dealer;
	}
	
	
	@GetMapping("/dealers")
	public List<DealerAddress> getAllDealers()
	{
		return uRepo.fetchDealerInnerJoin();
	}
	
	
	@PostMapping("/dealer")
	public Boolean loginDealer(@Validated @RequestBody Dealer dealer) throws ResourceNotFoundException
	{
		Boolean a=false;
		
		String email=dealer.getEmail();
		String password=dealer.getPassword();
		
		Dealer d=uRepo.findItByEmail(email);
			
		if(email.equals(d.getEmail()) && password.equals(d.getPassword()))
		{
			a=true;
		}
		
		return a;
	}
	
}
