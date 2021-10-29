package com.jumiainterview.jumia.exercise.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Transactional
	public void init() {
		List<Customer> customers = repo.findByIsValidIsNull();
		utils.validateCustomers(customers);
		repo.saveAllAndFlush(customers);

	}

	@Transactional
	public String createCustomer(CustomerDTO customerDto) {
		Customer customer = mapper.mapDtoToCustomer(customerDto);
		try {
			if (!repo.existsByPhone(customer.getPhone())) {
				customer.setId(null == repo.findMaxId() ? 0 : repo.findMaxId() + 1);
				repo.saveAndFlush(customer);
				return "Customer record created successfully.";
			} else {
				return "Customer already exists in the database.";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public CustomerDTO getCustomer(String phone) {

		if (repo.existsByPhone(phone)) {
			List<Customer> customers = repo.findByPhone(phone);
			if (customers.size() == 1) {
				return mapper.mapCustomerToDto(customers.get(0));
			}

		}
		return null;
	}

	public List<CustomerDTO> readCustomers() {
		return mapper.mapAlltoDto(repo.findAll());
	}

	@Transactional
	public String updateCustomer(CustomerDTO customerDto) {
		Customer customer = mapper.mapDtoToCustomer(customerDto);
		try {
			List<Customer> customers = repo.findByPhone(customer.getPhone());
			if (customers.size() > 1) {
				return "multiple customers found couldn't update";
			} else if (customers.isEmpty()) {
				return "Customer does not exists in the database.";
			}
			customer.setId(customers.get(0).getId());
			repo.saveAndFlush(customer);
			return "Customer Updated";

		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Transactional
	public String deleteCustomer(String phone) {
		if (repo.existsByPhone(phone)) {
			List<Customer> customers = repo.findByPhone(phone);
			repo.deleteAll(customers);
			return "Customer record deleted successfully.";
		} else {
			return "Customer does not exist";
		}
	}

}
