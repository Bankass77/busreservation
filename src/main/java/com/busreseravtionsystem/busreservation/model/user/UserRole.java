package com.busreseravtionsystem.busreservation.model.user;

public enum UserRole {
	
	
	ADMIN ("admin"), PASSENGER ("user");
	
	private String value;

	UserRole(String string) {
		value=string;
	}

	public String getValue() {
		
		return value;
	}


}
