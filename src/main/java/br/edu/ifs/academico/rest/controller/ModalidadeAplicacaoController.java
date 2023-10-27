package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.ModalidadeAplicacaoDto;
import br.edu.ifs.academico.rest.dto.ModalidadeAplicacaoDto;
import br.edu.ifs.academico.rest.form.ModalidadeAplicacao.ModalidadeAplicacaoForm;
import br.edu.ifs.academico.service.GrupoDespesaService;
import br.edu.ifs.academico.service.ModalidadeAplicacaoService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/modalidade-aplicacao")
public class ModalidadeAplicacaoController {

    @Autowired
    private ModalidadeAplicacaoService modalidadeAplicacaoService;

    @GetMapping
    public ResponseEntity<List<ModalidadeAplicacaoDto>> findAll() {
        List<ModalidadeAplicacaoDto> ModalidadeAplicacaoDtoList = modalidadeAplicacaoService.findAll();
        return ResponseEntity.ok().body(ModalidadeAplicacaoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModalidadeAplicacaoDto> find(@PathVariable("id") long Id) {
        ModalidadeAplicacaoDto ModalidadeAplicacaoDto = modalidadeAplicacaoService.findById(Id);
        return ResponseEntity.ok().body(ModalidadeAplicacaoDto);
    }

    @PostMapping
    public ResponseEntity<ModalidadeAplicacaoDto> insert(@Valid @RequestBody ModalidadeAplicacaoForm ModalidadeAplicacaoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ModalidadeAplicacaoDto ModalidadeAplicacaoDto = modalidadeAplicacaoService.insert(ModalidadeAplicacaoForm);
        return ResponseEntity.ok().body(ModalidadeAplicacaoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModalidadeAplicacaoDto> update(@Valid @RequestBody ModalidadeAplicacaoForm ModalidadeAplicacaoForm
            , @PathVariable("id") long Id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ModalidadeAplicacaoDto ModalidadeAplicacaoDto = modalidadeAplicacaoService.update(ModalidadeAplicacaoForm, Id);
        return ResponseEntity.ok().body(ModalidadeAplicacaoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long Id) {
        modalidadeAplicacaoService.delete(Id);
        return ResponseEntity.noContent().build();
    }
}
