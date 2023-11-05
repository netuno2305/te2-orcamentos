package br.edu.ifs.academico.rest.form.Lancamento;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class LancamentoUpdateForm {
    private Boolean lancamentoInvalido;

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
}