package com.luv2code.springdemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.luv2code.springdemo.entity.Customer;

/**
 * A Service is the layer between the Controller and the DAO that contains the business logic.
 * The Service can get and process data from several DAO objects.
 * 
 * Best practice is to have Service layer between Controller and DAO. So:
 * 
 * Controller <-----> Service <-----> DAO <-----> Database
 * 
 * Just like Repository, Service is a Component so Springs instantiates it via Component scanning.
 * 
 * @author kcarlso1
 *
 */
@Service
public interface CustomerService {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomers(String theSearchName);

}
