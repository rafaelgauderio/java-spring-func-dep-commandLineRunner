package com.ufpr.backend_funcionario_departamento.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "funcionario")
@Data
@NamedQueries({
        @NamedQuery(name="Funcionario.findByQuantityOfDependents",
            query = "FROM Funcionario WHERE quantDependentes = ?1")
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name= "Funcionario.FindByNameLike",
                query = "SELECT * FROM Funcionario WHERE nome_func LIKE CONCAT('%',?1,'%') ",
                resultClass = Funcionario.class
        )
})

public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cod_func", nullable = false)
    private Long codigo;
    @Column(name= "nome_func",length = 80, nullable = false)
    private String nome;
    @Column(name="quantidade_dependentes_func")
    private Integer quantDependentes;
    @Column(name = "salario_func")
    private Double salario;
    @Column(name = "cargo_func", length = 60)
    private String cargo;

    @ManyToOne
    @JoinColumn(name = "departamento_func", nullable = false)
    private Departamento departamento;

    @Override
    public String toString() {
        return "Funcionario{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", quantidade de dependentes=" + quantDependentes +
                ", salário=" + salario +
                ", cargo='" + cargo + '\'' +
                ", departamento=" + departamento.getNome() +
                '}';
    }
}
