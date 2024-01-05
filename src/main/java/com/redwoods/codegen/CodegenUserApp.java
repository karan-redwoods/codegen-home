package com.redwoods.codegen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodegenApplication implements CommandLineRunner {

	@Autowired
	private CodeGenerator codeGenerator;

	public static void main(String[] args) {
		SpringApplication.run(CodegenApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		codeGenerator.generateCRUDCode("Supplier");
	}
}
