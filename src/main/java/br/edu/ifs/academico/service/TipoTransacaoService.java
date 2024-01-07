package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.TipoTransacaoModel;
import br.edu.ifs.academico.repository.ITipoTransacaoRepository;
import br.edu.ifs.academico.rest.dto.TipoTransacaoDto;
import br.edu.ifs.academico.rest.form.TipoTransacao.TipoTransacaoForm;
import br.edu.ifs.academico.service.exceptions.DataIntegrityException;
import br.edu.ifs.academico.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TipoTransacaoService {

    @Autowired
    ITipoTransacaoRepository tipoTransacaoRepository;
    public TipoTransacaoDto insert(TipoTransacaoForm tipoTransacaoForm) {
        try {
            TipoTransacaoModel tipoTransacaoModel = convertToModel(tipoTransacaoForm);
            tipoTransacaoModel = tipoTransacaoRepository.save(tipoTransacaoModel);
            return convertToDto(tipoTransacaoModel);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Aluno não foi(foram) preenchido(s).");
        }


    }

    public TipoTransacaoDto findById(long Id) {
        try {
            TipoTransacaoModel tipoTransacaoModel = tipoTransacaoRepository.findById(Id).get();
            return convertToDto(tipoTransacaoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + Id);
        }
    }

    public List<TipoTransacaoDto> findAll(){
        List<TipoTransacaoModel> tipoTransacaoModels = tipoTransacaoRepository.findAll();
        return convertListToDto(tipoTransacaoModels);
    }

    public TipoTransacaoDto update(TipoTransacaoForm tipoTransacaoForm, long Id) {
        try {
            Optional<TipoTransacaoModel> modalidadeAplicacaoExistente = tipoTransacaoRepository.findById(Id);
            if (modalidadeAplicacaoExistente.isPresent()) {
                TipoTransacaoModel tipoTransacaoModel = modalidadeAplicacaoExistente.get();
                tipoTransacaoModel.setNome(tipoTransacaoForm.getNome());
                tipoTransacaoModel.setDataAlteracao(LocalDate.now());

                tipoTransacaoRepository.save(tipoTransacaoModel);
                return convertToDto(tipoTransacaoModel);
            }else{
                throw new DataIntegrityException("O Id não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    public void delete(long Id) {
        try {
            if (tipoTransacaoRepository.existsById(Id)) {
                tipoTransacaoRepository.deleteById(Id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir!");
        }
    }
    private TipoTransacaoModel convertToModel(TipoTransacaoForm tipoTransacaoForm) {
        TipoTransacaoModel tipoTransacaoModel = new TipoTransacaoModel();
        tipoTransacaoModel.setNome(tipoTransacaoForm.getNome());

        return tipoTransacaoModel;
    }
    private TipoTransacaoDto convertToDto(TipoTransacaoModel tipoTransacaoModel) {
        TipoTransacaoDto tipoTransacaoDto = new TipoTransacaoDto();
        tipoTransacaoDto.setNome(tipoTransacaoModel.getNome());
        tipoTransacaoDto.setDataCadastro(tipoTransacaoModel.getDataCadastro());
        tipoTransacaoDto.setId(tipoTransacaoModel.getID());

        return tipoTransacaoDto;
    }

    private List<TipoTransacaoDto> convertListToDto(List<TipoTransacaoModel> list){
        List<TipoTransacaoDto> tipoTransacaoDtoList = new ArrayList<>();
        for (TipoTransacaoModel tipoTransacaoModel : list) {
            TipoTransacaoDto tipoTransacaoDto = this.convertToDto(tipoTransacaoModel);
            tipoTransacaoDtoList.add(tipoTransacaoDto);
        }
        return tipoTransacaoDtoList;
    }

}
