package com.jumiainterview.jumia.exercise;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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

@SpringBootTest
class ApplicationTests {
	@Autowired
	CustomerController controller;

	@Autowired
	CustomerService service;

	@Autowired
	Mapper mapper;
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
		List<Customer> customers = new ArrayList<>();
		customers.add(customer);
		CustomerDTO customerDto = new CustomerDTO("Dominique mekontchou", "(237) 691816558", null, null);

		when(repo.findByPhoneOrName(customerDto.getPhone(), customerDto.getName())).thenReturn(customers);
		assertEquals(HttpStatus.OK, service.getCustomer(customerDto).getStatusCode());
	}

	@Test
	void testGetCustomerfailureNotFound() {
		List<Customer> customers = new ArrayList<>();
		CustomerDTO customerDto = new CustomerDTO("Daniel Makori", "(256) 714660221", null, null);

		when(repo.findByPhoneOrName(customerDto.getPhone(), customerDto.getName())).thenReturn(customers);
		assertEquals(HttpStatus.NOT_FOUND, service.getCustomer(customerDto).getStatusCode());
	}

	@Test
	void testGetCustomerFailureMultihits() {
		List<Customer> customers = Stream
				.of(new Customer(1, "Daniel Makori", "(237) 691816558", Countries.Cameroon, true),
						new Customer(1, "Dominique mekontchou", "(256) 714660221", Countries.Uganda, true))
				.collect(Collectors.toList());
		CustomerDTO customerDto = new CustomerDTO("Dominique mekontchou", "(237) 691816558", null, null);

		when(repo.findByPhoneOrName(customerDto.getPhone(), customerDto.getName())).thenReturn(customers);
		assertEquals(HttpStatus.BAD_REQUEST, service.getCustomer(customerDto).getStatusCode());
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
		List<Customer> customers = Stream
				.of(new Customer(1, "Daniel Makori", "(237) 691816558", Countries.Cameroon, true))
				.collect(Collectors.toList());
		CustomerDTO customerDto = new CustomerDTO("Daniel Makori", "(237) 691816558", null, null);

		when(repo.findByPhoneOrName(customerDto.getPhone(), customerDto.getName())).thenReturn(customers);

		assertEquals(HttpStatus.OK, service.deleteCustomer(customerDto).getStatusCode());

	}

	@Test
	void testdeleteCustomerFailure() {
		CustomerDTO customerDto = new CustomerDTO("Daniel Makori", "(237) 691816558", null, null);

		when(repo.findByPhoneOrName(customerDto.getPhone(), customerDto.getName())).thenReturn(new ArrayList<>());

		assertEquals(HttpStatus.BAD_REQUEST, service.deleteCustomer(customerDto).getStatusCode());

	}

	@Test
	void testUpdateCustomerSuccess() {
		CustomerDTO customerDto = new CustomerDTO("Daniel Makori", "(237) 691816558", Countries.Cameroon, true);

		Customer customer = new Customer(21, "Daniel Makori", "(256) 714660221", Countries.Uganda, true);
		Customer newCustomer = mapper.mapDtoToCustomer(customerDto);
		List<Customer> customers = new ArrayList<>();
		customers.add(customer);

		when(repo.findByPhoneOrName(customerDto.getPhone(), customerDto.getName())).thenReturn(customers);
		when(repo.saveAndFlush(newCustomer)).thenReturn(newCustomer);

		assertEquals(HttpStatus.OK, service.updateCustomer(customerDto).getStatusCode());

	}

	@Test
	void testUpdateCustomerFailureMultiHit() {
		CustomerDTO customerDto = new CustomerDTO("Daniel Makori", "(237) 691816558", Countries.Cameroon, true);
		List<Customer> customers = Stream
				.of(new Customer(1, "Daniel Makori", "(256) 714660221", Countries.Uganda, true),
						new Customer(1, "Dominique mekontchou", "(237) 691816558", Countries.Cameroon, true))
				.collect(Collectors.toList());

		when(repo.findByPhoneOrName(customerDto.getPhone(), customerDto.getName())).thenReturn(customers);

		assertEquals(HttpStatus.BAD_REQUEST, service.updateCustomer(customerDto).getStatusCode());

	}

	@Test
	void testUpdateCustomerFailureNotFound() {
		CustomerDTO customerDto = new CustomerDTO("Daniel Makori", "(237) 691816558", Countries.Cameroon, true);
		List<Customer> customers = new ArrayList<>();

		when(repo.findByPhoneOrName(customerDto.getPhone(), customerDto.getName())).thenReturn(customers);
		assertEquals(HttpStatus.BAD_REQUEST, service.updateCustomer(customerDto).getStatusCode());

	}

}
