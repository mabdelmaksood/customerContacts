package com.jumiainterview.jumia.exercise.dto;

import java.util.Objects;

import com.jumiainterview.jumia.exercise.enums.Countries;

public class CustomerDTO {

	private String name;

	private String phone;

	private Countries country;

	private Boolean isValid;

	public CustomerDTO() {
		super();
	}

	public CustomerDTO(String name, String phone, Countries country, Boolean isValid) {
		super();
		this.name = name;
		this.phone = phone;
		this.country = country;
		this.isValid = isValid;
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(country, isValid, name, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDTO other = (CustomerDTO) obj;
		return country == other.country && Objects.equals(isValid, other.isValid) && Objects.equals(name, other.name)
				&& Objects.equals(phone, other.phone);
	}

}
