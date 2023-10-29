package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.ElementoDespesaModel;
import br.edu.ifs.academico.repository.IElementoDespesaRepository;
import br.edu.ifs.academico.rest.dto.ElementoDespesaDto;
import br.edu.ifs.academico.rest.form.ElementoDespesa.ElementoDespesaForm;
import br.edu.ifs.academico.rest.form.ElementoDespesa.ElementoDespesaUpdateForm;
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
public class ElementoDespesaService {

    @Autowired
    IElementoDespesaRepository elementoDespesaRepository;

    public ElementoDespesaDto findById(long idElementoDespesa) {
        try {
            ElementoDespesaModel elementoDespesaModel = elementoDespesaRepository.findById(idElementoDespesa).get();
            return convertElementoDespesaModelToElementoDespesaDto(elementoDespesaModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Código: " + idElementoDespesa + ", Tipo: " + ElementoDespesaModel.class.getName());
        }
    }

    public List<ElementoDespesaDto> findAll(){
        List<ElementoDespesaModel> elementoDespesaList = elementoDespesaRepository.findAll();
        return convertListToDto(elementoDespesaList);
    }

    public ElementoDespesaDto insert(ElementoDespesaForm elementoDespesaForm) {
        try {
            ElementoDespesaModel elementoDespesaNovo = convertElementoDespesaFormToElementoDespesaModel(elementoDespesaForm);
            elementoDespesaNovo = elementoDespesaRepository.save(elementoDespesaNovo);
            return convertElementoDespesaModelToElementoDespesaDto(elementoDespesaNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s)  não foi(foram) preenchido(s).");
        }
    }

    public ElementoDespesaDto update(ElementoDespesaUpdateForm elementoDespesaUpdateForm, long idElementoDespesa) {
        try {
            Optional<ElementoDespesaModel> elementoDespesaExistente = elementoDespesaRepository.findById(idElementoDespesa);
            if (elementoDespesaExistente.isPresent()) {
                ElementoDespesaModel elementoDespesaAtualizado = elementoDespesaExistente.get();
                elementoDespesaAtualizado.setNome(elementoDespesaUpdateForm.getNome());
                elementoDespesaAtualizado.setCodigo(elementoDespesaUpdateForm.getCodigo());
                elementoDespesaRepository.save(elementoDespesaAtualizado);
                return convertElementoDespesaModelToElementoDespesaDto(elementoDespesaAtualizado);
            }else{
                throw new DataIntegrityException("O ID  não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s)  não foi(foram) preenchido(s).");
        }
    }

    public void delete(long idElementoDespesa) {
        try {
            if (elementoDespesaRepository.existsById(idElementoDespesa)) {
                elementoDespesaRepository.deleteById(idElementoDespesa);
            }
        } catch (
                DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir");
        }
    }

    private ElementoDespesaModel convertElementoDespesaFormToElementoDespesaModel(ElementoDespesaForm elementoDespesaForm) {
        ElementoDespesaModel elementoDespesaModel = new ElementoDespesaModel();
        elementoDespesaModel.setCodigo(elementoDespesaForm.getCodigo());
        elementoDespesaModel.setNome(elementoDespesaForm.getNome());
        elementoDespesaModel.setDataCadastro(elementoDespesaForm.getDataCadastro());
        return elementoDespesaModel;
    }

    private ElementoDespesaDto convertElementoDespesaModelToElementoDespesaDto(ElementoDespesaModel elementoDespesaModel) {
        ElementoDespesaDto elementoDespesaDto = new ElementoDespesaDto();
        elementoDespesaDto.setCodigo(elementoDespesaModel.getCodigo());
        elementoDespesaDto.setNome(elementoDespesaModel.getNome());
        elementoDespesaDto.setId(elementoDespesaModel.getID());
        return elementoDespesaDto;
    }

    private List<ElementoDespesaDto> convertListToDto(List<ElementoDespesaModel> list){
        List<ElementoDespesaDto> elementoDespesaDtoList = new ArrayList<>();
        for (ElementoDespesaModel elementoDespesaModel : list) {
            ElementoDespesaDto elementoDespesaDto = this.convertElementoDespesaModelToElementoDespesaDto(elementoDespesaModel);
            elementoDespesaDtoList.add(elementoDespesaDto);
        }
        return elementoDespesaDtoList;
    }

    //Poderíamos usar esse método no DELETE... apenas adicionei como exemplo de validação de negócio.
    /* public boolean possoRemover(long idElementoDespesa) {
        if (elementoDespesaRepository.findCountElementoDespesaUso(idElementoDespesa) > 0) {
            return false;
        } else {
            return true;
        }
    }  */
}