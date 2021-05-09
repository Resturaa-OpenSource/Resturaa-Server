package com.auri.pos;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.auri.controller", "com.auir.dao", "com.auri.entity", "com.auri.config",
		"com.auri.service" })
@EnableJpaRepositories("com.auri.dao")
@EntityScan(basePackages = "com.auri.entity")
@Configuration
@EnableAutoConfiguration

public class IapsSpringappApplication /*implements CommandLineRunner*/  {

	
//	@Resource
//	StorageService storageService;
 
	public static void main(String[] args) throws IOException {
		SpringApplication.run(IapsSpringappApplication.class, args);
	}
	
//	@Override
//	public void run(String... arg) throws Exception {
//		storageService.init();
//	}
}
