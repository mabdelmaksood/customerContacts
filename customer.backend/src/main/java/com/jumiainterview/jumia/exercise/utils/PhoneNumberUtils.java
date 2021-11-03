package com.jumiainterview.customer.backend.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.jumiainterview.customer.backend.entity.Customer;
import com.jumiainterview.customer.backend.enums.Countries;

@Component
public class PhoneNumberUtils {

	public void addCountryAndValidation(Customer cust) {

		for (Countries country : Countries.values()) {
			Pattern p = Pattern.compile(country.getValue());
			Matcher m = p.matcher(cust.getPhone());
			if (m.matches()) {
				cust.setCountry(country);
				cust.setIsValid(true);
				return;
			}
		}
		cust.setIsValid(false);
		return;

	}

	public void validateCustomers(List<Customer> customers) {
		for (Customer customer : customers) {
			addCountryAndValidation(customer);
		}
	}

}
