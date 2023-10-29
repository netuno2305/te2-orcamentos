package br.edu.ifs.academico.rest.form.ElementoDespesa;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class ElementoDespesaUpdateForm {
    @NotEmpty
    @NotBlank
    @Size(max = 80)
    private String nome;

    private Long codigo;

}