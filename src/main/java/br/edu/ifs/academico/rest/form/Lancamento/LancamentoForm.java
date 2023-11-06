package br.edu.ifs.academico.rest.form.Lancamento;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Data
public class LancamentoForm {
    private Boolean lancamentoInvalido;

    private Long numeroLancamento;

    private Long idTipoLancamento;

    private Long idUnidade;

    private Long idUnidadeOrcamentaria;

    private Long idElementoDespesa;

    private Long idAcao;

    private Long idPrograma;

    private Long idLancamentoPai;

    private Long idFonteRecurso;

    private Long idSolicitante;

    private Long idObjetivoEstrategico;

    private Long idGrupoDespesa;

    private Long idModalidadeAplicacao;

    private Long idTipoTransacao;

    @Min(1000)
    @Max(9999)
    private Long anoOrcamento;

    @NotEmpty
    @NotBlank
    @Size(max = 255)
    private String descricao;

    @NotEmpty
    @NotBlank
    @Size(max = 27)
    private String GED;

    @NotEmpty
    @NotBlank
    @Size(max = 255)
    private String contratado;

    private Float valor;

    @NotNull(message = "Data  não pode ser nula.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataLancamento;

}