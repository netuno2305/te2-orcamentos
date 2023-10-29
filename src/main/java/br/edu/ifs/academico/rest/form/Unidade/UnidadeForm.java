package br.edu.ifs.academico.rest.form.Unidade;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class UnidadeForm {
    @NotEmpty
    @NotBlank
    @Size(max = 80)
    private String nome;

    @NotNull(message = "Data de cadastro não pode ser nula.")
    @Past(message = "A data de cadastro informada deve ser anterior ao dia atual.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

}