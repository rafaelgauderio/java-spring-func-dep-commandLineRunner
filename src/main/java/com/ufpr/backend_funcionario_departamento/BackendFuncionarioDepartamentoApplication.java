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
			log.info("===========Listagem de todos os Departamentos");
			for (Departamento nickname : departamentoService.findAllDepartamentos()) {
				log.info(nickname.toString());
			}

			log.info("");
			log.info("");
			log.info("===========Listagem das Funcionários");
			for(Funcionario func : funcionarioService.findAllFuncionarios())
				log.info(func.toString());

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

			log.info("");
			log.info("");
			log.info("===========5. Listar os 3 (três) primeiros funcionários que tem os maiores salários.");
			for(Funcionario func : funcionarioService.findThreeEmployeeWithHigherSalary()) {
				log.info(func.toString());
			}

			log.info("");
			log.info("");
			log.info("===========6. Listar os funcionários que não tem dependentes em ordem crescente de nome por JPQL via @Query.");
			for(Funcionario nickname : funcionarioService.findEmployeeWhereQuantDependentsEqualZeroOrderByName()) {
				log.info(nickname.toString());
			}

			log.info("");
			log.info("");
			log.info("===========7. Listar os funcionários que tem salário maior que um determinado valor por JPQL sobrescrevendo palavras-chaves @Query.");
			log.info("===========Funcionários com salários > 5000.00");
			for(Funcionario func : funcionarioService.findEmployeeWhereSalaryGreaterThanValue(5000.00)) {
				log.info(func.toString());
			}

			log.info("");
			log.info("");
			log.info("===========8. Listar os funcionários que tem salário maior que um determinado valor por @Query com native query.");
			log.info("===========Funcionários com salários > 4000.00");
			for(Funcionario func : funcionarioService.findEmployeeWhereSalaryGreaterThanNativeQuery(4000.00)) {
				log.info(func.toString());
			}

			log.info("");
			log.info("");
			log.info("===========9. Alterar a classe Funcionario e criar uma consulta para listar os funcionários com uma determinada quantidade de dependentes por @NamedQuery.");
			log.info("=========== Funcionários com 3 depententes by Named Query.");
			for(Funcionario func : funcionarioService.findByQuantDependentsNamedQuery(3)){
				log.info(func.toString());
			}

			log.info("");
			log.info("");
			log.info("===========10. Alterar a classe Funcionario e criar uma consulta para listar os funcionários que contenham em " +
					"qualquer parte do seu nome um determinado nome por @NamedNativeQuery.");
			log.info("Nomes que contém 'Caro'");
			for(Funcionario f : funcionarioService.FindByNameLikeNamedNativeQuery("Caro")){
				log.info(f.toString());
			}
			log.info("Nomes que contém 'Luca'");
			for(Funcionario f : funcionarioService.FindByNameLikeNamedNativeQuery("Luca")){
				log.info(f.toString());
			}

			log.info("");
			log.info("");
			log.info("==========12. Listar todos os funcionários de um determinado departamento que não possuam dependentes");
			Departamento dep04 = departamentoService.findAllDepartamentos().get(4); // index 04 do departamento Financeiro
			for(Funcionario func: funcionarioService.findEmployeesDepentsEqualZeroByDepartamentJPQL(dep04)) {
				log.info(func.toString());
			}

			log.info("");
			log.info("");
			log.info("==========13. update que troca todos os funcionários de um determinado departamento para outro departamento utilizando a anotação @Modifying");
			Departamento dep01 = departamentoService.findAllDepartamentos().get(1);
			log.info("===========Listagem de Funcionários ANTES o update");
			for(Funcionario func : funcionarioService.findAllFuncionarios())
				log.info(func.toString());
			log.info("===========Listagem de Funcionários APÓS o update");
			funcionarioService.updateEmployeesByDeparmentJPQL(dep04,dep01);
			for(Funcionario func : funcionarioService.findAllFuncionarios())
				log.info(func.toString());

			log.info("");
			log.info("");
			log.info("========14. Uma instrução de delete que exclui todos os funcionários de um determinado departamento utilizando a anotação @Modifying.");
			funcionarioService.deleteEmployeeJPQL(2L); // vai excluir todos os funcionários de departamento de TI
			log.info("===========Listagem de Funcionários APÓS a exclusão");
			for(Funcionario func : funcionarioService.findAllFuncionarios())
				log.info(func.toString());

			log.info("");
			log.info("");
			log.info("========15. Criar um método na classe de serviço de departamento para salvar um departamento, associar esse departamento a um funcionário e salvar esse funcionário em um mesmo controle de transação(@Transactional)");
			Departamento dep05 = departamentoService.findAllDepartamentos().get(5);
			Funcionario funcionario = new Funcionario();
			funcionario.setNome("Maycon");
			funcionario.setCargo("Vendedor");
			funcionario.setSalario(5600.00);
			funcionario.setQuantDependentes(2);
			funcionarioService.saveNewEmployee(funcionario,dep05);
			log.info("========Listagem de funcionários após a inserção de novo no banco de dados");
			for(Funcionario func : funcionarioService.findAllFuncionarios())
				log.info(func.toString());
		};
	}
}