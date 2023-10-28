package br.edu.ifs.academico.rest.controller;
import br.edu.ifs.academico.rest.dto.TipoTransacaoDto;
import br.edu.ifs.academico.rest.form.TipoTransacao.TipoTransacaoForm;
import br.edu.ifs.academico.service.TipoTransacaoService;
import br.edu.ifs.academico.service.TipoTransacaoService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tipo-transacao")
public class TipoTransacaoController {

    @Autowired
    private TipoTransacaoService tipoTransacaoService;

    @GetMapping
    public ResponseEntity<List<TipoTransacaoDto>> findAll() {
        List<TipoTransacaoDto> tipoTransacaoDtoList = tipoTransacaoService.findAll();
        return ResponseEntity.ok().body(tipoTransacaoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoTransacaoDto> find(@PathVariable("id") long Id) {
        TipoTransacaoDto tipoTransacaoDto = tipoTransacaoService.findById(Id);
        return ResponseEntity.ok().body(tipoTransacaoDto);
    }

    @PostMapping
    public ResponseEntity<TipoTransacaoDto> insert(@Valid @RequestBody TipoTransacaoForm tipoTransacaoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TipoTransacaoDto tipoTransacaoDto = tipoTransacaoService.insert(tipoTransacaoForm);
        return ResponseEntity.ok().body(tipoTransacaoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoTransacaoDto> update(@Valid @RequestBody TipoTransacaoForm tipoTransacaoForm
            , @PathVariable("id") long Id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        TipoTransacaoDto tipoTransacaoDto = tipoTransacaoService.update(tipoTransacaoForm, Id);
        return ResponseEntity.ok().body(tipoTransacaoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long Id) {
        tipoTransacaoService.delete(Id);
        return ResponseEntity.noContent().build();
    }
}
