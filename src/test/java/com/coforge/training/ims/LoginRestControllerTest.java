package com.coforge.training.ims;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.ui.Model;

import com.coforge.training.ims.model.Address;
import com.coforge.training.ims.model.Dealer;
import com.coforge.training.ims.repository.UserRepository;
import com.coforge.training.ims.rest.LoginRestController;
import com.coforge.training.ims.service.LoginService;

public class LoginRestControllerTest {

	LoginRestController c1=new LoginRestController();

	/*We can use @Mock to create and inject mocked instances without having to call Mockito.mock manually.*/
	@Mock
	private LoginService lService;

	@Mock
	private UserRepository uRepo;

	/*Use the @Spy annotation to spy on an existing instance.*/
	@Spy
	private Model model;


	@Spy
	List<Dealer> dealer = new ArrayList<Dealer>();

	@Spy
	HttpServletRequest req;

	@Spy
	HttpSession ses;

	@BeforeEach
	public void init()
	{
		
		MockitoAnnotations.openMocks(this);
		dealer=getDealerList();
	}


	private List<Dealer> getDealerList() {
		Dealer d1=new Dealer();
		d1.setId(1L);
		d1.setEmail("raj@test.com");
		d1.setFname("Raj");
		d1.setLname("gs");
		d1.setPassword("123");
		d1.setDob(java.sql.Date.valueOf("1985-09-04"));
		d1.setPhoneNo("9999999");

		Address a=new Address();

		a.setStreet("Down Lane");
		a.setCity("Bangalore");
		a.setPincode(560042);

		d1.setAddres(a);
		a.setDealer(d1);

		List<Dealer> res=new ArrayList<Dealer>();

		res.add(d1);

		return res;
	}


	@Test
	public void createDealer()
	{
		System.out.println(dealer.get(0));
		lService.saveDealer(dealer.get(0)); // saving dealer spy object

		verify(lService,times(1)).saveDealer(dealer.get(0));

	}

	@Test
	public void loginDealerTest(){

		String email="satyam1@gmail.com";
		Dealer d=new Dealer();
		d.setEmail("satyam1@gmail.com");
		d.setPassword("12345");
		when(lService.findByEmail(email)).thenReturn(d);

		Dealer x=   lService.findByEmail("satyam1@gmail.com");

//		System.out.println(x.getEmail());
		
		assertEquals(x.getEmail(),"satyam1@gmail.com" );
		assertEquals(x.getPassword(),"MTIzNDU=");

		verify(lService,times(1)).findByEmail("satyam1@gmail.com");       
	}

}
