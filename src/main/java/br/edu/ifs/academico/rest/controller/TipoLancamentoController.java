package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.TipoLancamentoDto;
import br.edu.ifs.academico.rest.form.TipoLancamento.TipoLancamentoForm;
import br.edu.ifs.academico.rest.form.TipoLancamento.TipoLancamentoUpdateForm;
import br.edu.ifs.academico.service.TipoLancamentoService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tipo-lancamentos")
public class TipoLancamentoController {

    @Autowired
    private TipoLancamentoService tipoLancamentoService;

    @GetMapping
    public ResponseEntity<List<TipoLancamentoDto>> findAll() {
        List<TipoLancamentoDto> alunoDtoList = tipoLancamentoService.findAll();
        return ResponseEntity.ok().body(alunoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoLancamentoDto> find(@PathVariable("id") long codigoTipoLancamento) {
        TipoLancamentoDto tipoLancamentoDto = tipoLancamentoService.findById(codigoTipoLancamento);
        return ResponseEntity.ok().body(tipoLancamentoDto);
    }

    @PostMapping
    public ResponseEntity<TipoLancamentoDto> insert(@RequestBody @Valid TipoLancamentoForm tipoLancamentoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TipoLancamentoDto tipoLancamentoDto = tipoLancamentoService.insert(tipoLancamentoForm);
        return ResponseEntity.ok().body(tipoLancamentoDto);
    }

   @PutMapping("/{id}")
    public ResponseEntity<TipoLancamentoDto> update(@RequestBody @Valid TipoLancamentoUpdateForm tipoLancamentoUpdateForm
            , @PathVariable("id") long codigoTipoLancamento, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TipoLancamentoDto tipoLancamentoDto = tipoLancamentoService.update(tipoLancamentoUpdateForm, codigoTipoLancamento);
        return ResponseEntity.ok().body(tipoLancamentoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long codigoTipoLancamento) {
        tipoLancamentoService.delete(codigoTipoLancamento);
        return ResponseEntity.noContent().build();
    }
}