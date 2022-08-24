package com.coforge.training.ims;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.coforge.training.ims.model.BitPay;
import com.coforge.training.ims.model.Post;
import com.coforge.training.ims.model.Product;

public class ConsumeRestJSON {

	public static void main(String[] args) {
		
//		RestTemplate is used consume rest full webservices. It uses HttpServer using Restfull Principle
//		It retrieves an entity using HTTP GET method on the given URL.
		RestTemplate restTemplate=new RestTemplate();
		
		Product p=restTemplate.getForObject("http://localhost:8085/ims/api/products/{id}", Product.class,6);
		
		System.out.println("Product Id: "+p.getId());
		System.out.println("Product Name: "+p.getName());
		System.out.println("Product Price: "+p.getPrice());
		System.out.println("Product Made In: "+p.getMadein());
		
		
		System.out.println("***************************");

//		List<BitPay> bit=(List<BitPay>) restTemplate.getForObject("https://bitpay.com/api/rates", BitPay.class);
//	
//		System.out.println(bit.get(2));
//		
//		for(BitPay bp:bit)
//		{
//			System.out.println("Code: "+bp.getCode());
//		}
		
		Post p1=restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/{id}", Post.class,2);
		
		System.out.println("user Id: "+p1.getUserId());
		System.out.println("Id: "+p1.getId());
		System.out.println("Title: "+p1.getTitle());
		System.out.println("Body: "+p1.getBody());
		
	}
	
}
