package com.jumiainterview.customer.backend.entity;

import java.util.Objects;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.jumiainterview.customer.backend.enums.Countries;

@Entity
@Cacheable(false)
public class Customer {
	@Id
	private Integer id;

	private String name;

	private String phone;

	@Enumerated(EnumType.STRING)
	private Countries country;

	@Column(name = "is_valid")
	private Boolean isValid;

	public Customer() {
	}

	public Customer(Integer id, String name, String phone, Countries country, Boolean isValid) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.country = country;
		this.isValid = isValid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		return "Customer [id=" + id + ", name=" + name + ", phone=" + phone + ", country=" + country + ", isValid="
				+ isValid + "]";
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
		Customer other = (Customer) obj;
		return country == other.country && Objects.equals(isValid, other.isValid) && Objects.equals(name, other.name)
				&& Objects.equals(phone, other.phone);
	}

}
