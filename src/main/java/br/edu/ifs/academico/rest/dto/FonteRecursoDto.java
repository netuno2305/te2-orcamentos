package br.edu.ifs.academico.rest.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FonteRecursoDto {
    private Integer Codigo;
    private String Nome;
    private LocalDate DataCadastro;
    private LocalDate DataAlteracao;
}
