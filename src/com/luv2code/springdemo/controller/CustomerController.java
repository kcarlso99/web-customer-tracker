package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

/**
 * This is the main Controller class for this application. An application can have many Controller classes.
 * 
 * The Controller annotation tells Spring that this object handles HTTP/S requests.
 * 
 * The CustomerController handles all URL requests that begin with /customer, as defined by the RequestMapping annotation.
 * 
 * This control object handles:
 *  1) Displaying a list of Customers 
 *  2) Updating a Customer 
 *  3) Deleting a Customer
 *  4) Inserting a new Customer
 *  5) Searching for a Customer
 * 
 * @author kcarlso1
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// Inject Service into controller.
	// Spring finds the Service b/c of the Service annotation on the object.
	// Spring also finds DAO objects b/c of the Repository annotation
	// Autowired means that this object is instantiated automatically by Spring and available for use.
	@Autowired
	private CustomerService customerService;
	
	/**
	 * Method called when the /customer/list URL is accessed.
	 * This method retrieves a list of Customers from the DB and adds the List to the Model as a ModelAttribute.
	 * The Model Attribute "customers" object is then available to the returned JSP page (list-customers)
	 * 
	 * Can use GetMapping or RequestMapping. 
	 * GetMapping is just more specific and instructs Spring to only respond to Get requests for this method.
	 * 
	 * @param theModel
	 * @return
	 */
	@GetMapping("/list")
	public String listCustomers(Model theModel) {

		// get customers from the service
		List<Customer> customers = customerService.getCustomers();

		// add customers to the model
		theModel.addAttribute("customers", customers);
		
		// Return the name of the JSP page
		return "list-customers";
	}
	
	/**
	 * Method called when the Add Customer button is clicked on the "list-customers" jsp page.
	 * This method simply creates a Customer object and binds it to the Model object that will
	 * be used by the returned jsp page. 
	 * @param theModel
	 * @return
	 */
	@RequestMapping("/showFormForAdd") 
	public String showFormForAdd(Model theModel) {
		
		// Create object to hold new Customer
		Customer customer = new Customer();
		
		// Bind customer object to the model
		theModel.addAttribute("customer", customer);
		
		// Spring mappings look for the return name jsp file (i.e customer-form.jsp)
		return "customer-form";
	}
	
	/**
	 * Method called when the "Save" button is clicked on the "customer-form" jsp page.
	 * Since the button is inside a Form tag, this is a Post request. Thus use PostMapping.
	 * The Spring Form tag in the JSP includes a ModelAttribute called customer. This is passed as in input parameter.
	 * @param theCustomer
	 * @return
	 */
	@PostMapping("/saveCustomer") 
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		// Save the customer using Service
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	/**
	 * Method called when the "Update" link is clicked in the "list-customers" jsp page.
	 * The CustomerId is passed as a URL parameter, so use GetMapping to handle the request.
	 * @param theId
	 * @param theModel
	 * @return
	 */
	@GetMapping("/showUpdateForm")
	public String showUpdateForm(@RequestParam("customerId") int theId, Model theModel) {
		// Get the customer from Service using Id
		Customer customer = customerService.getCustomer(theId);
		
		// Set the customer as Model Attribute to pre-populate form
		theModel.addAttribute("customer", customer);
		
		// Send to form
		return "customer-form";
	}
	
	/**
	 * Method called when the "Delete" link is clicked on the "list-customer" jsp page.
	 * Customer ID is passed as a URL parameter, so use GetMapping to handle the request.
	 * @param theId
	 * @param theModel
	 * @return
	 */
	@GetMapping("/delete") 
	public String deleteCustomer(@RequestParam("customerId") int theId, Model theModel) {
		
		// Save the customer using Service
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
	
	/**
	 * Method called when the Search button is clicked on the "list-customers" jsp page
	 * 
	 * The button is inside a Form tag, so use PostMapping to handle request.
	 * The Form includes an Input tag called "theSearchName" which is passed into the method as a RequestParam.
	 * 
	 * Note: The query goes against Spriden and searches for First or Last Name, so can take awhile to run.
	 * 
	 * @param theSearchName
	 * @param theModel
	 * @return
	 */
	@PostMapping("/search") 
	public String searchCustomer(@RequestParam("theSearchName") String theSearchName, Model theModel) {
		
		// Search for customers with the input SearchName
		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
		
		// Add the customers as an attribute to the model
		theModel.addAttribute("customers", theCustomers);
		
		// Return the JSP page
		return "list-customers"; 
	}
	

}
