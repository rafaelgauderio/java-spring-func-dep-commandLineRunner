package com.ufpr.backend_funcionario_departamento;

import com.ufpr.backend_funcionario_departamento.entity.Departamento;
import com.ufpr.backend_funcionario_departamento.entity.Funcionario;
import com.ufpr.backend_funcionario_departamento.service.DepartamentoService;
import com.ufpr.backend_funcionario_departamento.service.FuncionarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendFuncionarioDepartamentoApplication {

	private static final Logger log = LoggerFactory.getLogger(BackendFuncionarioDepartamentoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BackendFuncionarioDepartamentoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(DepartamentoService departamentoService, FuncionarioService funcionarioService) {

		return (arg) -> {


			log.info("");
			log.info("");
			log.info("");
			log.info("===========Listagem das Musicas");
			for (Departamento nickname : departamentoService.findAllDepartamentos()) {
				log.info(nickname.toString());
			}

			log.info("");
			log.info("");
			log.info("");
			log.warn("===========Listagem das Funcionários");
			for(Funcionario func : funcionarioService.findAllFuncionarios())
				log.warn(func.toString());
		};
	}
}
