package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.UnidadeOrcamentariaModel;
import br.edu.ifs.academico.repository.IUnidadeOrcamentariaRepository;
import br.edu.ifs.academico.rest.dto.UnidadeOrcamentariaDto;
import br.edu.ifs.academico.rest.form.UnidadeOrcamentaria.UnidadeOrcamentariaForm;
import br.edu.ifs.academico.rest.form.UnidadeOrcamentaria.UnidadeOrcamentariaUpdateForm;
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
public class UnidadeOrcamentariaService {

    @Autowired
    IUnidadeOrcamentariaRepository unidadeOrcamentariaRepository;

    public UnidadeOrcamentariaDto findById(long idUnidadeOrcamentaria) {
        try {
            UnidadeOrcamentariaModel unidadeOrcamentariaModel = unidadeOrcamentariaRepository.findById(idUnidadeOrcamentaria).get();
            return convertUnidadeOrcamentariaModelToUnidadeOrcamentariaDto(unidadeOrcamentariaModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Código: " + idUnidadeOrcamentaria + ", Tipo: " + UnidadeOrcamentariaModel.class.getName());
        }
    }

    public List<UnidadeOrcamentariaDto> findAll(){
        List<UnidadeOrcamentariaModel> unidadeOrcamentariaList = unidadeOrcamentariaRepository.findAll();
        return convertListToDto(unidadeOrcamentariaList);
    }

    public UnidadeOrcamentariaDto insert(UnidadeOrcamentariaForm unidadeOrcamentariaForm) {
        try {
            UnidadeOrcamentariaModel unidadeOrcamentariaNovo = convertUnidadeOrcamentariaFormToUnidadeOrcamentariaModel(unidadeOrcamentariaForm);
            unidadeOrcamentariaNovo = unidadeOrcamentariaRepository.save(unidadeOrcamentariaNovo);
            return convertUnidadeOrcamentariaModelToUnidadeOrcamentariaDto(unidadeOrcamentariaNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Unidade Orcamentaria não foi(foram) preenchido(s).");
        }
    }

    public UnidadeOrcamentariaDto update(UnidadeOrcamentariaUpdateForm unidadeOrcamentariaUpdateForm, long idUnidadeOrcamentaria) {
        try {
            Optional<UnidadeOrcamentariaModel> unidadeOrcamentariaExistente = unidadeOrcamentariaRepository.findById(idUnidadeOrcamentaria);
            if (unidadeOrcamentariaExistente.isPresent()) {
                UnidadeOrcamentariaModel unidadeOrcamentariaAtualizado = unidadeOrcamentariaExistente.get();
                unidadeOrcamentariaAtualizado.setNome(unidadeOrcamentariaUpdateForm.getNome());
                unidadeOrcamentariaAtualizado.setCodigo(unidadeOrcamentariaUpdateForm.getCodigo());
                unidadeOrcamentariaRepository.save(unidadeOrcamentariaAtualizado);
                return convertUnidadeOrcamentariaModelToUnidadeOrcamentariaDto(unidadeOrcamentariaAtualizado);
            }else{
                throw new DataIntegrityException("O ID da Unidade Orcamentaria não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Unidade Orcamentaria não foi(foram) preenchido(s).");
        }
    }

    public void delete(long idUnidadeOrcamentaria) {
        try {
            if (unidadeOrcamentariaRepository.existsById(idUnidadeOrcamentaria)) {
                unidadeOrcamentariaRepository.deleteById(idUnidadeOrcamentaria);
            }
        } catch (
                DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir essa Unidade Orcamentaria");
        }
    }

    private UnidadeOrcamentariaModel convertUnidadeOrcamentariaFormToUnidadeOrcamentariaModel(UnidadeOrcamentariaForm unidadeOrcamentariaForm) {
        UnidadeOrcamentariaModel unidadeOrcamentariaModel = new UnidadeOrcamentariaModel();
        unidadeOrcamentariaModel.setCodigo(unidadeOrcamentariaForm.getCodigo());
        unidadeOrcamentariaModel.setNome(unidadeOrcamentariaForm.getNome());
        unidadeOrcamentariaModel.setDataCadastro(unidadeOrcamentariaForm.getDataCadastro());
        return unidadeOrcamentariaModel;
    }

    private UnidadeOrcamentariaDto convertUnidadeOrcamentariaModelToUnidadeOrcamentariaDto(UnidadeOrcamentariaModel unidadeOrcamentariaModel) {
        UnidadeOrcamentariaDto unidadeOrcamentariaDto = new UnidadeOrcamentariaDto();
        unidadeOrcamentariaDto.setCodigo(unidadeOrcamentariaModel.getCodigo());
        unidadeOrcamentariaDto.setNome(unidadeOrcamentariaModel.getNome());
        unidadeOrcamentariaDto.setId(unidadeOrcamentariaModel.getID());
        return unidadeOrcamentariaDto;
    }

    private List<UnidadeOrcamentariaDto> convertListToDto(List<UnidadeOrcamentariaModel> list){
        List<UnidadeOrcamentariaDto> unidadeOrcamentariaDtoList = new ArrayList<>();
        for (UnidadeOrcamentariaModel unidadeOrcamentariaModel : list) {
            UnidadeOrcamentariaDto unidadeOrcamentariaDto = this.convertUnidadeOrcamentariaModelToUnidadeOrcamentariaDto(unidadeOrcamentariaModel);
            unidadeOrcamentariaDtoList.add(unidadeOrcamentariaDto);
        }
        return unidadeOrcamentariaDtoList;
    }

    //Poderíamos usar esse método no DELETE... apenas adicionei como exemplo de validação de negócio.
    /* public boolean possoRemover(long idUnidadeOrcamentaria) {
        if (unidadeOrcamentariaRepository.findCountUnidadeOrcamentariaUso(idUnidadeOrcamentaria) > 0) {
            return false;
        } else {
            return true;
        }
    }  */
}