package br.edu.ifs.academico.service;
import br.edu.ifs.academico.model.ModalidadeAplicacaoModel;
import br.edu.ifs.academico.repository.IModalidadeAplicacaoRepository;
import br.edu.ifs.academico.rest.dto.ModalidadeAplicacaoDto;
import br.edu.ifs.academico.rest.form.ModalidadeAplicacao.ModalidadeAplicacaoForm;
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
public class ModalidadeAplicacaoService {

    @Autowired
    IModalidadeAplicacaoRepository modalidadeAplicacaoRepository;

    public ModalidadeAplicacaoDto insert(ModalidadeAplicacaoForm modalidadeAplicacaoForm) {
        try {
            ModalidadeAplicacaoModel modalidadeAplicacaoModel = convertToModel(modalidadeAplicacaoForm);


            modalidadeAplicacaoModel = modalidadeAplicacaoRepository.save(modalidadeAplicacaoModel);
            return convertToDto(modalidadeAplicacaoModel);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Aluno não foi(foram) preenchido(s).");
        }


    }

    public ModalidadeAplicacaoDto findById(long Id) {
        try {
            ModalidadeAplicacaoModel modalidadeAplicacaoModel = modalidadeAplicacaoRepository.findById(Id).get();
            return convertToDto(modalidadeAplicacaoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + Id);
        }
    }

    public List<ModalidadeAplicacaoDto> findAll(){
        List<ModalidadeAplicacaoModel> modalidadeAplicacaoModels = modalidadeAplicacaoRepository.findAll();
        return convertListToDto(modalidadeAplicacaoModels);
    }

    public ModalidadeAplicacaoDto update(ModalidadeAplicacaoForm modalidadeAplicacaoForm, long Id) {
        try {
            Optional<ModalidadeAplicacaoModel> modalidadeAplicacaoExistente = modalidadeAplicacaoRepository.findById(Id);
            if (modalidadeAplicacaoExistente.isPresent()) {
                ModalidadeAplicacaoModel modalidadeAplicacaoModel = modalidadeAplicacaoExistente.get();
                modalidadeAplicacaoModel.setNome(modalidadeAplicacaoForm.getNome());
                modalidadeAplicacaoModel.setCodigo(modalidadeAplicacaoForm.getCodigo());
                modalidadeAplicacaoModel.setDataAlteracao(LocalDate.now());

                modalidadeAplicacaoRepository.save(modalidadeAplicacaoModel);
                return convertToDto(modalidadeAplicacaoModel);
            }else{
                throw new DataIntegrityException("O Id não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    public void delete(long Id) {
        try {
            if (modalidadeAplicacaoRepository.existsById(Id)) {
                modalidadeAplicacaoRepository.deleteById(Id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir!");
        }
    }
    private ModalidadeAplicacaoModel convertToModel(ModalidadeAplicacaoForm modalidadeAplicacaoForm) {
        ModalidadeAplicacaoModel modalidadeAplicacaoModel = new ModalidadeAplicacaoModel();
        modalidadeAplicacaoModel.setCodigo(modalidadeAplicacaoForm.getCodigo());
        modalidadeAplicacaoModel.setNome(modalidadeAplicacaoForm.getNome());

        return modalidadeAplicacaoModel;
    }
    private ModalidadeAplicacaoDto convertToDto(ModalidadeAplicacaoModel modalidadeAplicacaoModel) {
        ModalidadeAplicacaoDto modalidadeAplicacaoDto = new ModalidadeAplicacaoDto();
        modalidadeAplicacaoDto.setCodigo(modalidadeAplicacaoModel.getCodigo());
        modalidadeAplicacaoDto.setId(modalidadeAplicacaoModel.getID());
        modalidadeAplicacaoDto.setNome(modalidadeAplicacaoModel.getNome());
        modalidadeAplicacaoDto.setDataCadastro(modalidadeAplicacaoModel.getDataCadastro());


        return modalidadeAplicacaoDto;
    }

    private List<ModalidadeAplicacaoDto> convertListToDto(List<ModalidadeAplicacaoModel> list){
        List<ModalidadeAplicacaoDto> modalidadeAplicacaoDtoList = new ArrayList<>();
        for (ModalidadeAplicacaoModel modalidadeAplicacaoModel : list) {
            ModalidadeAplicacaoDto modalidadeAplicacaoDto = this.convertToDto(modalidadeAplicacaoModel);
            modalidadeAplicacaoDtoList.add(modalidadeAplicacaoDto);
        }
        return modalidadeAplicacaoDtoList;
    }
}
