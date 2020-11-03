package com.busreseravtionsystem.busreservation.security;

public interface SecurityConstants {
	
	String SECRET="SecretKeyToGenJWTs";
	
	String TOKEN_PREFIX="Bearer";
	
	String HEADER_STRING="Authorization";
	String SIGN_UP_URL="/user/signu-up";
	long EXPIRATION_TIMES=864_000_000; //10 days.

}
