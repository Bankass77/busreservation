package com.busreseravtionsystem.busreservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

@Configuration
public class PageConfiguration implements WebMvcConfigurer {
	

	@Override
	public void addViewControllers(ViewControllerRegistry controllerRegistry) {
		controllerRegistry.addViewController("/home").setViewName("home");
		controllerRegistry.addViewController("/").setViewName("login");
		controllerRegistry.addViewController("/dashboard").setViewName("dashboard");
		controllerRegistry.addViewController("/login").setViewName("login");
		controllerRegistry.addViewController("/signup").setViewName("signup");
		controllerRegistry.addViewController("/profile").setViewName("profile");
		controllerRegistry.addViewController("/agency").setViewName("agency");
		controllerRegistry.addViewController("/bus").setViewName("bus");
		controllerRegistry.addViewController("/trip").setViewName("trip");
		controllerRegistry.addViewController("/logout").setViewName("logout");

	}
	
	  @Bean
	    @Description("Thymeleaf template resolver serving HTML 5")
	    public ClassLoaderTemplateResolver templateResolver() {
	        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	        templateResolver.setPrefix("templates/");
	        templateResolver.setCacheable(false);
	        templateResolver.setSuffix(".html");
	        templateResolver.setTemplateMode("HTML5");
	        templateResolver.setCharacterEncoding("UTF-8");
	        return templateResolver;
	    }

		/*
		 * @Bean public ServletContextTemplateResolver
		 * servletContextTemplateResolverResolver() { final
		 * ServletContextTemplateResolver resolver = new
		 * ServletContextTemplateResolver(); resolver.setPrefix("/templates/");
		 * resolver.setSuffix(".html"); resolver.setTemplateMode("HTML5");
		 * resolver.setCacheable(false); resolver.setCharacterEncoding("UTF-8"); return
		 * resolver; }
		 */

	    @Bean
	    @Description("Thymeleaf template engine with Spring integration")
	    public SpringTemplateEngine templateEngine() {
	        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	        templateEngine.addTemplateResolver(new UrlTemplateResolver());
	        //templateEngine.addDialect(new SpringSecurityDialect());
	        templateEngine.setTemplateResolver(templateResolver());
	        return templateEngine;
	    }


	    @Bean
	    @Description("Thymeleaf view resolver")
	    public ViewResolver viewResolver() {
	        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	        viewResolver.setTemplateEngine(templateEngine());
	        viewResolver.setCharacterEncoding("UTF-8");
	        return viewResolver;
	    }
}
