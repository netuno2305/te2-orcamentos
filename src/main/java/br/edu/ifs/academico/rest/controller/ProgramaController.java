package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.ProgramaDto;
import br.edu.ifs.academico.rest.form.Programa.ProgramaForm;
import br.edu.ifs.academico.rest.form.Programa.ProgramaUpdateForm;
import br.edu.ifs.academico.service.ProgramaService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/programas")
public class ProgramaController {

    @Autowired
    private ProgramaService programaService;

    @GetMapping
    public ResponseEntity<List<ProgramaDto>> findAll() {
        List<ProgramaDto> alunoDtoList = programaService.findAll();
        return ResponseEntity.ok().body(alunoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramaDto> find(@PathVariable("id") long codigoPrograma) {
        ProgramaDto programaDto = programaService.findById(codigoPrograma);
        return ResponseEntity.ok().body(programaDto);
    }

    @PostMapping
    public ResponseEntity<ProgramaDto> insert(@RequestBody @Valid ProgramaForm programaForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ProgramaDto programaDto = programaService.insert(programaForm);
        return ResponseEntity.ok().body(programaDto);
    }

   @PutMapping("/{id}")
    public ResponseEntity<ProgramaDto> update(@RequestBody @Valid ProgramaUpdateForm programaUpdateForm
            , @PathVariable("id") long codigoPrograma, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ProgramaDto programaDto = programaService.update(programaUpdateForm, codigoPrograma);
        return ResponseEntity.ok().body(programaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long codigoPrograma) {
        programaService.delete(codigoPrograma);
        return ResponseEntity.noContent().build();
    }
}