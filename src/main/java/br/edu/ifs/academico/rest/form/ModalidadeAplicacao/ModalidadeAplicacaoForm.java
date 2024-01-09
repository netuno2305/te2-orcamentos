package br.edu.ifs.academico.rest.form.ModalidadeAplicacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class ModalidadeAplicacaoForm {

    private Integer codigo;

    @NotEmpty
    @NotBlank(message = "O nome é inválido.")
    @Size(max = 80)
    private String nome;

}
