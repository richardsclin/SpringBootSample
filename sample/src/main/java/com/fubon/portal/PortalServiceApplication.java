package com.fubon.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.fubon"})  //for search class in the other jar (ex: common-security.jar)
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class PortalServiceApplication extends SpringBootServletInitializer {
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) 
	{
		return application.sources(PortalServiceApplication.class);
	}

	public static void main(String[] args) 
	{
		
		SpringApplication.run(PortalServiceApplication.class, args);
		
	}

}

