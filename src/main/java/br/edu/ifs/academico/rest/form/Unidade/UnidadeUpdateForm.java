package br.edu.ifs.academico.rest.form.Unidade;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class UnidadeUpdateForm {
    @NotEmpty
    @NotBlank
    @Size(max = 80)
    private String nome;
}