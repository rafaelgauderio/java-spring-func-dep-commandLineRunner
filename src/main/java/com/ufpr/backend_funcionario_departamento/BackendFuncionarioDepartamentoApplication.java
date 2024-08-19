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
			log.info("===========Listagem das Musicas");
			for (Departamento nickname : departamentoService.findAllDepartamentos()) {
				log.info(nickname.toString());
			}

			log.info("");
			log.info("");
			log.info("===========Listagem das Funcionários");
			for(Funcionario func : funcionarioService.findAllFuncionarios())
				log.warn(func.toString());

			log.info("");
			log.info("");
			log.info("===========1. Listar um funcionário pelo seu nome e quantidade de dependentes utilizando consulta por palavras-chaves.");
			Funcionario func01 = funcionarioService.FindByNomeAndByQuantidadeDependentes("Rafael de Luca",3);
			log.info(func01.toString());

			log.info("");
			log.info("");
			log.info("===========2. Listar todos os funcionários de um determinado departamento por JPQL via @Query.");
			Departamento departamento = departamentoService.findAllDepartamentos().get(4);
			for(Funcionario func: funcionarioService.findFuncionarioByDepartamento(departamento))  {
				log.info(func.toString());
			}

			log.info("");
			log.info("");
			log.info("===========3. Listar o primeiro departamento cadastrado.");
			Departamento departamento1 = departamentoService.findFisrtDepartamentoCadastrado();
			log.info(departamento1.toString());

			log.info("");
			log.info("");
			log.info("===========4. Listar o primeiro funcionário que tem o maior salario.");
			Funcionario funcionario01 = funcionarioService.findFirstFuncionarioWithHigherSalario();
			log.info(funcionario01.toString());

		};
	}
}
