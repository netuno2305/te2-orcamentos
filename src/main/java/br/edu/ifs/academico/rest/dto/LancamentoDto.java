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
    private String Descricao;
    private Float Valor;
    private LocalDate DataLancamento;
    private LocalDate DataCadastro;
    private LocalDate DataAlteracao;
}
