package com.sophosBank;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ClinicaOdontologicaApplication {

	public static void main(String[] args) {

		PropertyConfigurator.configure("src/main/resources/log4j.properties");
            		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
	}

}
