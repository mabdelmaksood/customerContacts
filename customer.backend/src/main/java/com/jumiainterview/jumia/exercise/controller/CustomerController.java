package com.jumiainterview.customer.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jumiainterview.customer.backend.dto.CustomerDTO;
import com.jumiainterview.customer.backend.enums.Countries;
import com.jumiainterview.customer.backend.service.CustomerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "api/")
public class CustomerController {

	@Autowired
	CustomerService service;

	@RequestMapping(value = "createcustomer", method = RequestMethod.POST)
	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customer) {
		return service.createCustomer(customer);
	}

	@RequestMapping(value = "countries", method = RequestMethod.GET)
	public ResponseEntity<Countries[]> getCountries() {
		return service.getCountries();
	}

	@RequestMapping(value = "readcustomers", method = RequestMethod.GET)
	public ResponseEntity<List<CustomerDTO>> readCustomers() {
		return service.readCustomers();
	}

	@RequestMapping(value = "customersbycountry", method = RequestMethod.POST)
	public ResponseEntity<List<CustomerDTO>> customersByCountry(@RequestBody String[] selectedCountries) {
		if (selectedCountries.length > 0) {
			return service.readCustomersByCountry(selectedCountries);
		}
		return new ResponseEntity<>(new ArrayList<CustomerDTO>(), HttpStatus.OK);
	}

	@RequestMapping(value = "customersbystate", method = RequestMethod.GET)
	public ResponseEntity<List<CustomerDTO>> getValidCustomers(@RequestBody Boolean isValid) {
		return service.getValidCustomers(isValid);
	}

	@RequestMapping(value = "getcustomer", method = RequestMethod.GET)
	public ResponseEntity<CustomerDTO> readCustomers(@RequestBody CustomerDTO customer) {
		return service.getCustomer(customer);
	}

	@RequestMapping(value = "updatecustomer", method = RequestMethod.PUT)
	public ResponseEntity<String> updateStudet(@RequestBody CustomerDTO customer) {
		return service.updateCustomer(customer);
	}

	@RequestMapping(value = "deletecustomer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
		return service.deleteCustomer(id);
	}

}
