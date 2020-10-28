package com.busreseravtionsystem.busreservation.exception;

public enum ExceptionType {
	
	ENTITY_NOT_FOUND("not.found"),
	DUPLICATE_ENTITY("duplicate entity"),
	ENTITY_EXCEPTION("Entity exception");
	
	
	String value;

	
	private ExceptionType(String value) {
		this.value = value;
	}



	public String getValue() {
		return this.value;
	}
	

}
