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
}
