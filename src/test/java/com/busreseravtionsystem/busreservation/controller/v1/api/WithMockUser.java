package com.busreseravtionsystem.busreservation.controller.v1.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public  @interface WithMockUser {
	 String value() default "admin1";

	    String username() default "";

	    String[] roles() default {"ADMIN"};

	    String password() default "admin1";
}
