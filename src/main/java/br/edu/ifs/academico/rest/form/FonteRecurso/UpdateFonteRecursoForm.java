package br.edu.ifs.academico.rest.form.FonteRecurso;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class UpdateFonteRecursoForm {
    @NotEmpty
    @NotBlank(message = "O Código não pode estar em branco.")
    @Size(max = 100)
    private Integer codigo;

    @NotEmpty
    @NotBlank
    @Email(message = "O Nome é inválido.")
    @Size(max = 80)
    private String nome;

    @NotNull(message = "Data de alteracao não pode ser nula.")
    @Past(message = "A data de alteracao informada deve ser anterior ao dia atual.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAlteracao;
}
