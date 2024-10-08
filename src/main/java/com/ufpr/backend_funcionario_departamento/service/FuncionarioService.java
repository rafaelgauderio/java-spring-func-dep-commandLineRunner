package com.ufpr.backend_funcionario_departamento.service;

import com.ufpr.backend_funcionario_departamento.entity.Departamento;
import com.ufpr.backend_funcionario_departamento.entity.Funcionario;
import com.ufpr.backend_funcionario_departamento.repository.DepartamentoRepository;
import com.ufpr.backend_funcionario_departamento.repository.FuncionarioRepository;
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

    @Transactional(readOnly = true)
    public List<Funcionario> findEmployeeWhereSalaryGreaterThanValue (Double salary) {
        return funcionarioRepository.findEmployeeSalaryGreaterThan(salary);
    }

    @Transactional(readOnly = true)
    public List<Funcionario> findEmployeeWhereSalaryGreaterThanNativeQuery (Double salary) {
        return funcionarioRepository.findEmployeeSalaryGreaterThanNativeQuery(salary);
    }

    @Transactional(readOnly = true)
    public List<Funcionario> findByQuantDependentsNamedQuery(Integer dependents) {
        return funcionarioRepository.findByQuantityOfDependents(dependents);
    }

    @Transactional(readOnly = true)
    public List<Funcionario> FindByNameLikeNamedNativeQuery(String subStringNome) {
        return funcionarioRepository.FindByNameLike(subStringNome);
    }

    @Transactional(readOnly = true)
    public List<Funcionario> findEmployeesDepentsEqualZeroByDepartamentJPQL(Departamento departamento) {
        return funcionarioRepository.findEmployeesDepentsEqualZeroByDepartament(departamento);
    }

    @Transactional(readOnly = false)
    public void updateEmployeesByDeparmentJPQL(Departamento departamentoFrom, Departamento departamentoTo) {
        funcionarioRepository.updateEmployeesByDepartment(departamentoFrom,departamentoTo);
    }

    public void deleteEmployeeJPQL(Long id) {
        Departamento departamento = departamentoRepository.getReferenceById(id);
        funcionarioRepository.deleteEmployee(departamento);
    }

    @Transactional(readOnly = false)
    public void saveNewEmployee(Funcionario func, Departamento dep) {
        departamentoRepository.save(dep);
        func.setDepartamento(dep);
        funcionarioRepository.save(func);
    }

    public void procedure_increase_salary_ten_per_cent (Integer percent) {
        funcionarioRepository.procedure_increase_salary_ten_per_cent(percent);
    }

}
