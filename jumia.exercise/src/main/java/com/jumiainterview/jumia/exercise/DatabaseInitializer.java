package com.jumiainterview.jumia.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jumiainterview.jumia.exercise.service.CustomerService;

@Component
public class DatabaseInitializer implements CommandLineRunner {

	@Autowired
	CustomerService service;

	@Override
	public void run(String... args) throws Exception {
		service.init();
	}

}
