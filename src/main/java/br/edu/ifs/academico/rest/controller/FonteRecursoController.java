package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.AlunoDto;
import br.edu.ifs.academico.rest.dto.FonteRecursoDto;
import br.edu.ifs.academico.rest.form.AlunoForm;
import br.edu.ifs.academico.rest.form.AlunoUpdateForm;
import br.edu.ifs.academico.rest.form.FonteRecurso.FonteRecursoForm;
import br.edu.ifs.academico.rest.form.FonteRecurso.UpdateFonteRecursoForm;
import br.edu.ifs.academico.service.AlunoService;
import br.edu.ifs.academico.service.FonteRecursoService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/fonte-recurso")
public class FonteRecursoController {

    @Autowired
    private FonteRecursoService fonteRecursoService;

    @GetMapping
    public ResponseEntity<List<FonteRecursoDto>> findAll() {
        List<FonteRecursoDto> fonteRecursoDtoList = fonteRecursoService.findAll();
        return ResponseEntity.ok().body(fonteRecursoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FonteRecursoDto> find(@PathVariable("id") long Id) {
        FonteRecursoDto fonteRecursoDto = fonteRecursoService.findById(Id);
        return ResponseEntity.ok().body(fonteRecursoDto);
    }

    @PostMapping
    public ResponseEntity<FonteRecursoDto> insert(@Valid @RequestBody FonteRecursoForm fonteRecursoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        FonteRecursoDto fonteRecursoDto = fonteRecursoService.insert(fonteRecursoForm);
        return ResponseEntity.ok().body(fonteRecursoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FonteRecursoDto> update(@Valid @RequestBody UpdateFonteRecursoForm updateFonteRecursoForm
            , @PathVariable("id") long Id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        FonteRecursoDto alunoDto = fonteRecursoService.update(updateFonteRecursoForm, Id);
        return ResponseEntity.ok().body(alunoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long Id) {
        fonteRecursoService.delete(Id);
        return ResponseEntity.noContent().build();
    }
}
