package br.edu.ifs.academico.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoDto {
    private Long Id;
    private Boolean LancamentoInvalido;
    private String Descricao;
    private String Contratado;
    private String GED;
    private Float Valor;
    private String idTipoLancamento;
    private String idUnidade;
    private String idUnidadeOrcamentaria;
    private String idPrograma;
    private String idAcao;
    private String idFonteRecurso;
    private String idGrupoDespesa;
    private String idModalidadeAplicacao;
    private String idElementoDespesa;
    private String idSolicitante;
    private String idObjetivoEstrategico;
    private String idTipoTransacao;
    private LocalDate DataLancamento;
    private LocalDate DataCadastro;
    private LocalDate DataAlteracao;
}
