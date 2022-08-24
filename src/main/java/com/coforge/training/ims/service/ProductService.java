package com.coforge.training.ims.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coforge.training.ims.model.Product;
import com.coforge.training.ims.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository prepo;
	
	
	
//	using method of CURD/JPA repository
	public List<Product> listAll() {
		return prepo.findAll(); // defined in JPA repo
	}

	public void save(Product product) { // save is userdefined
		prepo.save(product); // save method defined in JPA repo
	}

	public Product get(long id) {
		return prepo.findById(id).get(); // defined in JPA repo
	}

	public void delete(long id) {
		prepo.deleteById(id); // defined in JPA repo
	}
	
}
