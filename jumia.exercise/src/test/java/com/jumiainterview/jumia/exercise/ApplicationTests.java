package com.jumiainterview.jumia.exercise;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.jumiainterview.jumia.exercise.controller.CustomerController;
import com.jumiainterview.jumia.exercise.dto.CustomerDTO;
import com.jumiainterview.jumia.exercise.entity.Customer;
import com.jumiainterview.jumia.exercise.enums.Countries;
import com.jumiainterview.jumia.exercise.mapper.Mapper;
import com.jumiainterview.jumia.exercise.repository.CustomerRepository;
import com.jumiainterview.jumia.exercise.service.CustomerService;
import com.jumiainterview.jumia.exercise.utils.PhoneNumberUtils;

@SpringBootTest
class ApplicationTests {
	@Autowired
	CustomerController controller;

	@Autowired
	CustomerService service;

	@Autowired
	Mapper mapper;

	@Autowired
	PhoneNumberUtils utils;

	@MockBean
	CustomerRepository repo;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	void testCreateCustomerSuccess() {
		Customer customer = new Customer(1, "Dominique mekontchou", "(237) 691816558", Countries.Cameroon, true);
		when(repo.existsByPhoneOrName(customer.getPhone(), customer.getName())).thenReturn(false);
		when(repo.findMaxId()).thenReturn(0);
		when(repo.saveAndFlush(customer)).thenReturn(customer);
		assertEquals(HttpStatus.OK, service.createCustomer(mapper.mapCustomerToDto(customer)).getStatusCode());
	}

	@Test
	void testCreateCustomerfailure() {
		Customer customer = new Customer(2, "Dominique mekontchou", "(237) 691816558", Countries.Cameroon, true);
		when(repo.existsByPhoneOrName(customer.getPhone(), customer.getName())).thenReturn(true);
		assertEquals(HttpStatus.BAD_REQUEST, service.createCustomer(mapper.mapCustomerToDto(customer)).getStatusCode());
	}

	@Test
	void testGetCustomersuccess() {
		Customer customer = new Customer(1, "Dominique mekontchou", "(237) 691816558", Countries.Cameroon, true);
		CustomerDTO customerDto = new CustomerDTO(1, "Dominique mekontchou", "(237) 691816558", null, null);

		when(repo.findById(customerDto.getId())).thenReturn(Optional.of(customer));
		assertEquals(HttpStatus.OK, service.getCustomer(customerDto).getStatusCode());
	}

	@Test
	void testGetCustomerfailureNotFound() {
		CustomerDTO customerDto = new CustomerDTO(1, "Daniel Makori", "(256) 714660221", null, null);

		when(repo.findById(customerDto.getId())).thenReturn(Optional.empty());
		assertEquals(HttpStatus.NOT_FOUND, service.getCustomer(customerDto).getStatusCode());
	}

	@Test
	void testReadCustomers() {
		List<Customer> customers = Stream
				.of(new Customer(1, "Daniel Makori", "(237) 691816558", Countries.Cameroon, true),
						new Customer(1, "Dominique mekontchou", "(256) 714660221", Countries.Uganda, true))
				.collect(Collectors.toList());
		when(repo.findAll()).thenReturn(customers);
		assertEquals(mapper.mapAlltoDto(customers), service.readCustomers().getBody());
	}

	@Test
	void testdeleteCustomerSuccess() {
		Optional<Customer> customers = Optional
				.of(new Customer(1, "Daniel Makori", "(237) 691816558", Countries.Cameroon, true));
		Integer customerId = 1;

		when(repo.findById(customerId)).thenReturn(customers);

		assertEquals(HttpStatus.OK, service.deleteCustomer(1).getStatusCode());

	}

	@Test
	void testdeleteCustomerFailure() {
		Integer customerId = 1;

		when(repo.findById(customerId)).thenReturn(Optional.empty());

		assertEquals(HttpStatus.BAD_REQUEST, service.deleteCustomer(customerId).getStatusCode());

	}

	@Test
	void testUpdateCustomerSuccess() {
		CustomerDTO customerDto = new CustomerDTO(21, "Daniel Makori", "(237) 691816558", Countries.Cameroon, true);

		Customer customer = new Customer(21, "Daniel Makori", "(256) 714660221", Countries.Uganda, true);
		Customer newCustomer = mapper.mapDtoToCustomer(customerDto);

		when(repo.findById(customerDto.getId())).thenReturn(Optional.of(customer));
		when(repo.saveAndFlush(newCustomer)).thenReturn(newCustomer);

		assertEquals(HttpStatus.OK, service.updateCustomer(customerDto).getStatusCode());

	}

	@Test
	void testUpdateCustomerFailureNotFound() {
		CustomerDTO customerDto = new CustomerDTO(1, "Daniel Makori", "(237) 691816558", Countries.Cameroon, true);

		when(repo.findById(customerDto.getId())).thenReturn(Optional.empty());
		assertEquals(HttpStatus.BAD_REQUEST, service.updateCustomer(customerDto).getStatusCode());

	}

	@Test
	void testAddCountryAndValidation() {
		Customer customer1 = new Customer(1, "Daniel Makori", "(256) 714660221", Countries.Uganda, true);
		Customer customer2 = new Customer(1, "Daniel Makori", "(256) 714660221", null, null);
		Customer customer3 = new Customer(17, "Chouf Malo", "(212) 691933626", Countries.Morocco, true);
		Customer customer4 = new Customer(17, "Chouf Malo", "(212) 691933626", null, null);
		Customer customer5 = new Customer(55, "invalid phone", "(0) 1111", null, false);
		Customer customer6 = new Customer(55, "invalid phone", "(0) 1111", null, null);
		utils.addCountryAndValidation(customer2);
		utils.addCountryAndValidation(customer4);
		utils.addCountryAndValidation(customer6);

		assertEquals(customer1, customer2);
		assertEquals(customer3, customer4);
		assertEquals(customer5, customer6);
	}

}
