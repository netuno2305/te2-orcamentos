package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.UnidadeDto;
import br.edu.ifs.academico.rest.form.Unidade.UnidadeForm;
import br.edu.ifs.academico.rest.form.Unidade.UnidadeUpdateForm;
import br.edu.ifs.academico.service.UnidadeService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    @Autowired
    private UnidadeService unidadeService;

    @GetMapping
    public ResponseEntity<List<UnidadeDto>> findAll() {
        List<UnidadeDto> alunoDtoList = unidadeService.findAll();
        return ResponseEntity.ok().body(alunoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeDto> find(@PathVariable("id") long codigoUnidade) {
        UnidadeDto unidadeDto = unidadeService.findById(codigoUnidade);
        return ResponseEntity.ok().body(unidadeDto);
    }

    @PostMapping
    public ResponseEntity<UnidadeDto> insert(@RequestBody @Valid UnidadeForm unidadeForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        UnidadeDto unidadeDto = unidadeService.insert(unidadeForm);
        return ResponseEntity.ok().body(unidadeDto);
    }

   @PutMapping("/{id}")
    public ResponseEntity<UnidadeDto> update(@RequestBody @Valid UnidadeUpdateForm unidadeUpdateForm
            , @PathVariable("id") long codigoUnidade, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        UnidadeDto unidadeDto = unidadeService.update(unidadeUpdateForm, codigoUnidade);
        return ResponseEntity.ok().body(unidadeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long codigoUnidade) {
        unidadeService.delete(codigoUnidade);
        return ResponseEntity.noContent().build();
    }
}