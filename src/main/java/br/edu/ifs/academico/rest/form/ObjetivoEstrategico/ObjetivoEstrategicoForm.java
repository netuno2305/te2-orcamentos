package br.edu.ifs.academico.rest.form.ObjetivoEstrategico;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class ObjetivoEstrategicoForm {

    @NotEmpty
    @NotBlank
    @Email(message = "O Endereço de e-mail é inválido.")
    @Size(max = 80)
    private String nome;

    @NotNull(message = "Data de cadastro não pode ser nula.")
    @Past(message = "A data de cadastro informada deve ser anterior ao dia atual.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @NotNull(message = "Data de alteração não pode ser nula.")
    @Past(message = "A data de alteração informada deve ser anterior ao dia atual.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAlteracao;
}
