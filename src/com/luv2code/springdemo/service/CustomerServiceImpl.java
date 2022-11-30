package com.luv2code.springdemo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	// Inject the CustomerDAO since service depends on it.
	@Autowired 
	private CustomerDAO customerDao;

	// Best practice to define transactions at the Service layer
	// Transactional is a Spring annotation that automagically opens, commits, and closes SessionFactory transactions.
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDao.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		Date date = new java.util.Date();
		theCustomer.setCreateDate(date);
		customerDao.saveCustomer(theCustomer);	
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		return customerDao.getCustomer(theId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		customerDao.deleteCustomer(theId);
	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String theSearchName) {
		return customerDao.searchCustomers(theSearchName);
	}

}
