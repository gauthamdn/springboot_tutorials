package com.springboot.rest.webservices.restfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class RestfulWebservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebservicesApplication.class, args);
	}

	// internationalization ( locale)
	//define messages.properties and messages_fr.properties
	
	// use this when there u define accept language per method else use the AcceptHeaderLocaleResolver 
//	@Bean
//	public LocaleResolver localeResolver() {
//		SessionLocaleResolver localeresolver = new SessionLocaleResolver();
//		localeresolver.setDefaultLocale(Locale.US);
//		return localeresolver;
//	}
	
	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeresolver = new AcceptHeaderLocaleResolver();
		localeresolver.setDefaultLocale(Locale.US);
		return localeresolver;
	}
	
	// configure a resource bundle to refer to teh messages properties 
	// now update the hello world service to use these
	
	
	// you can also define setBasename in application.properties.. when you define it in application.properties you wont need the el
//	@Bean
//	public ResourceBundleMessageSource resourceBundleMessageSource() {
//		
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasename("messages");
//		return messageSource;
//		
//	}
}
