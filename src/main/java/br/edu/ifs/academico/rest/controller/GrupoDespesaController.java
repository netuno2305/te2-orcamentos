package br.edu.ifs.academico.rest.controller;
import br.edu.ifs.academico.rest.dto.GrupoDespesaDto;
import br.edu.ifs.academico.rest.form.GrupoDespesa.GrupoDespesaForm;
import br.edu.ifs.academico.service.GrupoDespesaService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupo-despesa")
public class GrupoDespesaController {

    @Autowired
    private GrupoDespesaService grupoDespesaService;

    @GetMapping
    public ResponseEntity<List<GrupoDespesaDto>> findAll() {
        List<GrupoDespesaDto> grupoDespesaDtoList = grupoDespesaService.findAll();
        return ResponseEntity.ok().body(grupoDespesaDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoDespesaDto> find(@PathVariable("id") long Id) {
        GrupoDespesaDto grupoDespesaDto = grupoDespesaService.findById(Id);
        return ResponseEntity.ok().body(grupoDespesaDto);
    }

    @PostMapping
    public ResponseEntity<GrupoDespesaDto> insert(@Valid @RequestBody GrupoDespesaForm grupoDespesaForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        GrupoDespesaDto grupoDespesaDto = grupoDespesaService.insert(grupoDespesaForm);
        return ResponseEntity.ok().body(grupoDespesaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GrupoDespesaDto> update(@Valid @RequestBody GrupoDespesaForm grupoDespesaForm
            , @PathVariable("id") long Id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        GrupoDespesaDto grupoDespesaDto = grupoDespesaService.update(grupoDespesaForm, Id);
        return ResponseEntity.ok().body(grupoDespesaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long Id) {
        grupoDespesaService.delete(Id);
        return ResponseEntity.noContent().build();
    }
}
