package com.cafe.cafeback;

import com.cafe.cafeback.service.CustomerService;
import com.cafe.cafeback.service.impl.CustomerServiceImpl;
import org.apache.catalina.core.ApplicationContext;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CafebackApplication {

	private static final Logger logger = LogManager.getLogger(CafebackApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CafebackApplication.class, args);
		System.out.println("Application started successfully");

		logger.info("Application started successfully");


//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CafebackApplication.class);
//		CustomerServiceImpl customerService = context.getBean(CustomerServiceImpl.class);
//		customerService.Hello();
	}

}
