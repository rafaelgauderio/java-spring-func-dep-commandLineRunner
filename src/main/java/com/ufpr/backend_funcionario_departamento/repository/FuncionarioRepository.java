package com.ufpr.backend_funcionario_departamento.repository;

import com.ufpr.backend_funcionario_departamento.entity.Departamento;
import com.ufpr.backend_funcionario_departamento.entity.Funcionario;
import com.ufpr.backend_funcionario_departamento.service.DepartamentoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    List<Funcionario> findByQuantityOfDependents(Integer dependents);

    List<Funcionario> FindByNameLike(String subStringNome);

    // Uma consulta que lista todos os funcionários de um determinado departamento que
    //não possuam dependentes utilizando parâmetros nomeados.
    @Query("SELECT f " +
            "FROM Funcionario f " +
            "WHERE quantDependentes=0 AND departamento= :departamento")
    List<Funcionario> findEmployeesDepentsEqualZeroByDepartament(@Param("departamento") Departamento departamento);

    //Uma instrução de update que troca todos os funcionários de um determinado
    //departamento para outro departamento utilizando a anotação @Modifying.
    @Modifying
    @Query("UPDATE Funcionario f " +
            "SET f.departamento= ?2 " +
            "WHERE f.departamento = ?1" +
            "")
    void updateEmployeesByDeparment(Departamento departamentoFrom, Departamento departamentoTo);
}
