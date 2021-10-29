package com.jumiainterview.jumia.exercise.dto;

import com.jumiainterview.jumia.exercise.enums.Countries;

public class CustomerDTO {

	private String name;

	private String phone;

	private Countries country;

	private Boolean isValid;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Countries getCountry() {
		return country;
	}

	public void setCountry(Countries country) {
		this.country = country;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	@Override
	public String toString() {
		return "CustomerDTO [name=" + name + ", phone=" + phone + ", country=" + country + ", isValid=" + isValid + "]";
	}

}
