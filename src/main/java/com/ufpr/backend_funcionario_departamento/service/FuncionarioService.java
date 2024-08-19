package com.ufpr.backend_funcionario_departamento.service;

import com.ufpr.backend_funcionario_departamento.entity.Funcionario;
import com.ufpr.backend_funcionario_departamento.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional(readOnly = true)
    public List<Funcionario> findAllFuncionarios () {
        return  funcionarioRepository.findAll();
    }
}
