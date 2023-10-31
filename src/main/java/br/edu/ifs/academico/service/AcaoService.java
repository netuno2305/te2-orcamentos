package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.AcaoModel;
import br.edu.ifs.academico.repository.IAcaoRepository;
import br.edu.ifs.academico.rest.dto.AcaoDto;
import br.edu.ifs.academico.rest.form.Acao.AcaoForm;
import br.edu.ifs.academico.rest.form.Acao.AcaoUpdateForm;
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
public class AcaoService {

    @Autowired
    IAcaoRepository acaoRepository;

    public AcaoDto findById(long idAcao) {
        try {
            AcaoModel acaoModel = acaoRepository.findById(idAcao).get();
            return convertAcaoModelToAcaoDto(acaoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Código: " + idAcao + ", Tipo: " + AcaoModel.class.getName());
        }
    }

    public List<AcaoDto> findAll(){
        List<AcaoModel> acaoList = acaoRepository.findAll();
        return convertListToDto(acaoList);
    }

    public AcaoDto insert(AcaoForm acaoForm) {
        try {
            AcaoModel acaoNovo = convertAcaoFormToAcaoModel(acaoForm);
            acaoNovo = acaoRepository.save(acaoNovo);
            return convertAcaoModelToAcaoDto(acaoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s)  não foi(foram) preenchido(s).");
        }
    }

    public AcaoDto update(AcaoUpdateForm acaoUpdateForm, long idAcao) {
        try {
            Optional<AcaoModel> acaoExistente = acaoRepository.findById(idAcao);
            if (acaoExistente.isPresent()) {
                AcaoModel acaoAtualizado = acaoExistente.get();
                acaoAtualizado.setNome(acaoUpdateForm.getNome());
                acaoAtualizado.setCodigo(acaoUpdateForm.getCodigo());
                acaoRepository.save(acaoAtualizado);
                return convertAcaoModelToAcaoDto(acaoAtualizado);
            }else{
                throw new DataIntegrityException("O ID  não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s)  não foi(foram) preenchido(s).");
        }
    }

    public void delete(long idAcao) {
        try {
            if (acaoRepository.existsById(idAcao)) {
                acaoRepository.deleteById(idAcao);
            }
        } catch (
                DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir");
        }
    }

    private AcaoModel convertAcaoFormToAcaoModel(AcaoForm acaoForm) {
        AcaoModel acaoModel = new AcaoModel();
        acaoModel.setCodigo(acaoForm.getCodigo());
        acaoModel.setNome(acaoForm.getNome());
        return acaoModel;
    }

    private AcaoDto convertAcaoModelToAcaoDto(AcaoModel acaoModel) {
        AcaoDto acaoDto = new AcaoDto();
        acaoDto.setCodigo(acaoModel.getCodigo());
        acaoDto.setNome(acaoModel.getNome());
        acaoDto.setId(acaoModel.getID());
        return acaoDto;
    }

    private List<AcaoDto> convertListToDto(List<AcaoModel> list){
        List<AcaoDto> acaoDtoList = new ArrayList<>();
        for (AcaoModel acaoModel : list) {
            AcaoDto acaoDto = this.convertAcaoModelToAcaoDto(acaoModel);
            acaoDtoList.add(acaoDto);
        }
        return acaoDtoList;
    }

    //Poderíamos usar esse método no DELETE... apenas adicionei como exemplo de validação de negócio.
    /* public boolean possoRemover(long idAcao) {
        if (acaoRepository.findCountAcaoUso(idAcao) > 0) {
            return false;
        } else {
            return true;
        }
    }  */
}