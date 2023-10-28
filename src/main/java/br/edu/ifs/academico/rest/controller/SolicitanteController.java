package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.SolicitanteDto;
import br.edu.ifs.academico.rest.form.Solicitante.SolicitanteForm;
import br.edu.ifs.academico.service.SolicitanteService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/solicitante")
public class SolicitanteController {

    @Autowired
    private SolicitanteService solicitanteService;

    @GetMapping
    public ResponseEntity<List<SolicitanteDto>> findAll() {
        List<SolicitanteDto> solicitanteDtoList = solicitanteService.findAll();
        return ResponseEntity.ok().body(solicitanteDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitanteDto> find(@PathVariable("id") long Id) {
        SolicitanteDto solicitanteDto = solicitanteService.findById(Id);
        return ResponseEntity.ok().body(solicitanteDto);
    }

    @PostMapping
    public ResponseEntity<SolicitanteDto> insert(@Valid @RequestBody SolicitanteForm solicitanteForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        SolicitanteDto solicitanteDto = solicitanteService.insert(solicitanteForm);
        return ResponseEntity.ok().body(solicitanteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitanteDto> update(@Valid @RequestBody SolicitanteForm solicitanteForm
            , @PathVariable("id") long Id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        SolicitanteDto solicitanteDto = solicitanteService.update(solicitanteForm, Id);
        return ResponseEntity.ok().body(solicitanteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long Id) {
        solicitanteService.delete(Id);
        return ResponseEntity.noContent().build();
    }
}
