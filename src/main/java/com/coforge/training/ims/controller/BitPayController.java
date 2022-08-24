package com.coforge.training.ims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BitPayController {

//	method to display bit-pay.jsp file which consumes public rest api data
	@RequestMapping("/bitpay")
	public String viewBitPayPage()
	{
		return "bit-pay";
	}
	
}
