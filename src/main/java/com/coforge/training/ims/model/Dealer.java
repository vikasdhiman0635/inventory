package com.coforge.training.ims.model;


import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Base64;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "dealer")
public class Dealer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(unique = true)
	private String email;

	@Column(name="first_name")
	private String fname;

	@Column(name="last_name")
	private String lname;

	@Column(name="password")
	private String password;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="dob")
	private Date dob;

	@Column(name="phone")
	private String phoneNo;
	
	/*The One-To-One mapping represents a single-valued association where an instance of one entity is 
	 * associated with an instance of another entity. */
	@OneToOne(mappedBy="dealer",cascade = CascadeType.ALL)
	private Address addres;
	
	public Dealer() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		Base64.Encoder encoder = Base64.getEncoder(); // encrypt password in database field
		String normalString = password;
		String encodedString = encoder.encodeToString(
		normalString.getBytes(StandardCharsets.UTF_8) );
		this.password = encodedString;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Address getAddres() {
		return addres;
	}

	public void setAddres(Address addres) {
		this.addres = addres;
	}
	
}
