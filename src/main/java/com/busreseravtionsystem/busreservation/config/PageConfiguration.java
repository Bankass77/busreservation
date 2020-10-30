package com.busreseravtionsystem.busreservation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PageConfiguration  implements WebMvcConfigurer{
	
	@Override
	public void addViewControllers(ViewControllerRegistry controllerRegistry) {
		controllerRegistry.addViewController("/home").setViewName("home");
		controllerRegistry.addViewController("/").setViewName("login");
		controllerRegistry.addViewController("/dashbord").setViewName("dashbord");
		controllerRegistry.addViewController("/login").setViewName("login");
		controllerRegistry.addViewController("/signup").setViewName("signup");
		controllerRegistry.addViewController("/profile").setViewName("profile");
		controllerRegistry.addViewController("/agency").setViewName("agency");
		controllerRegistry.addViewController("/bus").setViewName("bus");
		controllerRegistry.addViewController("/trip").setViewName("trip");
		controllerRegistry.addViewController("/logout").setViewName("logout");
		
	}

}
