package com.bernardo.CRUD;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bernardo.CRUD.entity.Funcionario;
import com.bernardo.CRUD.resources.FuncionarioResource;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);

	}

	@Bean
	CommandLineRunner initBase(FuncionarioResource repository) {
		return args -> {

			Funcionario f = new Funcionario();
			f.setNome("Bernardo");
			f.setSobrenome("Bortot Spiazzi");
			//validação de e-mail funcionando
			f.setEmail("bernardospiazzidv8@gmail.com");
			f.setPis(12345789L);
			repository.salvar(f);
		};
	}

}
