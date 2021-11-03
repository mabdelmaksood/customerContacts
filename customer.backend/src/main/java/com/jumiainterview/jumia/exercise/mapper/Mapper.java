package com.jumiainterview.customer.backend.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.jumiainterview.customer.backend.dto.CustomerDTO;
import com.jumiainterview.customer.backend.entity.Customer;

@Component
public class Mapper {
	private DozerBeanMapper mapper;

	public Mapper() {
		mapper = new DozerBeanMapper();
	}

	public CustomerDTO mapCustomerToDto(Customer customer) {
		return mapper.map(customer, CustomerDTO.class);
	}

	public Customer mapDtoToCustomer(CustomerDTO customerDto) {
		return mapper.map(customerDto, Customer.class);

	}

	public List<CustomerDTO> mapAlltoDto(List<Customer> customers) {
		List<CustomerDTO> customerDTOs = new ArrayList<>();
		for (Customer customer : customers) {
			customerDTOs.add(mapCustomerToDto(customer));
		}
		return customerDTOs;
	}
}
