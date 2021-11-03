package com.jumiainterview.customer.backend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jumiainterview.customer.backend.dto.CustomerDTO;
import com.jumiainterview.customer.backend.entity.Customer;
import com.jumiainterview.customer.backend.enums.Countries;
import com.jumiainterview.customer.backend.mapper.Mapper;
import com.jumiainterview.customer.backend.repository.CustomerRepository;
import com.jumiainterview.customer.backend.utils.PhoneNumberUtils;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository repo;

	@Autowired
	PhoneNumberUtils utils;

	@Autowired
	Mapper mapper;

	Logger logger = LoggerFactory.getLogger(CustomerService.class);

	private static String IN_VALID = "In Valid";

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
	public ResponseEntity<CustomerDTO> createCustomer(CustomerDTO customerDto) {
		try {
			if (!repo.existsByPhoneOrName(customerDto.getPhone(), customerDto.getName())) {
				Customer customer = mapper.mapDtoToCustomer(customerDto);
				customer.setId(null == repo.findMaxId() ? 0 : repo.findMaxId() + 1);
				utils.addCountryAndValidation(customer);
				CustomerDTO savedCustomer = mapper.mapCustomerToDto(repo.saveAndFlush(customer));
				return new ResponseEntity<>(savedCustomer, getHeaders(), HttpStatus.OK);
			} else {
				logger.error("Customer already exists in the database: {}", customerDto);
				return new ResponseEntity<>(getHeaders(), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			logger.error("creating customer failed", e);
			return new ResponseEntity<>(getHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<CustomerDTO> getCustomer(CustomerDTO customerDto) {
		try {
			Optional<Customer> customer = repo.findById(customerDto.getId());
			if (customer.isEmpty()) {
				logger.error("there are  no customers with data {}", customerDto.toString());
				return new ResponseEntity<>(getHeaders(), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(mapper.mapCustomerToDto(customer.get()), getHeaders(), HttpStatus.OK);

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

	public ResponseEntity<List<CustomerDTO>> readCustomersByCountry(String[] countries) {
		try {
			boolean inValid = ArrayUtils.contains(countries, IN_VALID);
			if (inValid) {
				countries = ArrayUtils.remove(countries, ArrayUtils.indexOf(countries, IN_VALID));
			}
			List<Customer> customers;
			if (countries.length == 0) {
				customers = repo.findByIsValid(!inValid);
			} else {
				Countries[] countriesEnum = new Countries[countries.length];
				for (int i = 0; i < countries.length; i++) {
					countriesEnum[i] = Countries.valueOf(countries[i]);
				}
				if (inValid) {
					customers = repo.findByIsValidOrCountryIn(!inValid, countriesEnum);
				} else {
					customers = repo.findByCountryIn(countriesEnum);
				}
			}
			return new ResponseEntity<>(mapper.mapAlltoDto(customers), getHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Failed in reading customers", e);
			return new ResponseEntity<>(getHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<CustomerDTO>> getValidCustomers(Boolean isValid) {
		try {
			return new ResponseEntity<>(mapper.mapAlltoDto(repo.findByIsValid(isValid)), getHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Failed in getting valid customers", e);
			return new ResponseEntity<>(getHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<String> updateCustomer(CustomerDTO customerDto) {
		try {
			Optional<Customer> customer = repo.findById(customerDto.getId());
			if (!customer.isEmpty()) {
				Customer newCustomer = mapper.mapDtoToCustomer(customerDto);
				newCustomer.setId(customer.get().getId());
				utils.addCountryAndValidation(newCustomer);
				repo.saveAndFlush(newCustomer);
				return new ResponseEntity<>("Customer Updated", getHeaders(), HttpStatus.OK);

			}
			return new ResponseEntity<>("Customer does not exists in the database.", getHeaders(),
					HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), getHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<String> deleteCustomer(Integer id) {
		try {
			Optional<Customer> customer = repo.findById(id);
			if (customer.isEmpty()) {
				return new ResponseEntity<>("Customer does not exist", getHeaders(), HttpStatus.BAD_REQUEST);
			}
			repo.delete(customer.get());
			return new ResponseEntity<>("Customer record deleted successfully.", getHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), getHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<Countries[]> getCountries() {
		return new ResponseEntity<>(Countries.values(), getHeaders(), HttpStatus.OK);
	}

}
