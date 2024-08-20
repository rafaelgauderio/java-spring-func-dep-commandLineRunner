package com.ufpr.backend_funcionario_departamento.repository;

import com.ufpr.backend_funcionario_departamento.entity.Departamento;
import com.ufpr.backend_funcionario_departamento.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Funcionario findByNomeAndQuantDependentes(String nome, Integer quantDependentes);

    @Query("SELECT func " +
    "FROM Funcionario func " +
            "WHERE departamento = ?1")
    List<Funcionario> findAllFuncionariosByDepartamento (Departamento Departamento);

    Funcionario findFirstByOrderBySalarioDesc();

    @Query(value = "SELECT * " +
            "FROM funcionario " +
            "ORDER BY salario_func DESC " +
            "LIMIT 3", nativeQuery = true)
    List<Funcionario> findThreeWithHigherSalary ();

    @Query("SELECT func " +
            "FROM Funcionario func " +
            "WHERE quantDependentes = 0 " +
            "ORDER BY nome ASC")
    List<Funcionario> findEmployeeQuantDependentsEqualZeroOrderByName ();

    @Query("SELECT func " +
            "FROM Funcionario func " +
            "WHERE salario > ?1")
    List<Funcionario> findEmployeeSalaryGreaterThan (Double salary);

    @Query(value = "SELECT * " +
            "FROM funcionario " +
            "WHERE salario_func > ?1", nativeQuery = true)
    List<Funcionario> findEmployeeSalaryGreaterThanNativeQuery (Double salary);
}
