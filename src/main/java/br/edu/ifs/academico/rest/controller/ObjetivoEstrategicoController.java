package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.ObjetivoEstrategicoDto;
import br.edu.ifs.academico.rest.form.ObjetivoEstrategico.ObjetivoEstrategicoForm;
import br.edu.ifs.academico.service.ObjetivoEstrategicoService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/objetivo-estrategico")
public class ObjetivoEstrategicoController {

    @Autowired
    private ObjetivoEstrategicoService objetivoEstrategicoService;

    @GetMapping
    public ResponseEntity<List<ObjetivoEstrategicoDto>> findAll() {
        List<ObjetivoEstrategicoDto> objetivoEstrategicoDtoList = objetivoEstrategicoService.findAll();
        return ResponseEntity.ok().body(objetivoEstrategicoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjetivoEstrategicoDto> find(@PathVariable("id") long Id) {
        ObjetivoEstrategicoDto objetivoEstrategicoDto = objetivoEstrategicoService.findById(Id);
        return ResponseEntity.ok().body(objetivoEstrategicoDto);
    }

    @PostMapping
    public ResponseEntity<ObjetivoEstrategicoDto> insert(@Valid @RequestBody ObjetivoEstrategicoForm objetivoEstrategicoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ObjetivoEstrategicoDto objetivoEstrategicoDto = objetivoEstrategicoService.insert(objetivoEstrategicoForm);
        return ResponseEntity.ok().body(objetivoEstrategicoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjetivoEstrategicoDto> update(@Valid @RequestBody ObjetivoEstrategicoForm objetivoEstrategicoForm
            , @PathVariable("id") long Id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ObjetivoEstrategicoDto objetivoEstrategicoDto = objetivoEstrategicoService.update(objetivoEstrategicoForm, Id);
        return ResponseEntity.ok().body(objetivoEstrategicoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long Id) {
        objetivoEstrategicoService.delete(Id);
        return ResponseEntity.noContent().build();
    }
}
