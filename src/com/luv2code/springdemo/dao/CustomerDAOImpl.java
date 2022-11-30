package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

// Repository used by Spring in DAO Implementation classes. Will automatically register it for scanning.
// Spring also translates JDBC exceptions.
@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	// Autowired tells Spring to "inject" (i.e. instantiate) the session factory into this object (Dependency Injection)
	// Spring looks in the Spring config file for a Bean Id = sessionFactory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		// Get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Query
		// Hibernate Query Language (HQL) can take actual DB table and column names OR the class defined names!
		/*Query<Customer> theQuery = session.createQuery(
				"from Customer where spriden_change_ind is null and rownum <= 10", 
				Customer.class);*/
		
		Query<Customer> theQuery = session.createQuery(
				"from Customer where spriden_change_ind is null and spriden_pidm > 3976720 order by lastName", 
				Customer.class);
		
		// Get Results
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

	/**
	 * Save or Update Customer
	 */
	@Override
	public void saveCustomer(Customer theCustomer) {
		Session session = sessionFactory.getCurrentSession();
		
		// In Hibernate:
		// session.save will INSERT 
		// session.update will UPDATE existing record
		// session.saveOrUpdate will INSERT if no PK and UPDATE if PK exists.
		session.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		Session session = sessionFactory.getCurrentSession();
		
		// Get Customer using PK
		Customer customer = session.get(Customer.class, theId);
		
		return customer;
	}

	@Override
	public void deleteCustomer(int theId) {
		Session session = sessionFactory.getCurrentSession();
		
		// Get Customer using PK
		//Customer customer = session.get(Customer.class, theId);
		
		// Delete the Customer from the DB
		//session.delete(customer);
		
		// Alternate method to delete using a named parameter (customerId in this case)
		Query<Customer> theQuery = session.createQuery("delete from Customer where id = :customerId");
		
		// Bind the parameter to the query. Parameter name must match name from query object above.
		theQuery.setParameter("customerId", theId);
		
		// Run query
		theQuery.executeUpdate();	
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		Session session = sessionFactory.getCurrentSession();
		
		Query theQuery = null;

		//
		// only search by name if theSearchName is not empty
		//
		if (theSearchName != null && theSearchName.trim().length() > 0) {
			// search for firstName or lastName ... case insensitive
			theQuery = session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName and spriden_change_ind is null", Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		}
		else {
			// theSearchName is empty ... so just get all customers
			theQuery = session.createQuery(
					"from Customer where spriden_change_ind is null and spriden_pidm > 3976720 order by lastName", 
					Customer.class);            
		}
		
		// Get Results
		List<Customer> customers = theQuery.getResultList();

		return customers;
	}



}
