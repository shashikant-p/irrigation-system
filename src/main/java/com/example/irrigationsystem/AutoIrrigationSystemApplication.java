package com.example.irrigationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing
@EnableScheduling
public class AutoIrrigationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoIrrigationSystemApplication.class, args);
	}

}
