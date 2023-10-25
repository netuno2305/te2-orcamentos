package br.edu.ifs.academico.rest.form.ModalidadeAplicacao;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class ModalidadeAplicacaoForm {

    @NotEmpty
    @NotBlank(message = "O Código não pode estar em branco.")
    @Size(max = 100)
    private Integer codigo;

    @NotEmpty
    @NotBlank
    @Email(message = "O nome é inválido.")
    @Size(max = 80)
    private String nome;

    @NotNull(message = "Data de cadastro não pode ser nula.")
    @Past(message = "A data de cadastro informada deve ser anterior ao dia atual.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

}
