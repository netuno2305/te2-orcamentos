package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.UnidadeOrcamentariaDto;
import br.edu.ifs.academico.rest.form.UnidadeOrcamentaria.UnidadeOrcamentariaForm;
import br.edu.ifs.academico.rest.form.UnidadeOrcamentaria.UnidadeOrcamentariaUpdateForm;
import br.edu.ifs.academico.service.UnidadeOrcamentariaService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/unidades-orcamentarias")
public class UnidadeOrcamentariaController {

    @Autowired
    private UnidadeOrcamentariaService unidadeOrcamentariaService;

    @GetMapping
    public ResponseEntity<List<UnidadeOrcamentariaDto>> findAll() {
        List<UnidadeOrcamentariaDto> alunoDtoList = unidadeOrcamentariaService.findAll();
        return ResponseEntity.ok().body(alunoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeOrcamentariaDto> find(@PathVariable("id") long codigoUnidadeOrcamentaria) {
        UnidadeOrcamentariaDto unidadeOrcamentariaDto = unidadeOrcamentariaService.findById(codigoUnidadeOrcamentaria);
        return ResponseEntity.ok().body(unidadeOrcamentariaDto);
    }

    @PostMapping
    public ResponseEntity<UnidadeOrcamentariaDto> insert(@RequestBody @Valid UnidadeOrcamentariaForm unidadeOrcamentariaForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        UnidadeOrcamentariaDto unidadeOrcamentariaDto = unidadeOrcamentariaService.insert(unidadeOrcamentariaForm);
        return ResponseEntity.ok().body(unidadeOrcamentariaDto);
    }

   @PutMapping("/{id}")
    public ResponseEntity<UnidadeOrcamentariaDto> update(@RequestBody @Valid UnidadeOrcamentariaUpdateForm unidadeOrcamentariaUpdateForm
            , @PathVariable("id") long codigoUnidadeOrcamentaria, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        UnidadeOrcamentariaDto unidadeOrcamentariaDto = unidadeOrcamentariaService.update(unidadeOrcamentariaUpdateForm, codigoUnidadeOrcamentaria);
        return ResponseEntity.ok().body(unidadeOrcamentariaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long codigoUnidadeOrcamentaria) {
        unidadeOrcamentariaService.delete(codigoUnidadeOrcamentaria);
        return ResponseEntity.noContent().build();
    }
}