package br.edu.ifs.academico.rest.form.Solicitante;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class SolicitanteForm {
    @NotEmpty
    @NotBlank(message = "O nome é inválido.")
    @Size(max = 80)
    private String nome;
}
