package com.app.library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan("com.app.library")
public class LibraryApplication {

	private static final Logger logger = LogManager.getLogger(LibraryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
		logger.debug("--Application Started--");
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
