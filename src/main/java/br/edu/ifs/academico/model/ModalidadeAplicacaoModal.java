package br.edu.ifs.academico.model;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="ModalidadeAplicacao")
public class ModalidadeAplicacaoModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false, length = 255)
    private Integer Codigo;

    @Column(nullable = false, length = 255)
    private String Nome;

    @Column(name = "DataCadastro", nullable = false)
    private LocalDate DataCadastro;

    @Column(name = "DataAlteracao", nullable = false)
    private LocalDate DataAlteracao;
}
