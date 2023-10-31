package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.UnidadeModel;
import br.edu.ifs.academico.repository.IUnidadeRepository;
import br.edu.ifs.academico.rest.dto.UnidadeDto;
import br.edu.ifs.academico.rest.form.Unidade.UnidadeForm;
import br.edu.ifs.academico.rest.form.Unidade.UnidadeUpdateForm;
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
public class UnidadeService {

    @Autowired
    IUnidadeRepository unidadeRepository;

    public UnidadeDto findById(long idUnidade) {
        try {
            UnidadeModel unidadeModel = unidadeRepository.findById(idUnidade).get();
            return convertUnidadeModelToUnidadeDto(unidadeModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Código: " + idUnidade + ", Tipo: " + UnidadeModel.class.getName());
        }
    }

    public List<UnidadeDto> findAll(){
        List<UnidadeModel> unidadeList = unidadeRepository.findAll();
        return convertListToDto(unidadeList);
    }

    public UnidadeDto insert(UnidadeForm unidadeForm) {
        try {
            UnidadeModel unidadeNovo = convertUnidadeFormToUnidadeModel(unidadeForm);
            unidadeNovo = unidadeRepository.save(unidadeNovo);
            return convertUnidadeModelToUnidadeDto(unidadeNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Unidade Orcamentaria não foi(foram) preenchido(s).");
        }
    }

    public UnidadeDto update(UnidadeUpdateForm unidadeUpdateForm, long idUnidade) {
        try {
            Optional<UnidadeModel> unidadeExistente = unidadeRepository.findById(idUnidade);
            if (unidadeExistente.isPresent()) {
                UnidadeModel unidadeAtualizado = unidadeExistente.get();
                unidadeAtualizado.setNome(unidadeUpdateForm.getNome());
                unidadeRepository.save(unidadeAtualizado);
                return convertUnidadeModelToUnidadeDto(unidadeAtualizado);
            }else{
                throw new DataIntegrityException("O ID da Unidade Orcamentaria não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Unidade Orcamentaria não foi(foram) preenchido(s).");
        }
    }

    public void delete(long idUnidade) {
        try {
            if (unidadeRepository.existsById(idUnidade)) {
                unidadeRepository.deleteById(idUnidade);
            }
        } catch (
                DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir essa Unidade Orcamentaria");
        }
    }

    private UnidadeModel convertUnidadeFormToUnidadeModel(UnidadeForm unidadeForm) {
        UnidadeModel unidadeModel = new UnidadeModel();
        unidadeModel.setNome(unidadeForm.getNome());
        return unidadeModel;
    }

    private UnidadeDto convertUnidadeModelToUnidadeDto(UnidadeModel unidadeModel) {
        UnidadeDto unidadeDto = new UnidadeDto();
        unidadeDto.setNome(unidadeModel.getNome());
        unidadeDto.setId(unidadeModel.getID());
        return unidadeDto;
    }

    private List<UnidadeDto> convertListToDto(List<UnidadeModel> list){
        List<UnidadeDto> unidadeDtoList = new ArrayList<>();
        for (UnidadeModel unidadeModel : list) {
            UnidadeDto unidadeDto = this.convertUnidadeModelToUnidadeDto(unidadeModel);
            unidadeDtoList.add(unidadeDto);
        }
        return unidadeDtoList;
    }

    //Poderíamos usar esse método no DELETE... apenas adicionei como exemplo de validação de negócio.
    /* public boolean possoRemover(long idUnidade) {
        if (unidadeRepository.findCountUnidadeUso(idUnidade) > 0) {
            return false;
        } else {
            return true;
        }
    }  */
}