package com.busreseravtionsystem.busreservation.model.user;

public enum UserRole {

	ADMIN("ADMIN"), PASSENGER("PASSENGER");

	private String value;

	UserRole(String string) {
		value = string;
	}

	public String getValue() {

		return value;
	}

}
