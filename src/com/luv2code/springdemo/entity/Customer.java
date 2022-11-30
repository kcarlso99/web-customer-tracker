package com.luv2code.springdemo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="SATURN.SPRIDEN")
public class Customer {
	
	// The GenericGenerator name can be anything. Strategy=increment tells Hibernate to find the max value for this column
	// and increment by one. Useful for Oracle databases that don't use sequences.
	@Id
	@GenericGenerator(name="nextId" , strategy="increment")
	@GeneratedValue(generator="nextId")
	@Column(name="SPRIDEN_PIDM")
	private int id;
	
	@Column(name="SPRIDEN_FIRST_NAME")
	private String firstName;
	
	@Column(name="SPRIDEN_LAST_NAME")
	private String lastName;
	
	@Column(name="SPRIDEN_ID")
	private String uin;
	
	@Column(name="SPRIDEN_ACTIVITY_DATE")
	@Temporal(TemporalType.DATE) 
	private Date createDate;
	
	@GenericGenerator(name="nextSurrogateId" , strategy="increment")
	@GeneratedValue(generator="nextSurrogateId")
	@Column(name="SPRIDEN_SURROGATE_ID")
	private int surrogateId;
	
	@Column(name="SPRIDEN_VERSION")
	private int version = 1;		
	
	public Customer() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUin() {
		return uin;
	}

	public void setUin(String uin) {
		this.uin = uin;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", uin=" + uin + "]";
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
