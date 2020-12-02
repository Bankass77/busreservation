package com.busreseravtionsystem.busreservation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class PageConfiguration implements WebMvcConfigurer , ApplicationContextAware {

	
	  @Autowired 
	  private MessageSource messageSource;
	  
	  @Value("${spring.thymeleaf.cache}") String thymeleafCache;
	  
	  private ApplicationContext applicationContext;
	  
	  public void setApplicationContext(ApplicationContext applicationContext) {
	  this.applicationContext = applicationContext; }
	  
	  @Bean public ITemplateResolver templateResolver() {
	  SpringResourceTemplateResolver templateResolver = new
	  SpringResourceTemplateResolver();
	  templateResolver.setApplicationContext(applicationContext);
	  templateResolver.setPrefix("classpath:/templates/");
	  templateResolver.setSuffix(".html");
	  templateResolver.setTemplateMode("HTML5");
	  templateResolver.setCharacterEncoding("UTF-8");
	  
	  if (thymeleafCache.equals("true")) { templateResolver.setCacheable(true); }
	  else { templateResolver.setCacheable(false); }
	  
	  return templateResolver; }
	  
	  @Bean public SpringTemplateEngine templateEngine() { SpringTemplateEngine
	  templateEngine = new SpringTemplateEngine();
	  
	  templateEngine.setEnableSpringELCompiler(true);
	  templateEngine.setTemplateResolver(templateResolver());
	  templateEngine.setMessageSource(messageSource); templateEngine.addDialect(new
	  LayoutDialect());
	  
	  return templateEngine; }
	  
	  @Bean public ViewResolver viewResolver() { ThymeleafViewResolver viewResolver
	  = new ThymeleafViewResolver();
	  viewResolver.setTemplateEngine(templateEngine()); return viewResolver; }
	 
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

}
