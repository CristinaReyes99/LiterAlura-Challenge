package com.aluracursos.Liter_Alura;

import com.aluracursos.Liter_Alura.principal.Principal;
import com.aluracursos.Liter_Alura.repository.LibroRepository;
import com.aluracursos.Liter_Alura.service.ConsumoAPI;
import com.aluracursos.Liter_Alura.service.ConvierteDtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository repository;

	@Autowired
	private ConsumoAPI consumoAPI;

	@Autowired
	private ConvierteDtos conversor;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Principal principal = new Principal(repository, consumoAPI, conversor);
		principal.muestraElMenu();
	}
}