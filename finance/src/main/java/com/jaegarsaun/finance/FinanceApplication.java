package com.jaegarsaun.finance;

import com.jaegarsaun.finance.model.Test;
import com.jaegarsaun.finance.repository.TestRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.jaegarsaun.finance.config.Database;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(TestRepository repository) {
		return (args) -> {
			// Fetch all entries from the 'test' table
			List<Test> tests = repository.findAll();

			// Print each entry to the console
			for (Test test : tests) {
				System.out.println(test.toString());
			}
		};
	}
}
