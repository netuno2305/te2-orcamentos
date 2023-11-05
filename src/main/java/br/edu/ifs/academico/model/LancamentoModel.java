package br.edu.ifs.academico.model;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="Lancamento")
public class LancamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "LancamentoInvalido")
    private boolean LancamentoInvalido;

    @Column(nullable = false)
    private Long NumeroLancamento;

    @Column(nullable = false)
    private Long IDTipoLancamento;

    @Column(nullable = false)
    private Long IDUnidade;

    @Column(nullable = false)
    private Long IDUnidadeOrcamentaria;

    @Column(nullable = false)
    private Long IDElementoDespesa;

    @Column(nullable = false)
    private Long IDAcao;

    @Column(nullable = false)
    private Long IDPrograma;

    @Column(nullable = false)
    private Long IDLancamentoPai;

    @Column(name = "DataLancamento")
    private LocalDate DataLancamento;

    @Column(nullable = false, length = 255)
    private String Descricao;

    @Column(nullable = false, length = 27)
    private String GED;

    @Column(nullable = false, length = 255)
    private String Contratado;

    @Column(nullable = false)
    private Float Valor;

    @Column(name = "DataCadastro", nullable = false)
    private LocalDate DataCadastro;


    @Column(name = "DataAlteracao")
    private LocalDate DataAlteracao;

    @Column(nullable = false, length = 4)
    private Long AnoOrcamento;

    @PreUpdate
    private void preUpdate() {
        DataAlteracao = LocalDate.now();
    }

    @PrePersist
    private void prePersist() {
        DataCadastro = LocalDate.now();
    }
}