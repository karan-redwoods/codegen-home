package com.redwoods.codegen;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@OpenAPIDefinition(servers = {@Server(url = "/codegenUser/v1", description = "default url")})
@Configuration
@SpringBootApplication
public class CodegenUserApp implements CommandLineRunner {

	@Autowired
	private CodeGenerator codeGenerator;

	public static void main(String[] args) {
		SpringApplication.run(CodegenUserApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		codeGenerator.generateCRUDCode("User");

	}

	@Bean
	public OpenAPI userMicroserviceOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("User Service API").description("User Service APIs").version("1.0"));
	}
}
