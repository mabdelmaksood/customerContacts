package com.jumiainterview.jumia.exercise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jumiainterview.jumia.exercise.dto.CustomerDTO;
import com.jumiainterview.jumia.exercise.service.CustomerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "api/")
public class CustomerController {

	@Autowired
	CustomerService service;

	@RequestMapping(value = "createcustomer", method = RequestMethod.POST)
	public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customer) {
		return service.createCustomer(customer);
	}

	@RequestMapping(value = "readcustomers", method = RequestMethod.GET)
	public ResponseEntity<List<CustomerDTO>> readCustomers() {
		return service.readCustomers();
	}

	@RequestMapping(value = "getcustomer", method = RequestMethod.GET)
	public ResponseEntity<CustomerDTO> readCustomers(@RequestBody CustomerDTO customer) {
		return service.getCustomer(customer);
	}

	@RequestMapping(value = "updatecustomer", method = RequestMethod.PUT)
	public ResponseEntity<String> updateStudet(@RequestBody CustomerDTO customer) {
		return service.updateCustomer(customer);
	}

	@RequestMapping(value = "deletecustomer", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCustomer(@RequestBody CustomerDTO customer) {
		return service.deleteCustomer(customer);
	}

}
