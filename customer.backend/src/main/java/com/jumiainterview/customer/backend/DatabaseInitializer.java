package com.jumiainterview.customer.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jumiainterview.customer.backend.service.CustomerService;

@Component
public class DatabaseInitializer implements CommandLineRunner {

	@Autowired
	CustomerService service;

	@Override
	public void run(String... args) throws Exception {
		service.init();
	}

}
