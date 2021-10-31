package com.jumiainterview.jumia.exercise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jumiainterview.jumia.exercise.entity.Customer;
import com.jumiainterview.jumia.exercise.enums.Countries;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query("select max(c.id) from Customer c")
	public Integer findMaxId();

	public boolean existsByPhoneOrName(String phone, String name);

	public List<Customer> findByIsValidIsNull();

	public List<Customer> findByPhoneOrName(String phone, String name);

	public List<Customer> findByCountry(Countries country);
}
