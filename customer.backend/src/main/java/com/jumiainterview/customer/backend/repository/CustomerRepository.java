package com.jumiainterview.customer.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jumiainterview.customer.backend.entity.Customer;
import com.jumiainterview.customer.backend.enums.Countries;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query("select max(c.id) from Customer c")
	public Integer findMaxId();

	public boolean existsByPhoneOrName(String phone, String name);

	public List<Customer> findByIsValidIsNull();

	public List<Customer> findByPhoneOrName(String phone, String name);

	public List<Customer> findByCountry(Countries country);

	public List<Customer> findByCountryIn(Countries[] country);

	public List<Customer> findByIsValidOrCountryIn(Boolean isValid, Countries[] country);

	public List<Customer> findByIsValid(Boolean isValid);

}
