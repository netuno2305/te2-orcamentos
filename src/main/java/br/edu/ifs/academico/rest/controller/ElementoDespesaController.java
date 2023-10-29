package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.ElementoDespesaDto;
import br.edu.ifs.academico.rest.form.ElementoDespesa.ElementoDespesaForm;
import br.edu.ifs.academico.rest.form.ElementoDespesa.ElementoDespesaUpdateForm;
import br.edu.ifs.academico.service.ElementoDespesaService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/elementos-despesa")
public class ElementoDespesaController {

    @Autowired
    private ElementoDespesaService elementoDespesaService;

    @GetMapping
    public ResponseEntity<List<ElementoDespesaDto>> findAll() {
        List<ElementoDespesaDto> alunoDtoList = elementoDespesaService.findAll();
        return ResponseEntity.ok().body(alunoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElementoDespesaDto> find(@PathVariable("id") long codigoElementoDespesa) {
        ElementoDespesaDto elementoDespesaDto = elementoDespesaService.findById(codigoElementoDespesa);
        return ResponseEntity.ok().body(elementoDespesaDto);
    }

    @PostMapping
    public ResponseEntity<ElementoDespesaDto> insert(@RequestBody @Valid ElementoDespesaForm elementoDespesaForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ElementoDespesaDto elementoDespesaDto = elementoDespesaService.insert(elementoDespesaForm);
        return ResponseEntity.ok().body(elementoDespesaDto);
    }

   @PutMapping("/{id}")
    public ResponseEntity<ElementoDespesaDto> update(@RequestBody @Valid ElementoDespesaUpdateForm elementoDespesaUpdateForm
            , @PathVariable("id") long codigoElementoDespesa, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ElementoDespesaDto elementoDespesaDto = elementoDespesaService.update(elementoDespesaUpdateForm, codigoElementoDespesa);
        return ResponseEntity.ok().body(elementoDespesaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long codigoElementoDespesa) {
        elementoDespesaService.delete(codigoElementoDespesa);
        return ResponseEntity.noContent().build();
    }
}