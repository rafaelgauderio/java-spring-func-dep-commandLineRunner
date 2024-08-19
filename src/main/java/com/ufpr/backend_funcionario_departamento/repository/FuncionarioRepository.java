package com.ufpr.backend_funcionario_departamento.repository;

import com.ufpr.backend_funcionario_departamento.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
