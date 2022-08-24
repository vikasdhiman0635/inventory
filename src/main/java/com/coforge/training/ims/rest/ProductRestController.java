package com.coforge.training.ims.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coforge.training.ims.exception.ResourceNotFoundException;
import com.coforge.training.ims.model.Product;
import com.coforge.training.ims.repository.ProductRepository;
import com.coforge.training.ims.service.ProductService;

/*
 * Cross-origin resource sharing (CORS) is a standard protocol that defines the interaction between
 * a browser and a server for safely handling cross-origin HTTP requests.
Simply put, a cross-origin HTTP request is a request to a specific resource,
which is located at a different origin, namely a domain, protocol and port, than
the one of the client performing the request.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ProductRestController {

	@Autowired
	private ProductService pService;
	
	@Autowired
	private ProductRepository pRepo;
	
	@GetMapping("/products")
	public List<Product> getAllProducts()
	{
		return pService.listAll();
	}
	
	/**
	* ResponseEntity represents an HTTP response, including headers, body, and status.
	*/
	/*@PathVariable is a Spring annotation which indicates that a method parameter 
	 * should be bound to a URI template variable.
	 * */
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long pId) throws ResourceNotFoundException
	{
		Product p=pRepo.findById(pId).orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + pId));
		return ResponseEntity.ok().body(p);
	}
	
	
//	@RequestBody annotation automatically deserialize JSOn into java type
	/*The @Validated annotation is a class-level annotation that we can use to 
	 * tell Spring to validate parameters that are passed into a method of the 
	 * annotated class.*/
	@PostMapping("/products")
	public Product saveProduct(@Validated @RequestBody Product product)
	{
		return pRepo.save(product);
	}
	
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long pId, 
			@Validated @RequestBody Product product) 
			throws ResourceNotFoundException
	{
		Product p=pRepo.findById(pId).orElseThrow(() -> 
			new ResourceNotFoundException("Product not found for this id :: " + pId));
				
		p.setBrand(product.getBrand());
		p.setMadein(product.getMadein());
		p.setName(product.getName());
		p.setPrice(product.getPrice());
		
		final Product updatedProduct=pRepo.save(p); // invokes save method of JPA Repository
		
		return ResponseEntity.ok().body(updatedProduct);
		
	}
	
	
	@DeleteMapping("/products/{id}")
	public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long pId) 
			throws ResourceNotFoundException
	{
		Product p=pRepo.findById(pId).orElseThrow(() -> 
			new ResourceNotFoundException("Product not found for this id :: " + pId));
		
		pRepo.delete(p); // invokes delete method of JPA Repository
		
		Map<String, Boolean> response=new HashMap<>();
		response.put("Deleted Product", Boolean.TRUE);
		
		return response;
		
	}
	
	
	
	
	
	
}
