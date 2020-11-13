package com.busreseravtionsystem.busreservation.config;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class BSRConfiguration {

	@Bean
	public ModelMapper modelMapper() {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true)
				.setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
		return modelMapper;

	}

	@Bean
	public Docket swaggerBSRApi() {

		return new Docket(DocumentationType.SWAGGER_2).groupName("BRS").select()
				.apis(RequestHandlerSelectors.basePackage("com.busreseravtionsystem.busreservation.controller.v1.api"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo()).securitySchemes(Arrays.asList(apiKey()));
	}

	@Bean
	public Docket swaggerUserApi() {

		return new Docket(DocumentationType.SWAGGER_2).groupName("user").select()
				.apis(RequestHandlerSelectors.basePackage("com.busreseravtionsystem.busreservation.config"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo()).securitySchemes(Arrays.asList(apiKey()));
	}

	private ApiKey apiKey() {

		return new ApiKey("apiKey", "Authorization", "header");
	}

	private ApiInfo apiInfo() {

		return new ApiInfoBuilder().title("Bus Reservation System- REST APIs")
				.description("spring boot starter kit application").termsOfServiceUrl("")
				.contact(new Contact("Guindo Amadou", null, "amguindo77@gmail.com"))
				.license("Apache Licence Version 2.0").licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.version("1.0").build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;

	}

}
