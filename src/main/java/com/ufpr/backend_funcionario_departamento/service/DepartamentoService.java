package com.ufpr.backend_funcionario_departamento.service;

import com.ufpr.backend_funcionario_departamento.entity.Departamento;
import com.ufpr.backend_funcionario_departamento.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Transactional(readOnly = true)
    public List<Departamento> findAllDepartamentos () {
        return departamentoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Departamento findFisrtDepartamentoCadastrado () {
        return departamentoRepository.findFirstByOrderByCodigoAsc();
    }

}
