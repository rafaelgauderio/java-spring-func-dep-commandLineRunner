package com.ufpr.backend_funcionario_departamento.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "departamento")
@Data
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_departamento", nullable = false)
    private Long codigo;

    @Column(name= "nome_departamento", length = 40)
    private String nome;

    @Override
    public String toString() {
        return "Departamento{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                '}';
    }
}
