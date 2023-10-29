package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.AcaoDto;
import br.edu.ifs.academico.rest.form.Acao.AcaoForm;
import br.edu.ifs.academico.rest.form.Acao.AcaoUpdateForm;
import br.edu.ifs.academico.service.AcaoService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/acoes")
public class AcaoController {

    @Autowired
    private AcaoService acaoService;

    @GetMapping
    public ResponseEntity<List<AcaoDto>> findAll() {
        List<AcaoDto> alunoDtoList = acaoService.findAll();
        return ResponseEntity.ok().body(alunoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcaoDto> find(@PathVariable("id") long codigoAcao) {
        AcaoDto acaoDto = acaoService.findById(codigoAcao);
        return ResponseEntity.ok().body(acaoDto);
    }

    @PostMapping
    public ResponseEntity<AcaoDto> insert(@RequestBody @Valid AcaoForm acaoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        AcaoDto acaoDto = acaoService.insert(acaoForm);
        return ResponseEntity.ok().body(acaoDto);
    }

   @PutMapping("/{id}")
    public ResponseEntity<AcaoDto> update(@RequestBody @Valid AcaoUpdateForm acaoUpdateForm
            , @PathVariable("id") long codigoAcao, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        AcaoDto acaoDto = acaoService.update(acaoUpdateForm, codigoAcao);
        return ResponseEntity.ok().body(acaoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long codigoAcao) {
        acaoService.delete(codigoAcao);
        return ResponseEntity.noContent().build();
    }
}