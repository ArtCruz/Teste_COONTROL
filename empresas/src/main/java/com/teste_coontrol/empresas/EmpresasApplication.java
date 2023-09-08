package com.teste_coontrol.empresas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.teste_coontrol.empresas.entity.Empresa;
import com.teste_coontrol.empresas.repository.EmpresaRepository;

@SpringBootApplication
public class EmpresasApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(EmpresasApplication.class, args);
	}

	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Empresa empresa1 = new Empresa("Azul", "18/02/2003", 50, "Sul", "Industrial");
		empresaRepository.save(empresa1);

		Empresa empresa2 = new Empresa("BB", "07/09/1908", 431, "Sudeste", "Serviços");
		empresaRepository.save(empresa2);

		Empresa empresa3 = new Empresa("Correios", "01/01/1963", 50, "Norte", "Varejo");
		empresaRepository.save(empresa3);

	}

}
