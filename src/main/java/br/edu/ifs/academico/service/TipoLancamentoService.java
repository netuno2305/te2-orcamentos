package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.TipoLancamentoModel;
import br.edu.ifs.academico.repository.ITipoLancamentoRepository;
import br.edu.ifs.academico.rest.dto.TipoLancamentoDto;
import br.edu.ifs.academico.rest.form.TipoLancamento.TipoLancamentoForm;
import br.edu.ifs.academico.rest.form.TipoLancamento.TipoLancamentoUpdateForm;
import br.edu.ifs.academico.service.exceptions.DataIntegrityException;
import br.edu.ifs.academico.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TipoLancamentoService {

    @Autowired
    ITipoLancamentoRepository tipoLancamentoRepository;

    public TipoLancamentoDto findById(long idTipoLancamento) {
        try {
            TipoLancamentoModel tipoLancamentoModel = tipoLancamentoRepository.findById(idTipoLancamento).get();
            return convertTipoLancamentoModelToTipoLancamentoDto(tipoLancamentoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Código: " + idTipoLancamento + ", Tipo: " + TipoLancamentoModel.class.getName());
        }
    }

    public List<TipoLancamentoDto> findAll(){
        List<TipoLancamentoModel> tipoLancamentoList = tipoLancamentoRepository.findAll();
        return convertListToDto(tipoLancamentoList);
    }

    public TipoLancamentoDto insert(TipoLancamentoForm tipoLancamentoForm) {
        try {
            TipoLancamentoModel tipoLancamentoNovo = convertTipoLancamentoFormToTipoLancamentoModel(tipoLancamentoForm);
            tipoLancamentoNovo = tipoLancamentoRepository.save(tipoLancamentoNovo);
            return convertTipoLancamentoModelToTipoLancamentoDto(tipoLancamentoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da TipoLancamento  não foi(foram) preenchido(s).");
        }
    }

    public TipoLancamentoDto update(TipoLancamentoUpdateForm tipoLancamentoUpdateForm, long idTipoLancamento) {
        try {
            Optional<TipoLancamentoModel> tipoLancamentoExistente = tipoLancamentoRepository.findById(idTipoLancamento);
            if (tipoLancamentoExistente.isPresent()) {
                TipoLancamentoModel tipoLancamentoAtualizado = tipoLancamentoExistente.get();
                tipoLancamentoAtualizado.setNome(tipoLancamentoUpdateForm.getNome());
                tipoLancamentoRepository.save(tipoLancamentoAtualizado);
                return convertTipoLancamentoModelToTipoLancamentoDto(tipoLancamentoAtualizado);
            }else{
                throw new DataIntegrityException("O ID da TipoLancamento  não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da TipoLancamento  não foi(foram) preenchido(s).");
        }
    }

    public void delete(long idTipoLancamento) {
        try {
            if (tipoLancamentoRepository.existsById(idTipoLancamento)) {
                tipoLancamentoRepository.deleteById(idTipoLancamento);
            }
        } catch (
                DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir essa TipoLancamento ");
        }
    }

    private TipoLancamentoModel convertTipoLancamentoFormToTipoLancamentoModel(TipoLancamentoForm tipoLancamentoForm) {
        TipoLancamentoModel tipoLancamentoModel = new TipoLancamentoModel();
        tipoLancamentoModel.setNome(tipoLancamentoForm.getNome());
        return tipoLancamentoModel;
    }

    private TipoLancamentoDto convertTipoLancamentoModelToTipoLancamentoDto(TipoLancamentoModel tipoLancamentoModel) {
        TipoLancamentoDto tipoLancamentoDto = new TipoLancamentoDto();
        tipoLancamentoDto.setNome(tipoLancamentoModel.getNome());
        tipoLancamentoDto.setId(tipoLancamentoModel.getID());
        return tipoLancamentoDto;
    }

    private List<TipoLancamentoDto> convertListToDto(List<TipoLancamentoModel> list){
        List<TipoLancamentoDto> tipoLancamentoDtoList = new ArrayList<>();
        for (TipoLancamentoModel tipoLancamentoModel : list) {
            TipoLancamentoDto tipoLancamentoDto = this.convertTipoLancamentoModelToTipoLancamentoDto(tipoLancamentoModel);
            tipoLancamentoDtoList.add(tipoLancamentoDto);
        }
        return tipoLancamentoDtoList;
    }


}