package br.edu.ifs.academico.model;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="ElementoDespesa")
public class ElementoDespesaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false)
    private Long Codigo;

    @Column(nullable = false, length = 255)
    private String Nome;

    @Column(name = "DataCadastro", nullable = false)
    private LocalDate DataCadastro;

    @Column(name = "DataAlteracao")
    private LocalDate DataAlteracao;

    @PreUpdate
    private void preUpdate() {
        DataAlteracao = LocalDate.now();
    }

    @PrePersist
    private void prePersist() {
        DataCadastro = LocalDate.now();
    }
}