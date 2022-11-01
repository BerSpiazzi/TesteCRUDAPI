package com.bernardo.CRUD;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bernardo.CRUD.entity.Employee;
import com.bernardo.CRUD.resources.EmployeeResource;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);

	}

	@Bean
	CommandLineRunner initBase(EmployeeResource repository) {
		return args -> {

			Employee f = new Employee();
			f.setName("Bernardo");
			f.setLastName("Bortot Spiazzi");
			// validação de e-mail funcionando
			f.setEmail("bernardospiazzidv8@gmail.com");
			f.setPis(12345789L);
			repository.save(f);
		};
	}

}
