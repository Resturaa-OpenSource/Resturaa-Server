//package com.auri.config;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Properties;
//
//import javax.sql.DataSource;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.stereotype.Component;
//
//@Component
//@PropertySource("classpath:application.properties")
//@ConfigurationProperties("spring.datasource")
//public class ConfigProperties {
//
//	private String url;
//	private String username;
//	private String password;
//
//	@Bean
//	public DataSource oraclesqlDataSource() {
//
////			File file = new File("C:\\auri\\config.txt");
////			File file = new File("//home//sarath//Projects//pos//config.txt");
//		
////			try {
////				
////				BufferedReader br = new BufferedReader(new FileReader(file));
////				setUrl(br.readLine());
////				setUsername(br.readLine());
////				setPassword(br.readLine());
////				
////				System.out.println(getUrl());
////				System.out.println(getUsername());
////				System.out.println(getPassword());
////				
////			} catch (FileNotFoundException e) {
////				e.printStackTrace();
////			} catch (IOException e) {
////				e.printStackTrace();
////			}
//	
//		return DataSourceBuilder.create().url(getUrl()).username(getUsername()).password(getPassword())
//				.build();
//	}
//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//}