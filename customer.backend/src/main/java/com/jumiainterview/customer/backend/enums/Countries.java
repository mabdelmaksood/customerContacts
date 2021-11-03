package com.jumiainterview.customer.backend.enums;

public enum Countries {

	Cameroon("\\(237\\)\\ ?[2368]\\d{7,8}$"), Ethiopia("\\(251\\)\\ ?[1-59]\\d{8}$"),
	Morocco("\\(212\\)\\ ?[5-9]\\d{8}$"), Mozambique("\\(258\\)\\ ?[28]\\d{7,8}$"), Uganda("\\(256\\)\\ ?\\d{9}$");

	private String value;

	private Countries(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
