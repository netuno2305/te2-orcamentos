package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.LancamentoDto;
import br.edu.ifs.academico.rest.form.Lancamento.LancamentoForm;
import br.edu.ifs.academico.rest.form.Lancamento.LancamentoUpdateForm;
import br.edu.ifs.academico.service.LancamentoService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping
    public ResponseEntity<List<LancamentoDto>> findAll() {
        List<LancamentoDto> alunoDtoList = lancamentoService.findAll();
        return ResponseEntity.ok().body(alunoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoDto> find(@PathVariable("id") long codigoLancamento) {
        LancamentoDto lancamentoDto = lancamentoService.findById(codigoLancamento);
        return ResponseEntity.ok().body(lancamentoDto);
    }

    @PostMapping
    public ResponseEntity<LancamentoDto> insert(@RequestBody @Valid LancamentoForm lancamentoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        LancamentoDto lancamentoDto = lancamentoService.insert(lancamentoForm);
        return ResponseEntity.ok().body(lancamentoDto);
    }

   @PutMapping("/{id}")
    public ResponseEntity<LancamentoDto> update(@RequestBody @Valid LancamentoUpdateForm lancamentoUpdateForm
            , @PathVariable("id") long codigoLancamento, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        LancamentoDto lancamentoDto = lancamentoService.update(lancamentoUpdateForm, codigoLancamento);
        return ResponseEntity.ok().body(lancamentoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long codigoLancamento) {
        lancamentoService.delete(codigoLancamento);
        return ResponseEntity.noContent().build();
    }
}