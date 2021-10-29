package com.jumiainterview.jumia.exercise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jumiainterview.jumia.exercise.dto.CustomerDTO;
import com.jumiainterview.jumia.exercise.service.CustomerService;

@RestController
@RequestMapping(value = "api/")
public class CustomerController {

	@Autowired
	CustomerService service;

	@RequestMapping(value = "createcustomer", method = RequestMethod.POST)
	public String createCustomer(@RequestBody CustomerDTO customer) {
		return service.createCustomer(customer);
	}

	@RequestMapping(value = "readcustomers", method = RequestMethod.GET)
	public List<CustomerDTO> readCustomers() {
		return service.readCustomers();
	}

	@RequestMapping(value = "getcustomer", method = RequestMethod.GET)
	public CustomerDTO readCustomers(@RequestBody String phone) {
		return service.getCustomer(phone);
	}

	@RequestMapping(value = "updatecustomer", method = RequestMethod.PUT)
	public String updateStudet(@RequestBody CustomerDTO customer) {
		return service.updateCustomer(customer);
	}

	@RequestMapping(value = "deletecustomer", method = RequestMethod.DELETE)
	public String deleteCustomer(@RequestBody String phone) {
		return service.deleteCustomer(phone);
	}

}
