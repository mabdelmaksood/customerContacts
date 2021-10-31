package com.jumiainterview.jumia.exercise.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jumiainterview.jumia.exercise.dto.CustomerDTO;
import com.jumiainterview.jumia.exercise.entity.Customer;
import com.jumiainterview.jumia.exercise.mapper.Mapper;
import com.jumiainterview.jumia.exercise.repository.CustomerRepository;
import com.jumiainterview.jumia.exercise.utils.PhoneNumberUtils;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository repo;

	@Autowired
	PhoneNumberUtils utils;

	@Autowired
	Mapper mapper;

	Logger logger = LoggerFactory.getLogger(CustomerService.class);

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setDate(System.currentTimeMillis());
		return headers;
	}

	@Transactional
	public void init() {
		try {
			List<Customer> customers = repo.findByIsValidIsNull();
			utils.validateCustomers(customers);
			repo.saveAllAndFlush(customers);
		} catch (Exception e) {
			logger.error("initialization of database records failed countries might be missing for some customers", e);
		}

	}

	@Transactional
	public ResponseEntity<String> createCustomer(CustomerDTO customerDto) {
		try {
			Customer customer = mapper.mapDtoToCustomer(customerDto);
			if (!repo.existsByPhoneOrName(customer.getPhone(), customer.getName())) {
				customer.setId(null == repo.findMaxId() ? 0 : repo.findMaxId() + 1);
				utils.addCountryAndValidation(customer);
				repo.saveAndFlush(customer);
				return new ResponseEntity<>("Customer record created successfully.", getHeaders(), HttpStatus.OK);
			} else {
				logger.error("Customer already exists in the database: {}", customerDto);
				return new ResponseEntity<>("Customer already exists in the database.", getHeaders(),
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			logger.error("creating customer failed", e);
			return new ResponseEntity<>(e.getMessage(), getHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<CustomerDTO> getCustomer(CustomerDTO customerDto) {
		try {
			List<Customer> customers = repo.findByPhoneOrName(customerDto.getPhone(), customerDto.getName());
			if (customers.isEmpty()) {
				logger.error("there are  no customers with data {}", customerDto.toString());
				return new ResponseEntity<>(getHeaders(), HttpStatus.NOT_FOUND);
			}
			if (customers.size() == 1) {
				return new ResponseEntity<>(mapper.mapCustomerToDto(customers.get(0)), getHeaders(), HttpStatus.OK);
			}
			logger.error("there are {0} dublicate customers with data {1}", customers.size(), customerDto.toString());
			return new ResponseEntity<>(getHeaders(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("Failed in getting customer " + customerDto.toString(), e);
			return new ResponseEntity<>(getHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<List<CustomerDTO>> readCustomers() {
		try {
			return new ResponseEntity<>(mapper.mapAlltoDto(repo.findAll()), getHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Failed in reading customers", e);
			return new ResponseEntity<>(getHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<String> updateCustomer(CustomerDTO customerDto) {
		try {
			List<Customer> customers = repo.findByPhoneOrName(customerDto.getPhone(), customerDto.getName());
			if (customers.size() > 1) {
				return new ResponseEntity<>("multiple customers found couldn't update", getHeaders(),
						HttpStatus.BAD_REQUEST);
			} else if (customers.isEmpty()) {
				return new ResponseEntity<>("Customer does not exists in the database.", getHeaders(),
						HttpStatus.BAD_REQUEST);
			}
			Customer customer = mapper.mapDtoToCustomer(customerDto);
			customer.setId(customers.get(0).getId());
			utils.addCountryAndValidation(customer);
			repo.saveAndFlush(customer);
			return new ResponseEntity<>("Customer Updated", getHeaders(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), getHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<String> deleteCustomer(CustomerDTO customerDto) {
		try {
			List<Customer> customers = repo.findByPhoneOrName(customerDto.getPhone(), customerDto.getName());
			if (customers.isEmpty()) {
				return new ResponseEntity<>("Customer does not exist", getHeaders(), HttpStatus.BAD_REQUEST);
			}
			repo.deleteAll(customers);
			return new ResponseEntity<>("Customer record deleted successfully.", getHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), getHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
