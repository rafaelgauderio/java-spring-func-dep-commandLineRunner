package com.ufpr.backend_funcionario_departamento.service;

import com.ufpr.backend_funcionario_departamento.entity.Departamento;
import com.ufpr.backend_funcionario_departamento.entity.Funcionario;
import com.ufpr.backend_funcionario_departamento.repository.DepartamentoRepository;
import com.ufpr.backend_funcionario_departamento.repository.FuncionarioRepository;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Transactional(readOnly = true)
    public List<Funcionario> findAllFuncionarios () {
        return  funcionarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Funcionario FindByNomeAndByQuantidadeDependentes(String nome, Integer quantDependentes) {
        return funcionarioRepository.findByNomeAndQuantDependentes(nome, quantDependentes);
    }

    @Transactional(readOnly = true)
    public List<Funcionario> findFuncionarioByDepartamento(Departamento departamento) {

        return funcionarioRepository.findAllFuncionariosByDepartamento(departamento);
    }

    @Transactional(readOnly = true)
    public Funcionario findFirstFuncionarioWithHigherSalario () {
        return funcionarioRepository.findFirstByOrderBySalarioDesc();
    }

    @Transactional(readOnly = true)
    public List<Funcionario> findThreeEmployeeWithHigherSalary () {
        return  funcionarioRepository.findThreeWithHigherSalary();
    }

    @Transactional(readOnly = true)
    public List<Funcionario> findEmployeeWhereQuantDependentsEqualZeroOrderByName () {
        return funcionarioRepository.findEmployeeQuantDependentsEqualZeroOrderByName();
    }
}
