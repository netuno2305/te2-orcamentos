package br.edu.ifs.academico.rest.form.GrupoDespesa;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class GrupoDespesaForm {

    private Float codigo;

    @NotEmpty
    @NotBlank(message = "O Nome é inválido.")
    @Size(max = 80)
    private String nome;


}
