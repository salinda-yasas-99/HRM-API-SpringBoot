package com.hrm.human.resource.management.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableScheduling
//@EnableSwagger2
//@EnableWebMvc
public class HumanResourceManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HumanResourceManagementSystemApplication.class, args);
	}

}
