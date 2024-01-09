package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.ObjetivoEstrategicoModel;
import br.edu.ifs.academico.repository.IObjetivoEstrategicoRepository;
import br.edu.ifs.academico.rest.dto.ObjetivoEstrategicoDto;
import br.edu.ifs.academico.rest.form.ObjetivoEstrategico.ObjetivoEstrategicoForm;
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
public class ObjetivoEstrategicoService {

    @Autowired
    IObjetivoEstrategicoRepository objetivoEstrategicoRepository;

    public ObjetivoEstrategicoDto insert(ObjetivoEstrategicoForm objetivoEstrategicoForm) {
        try {
            ObjetivoEstrategicoModel objetivoEstrategicoModel = convertToModel(objetivoEstrategicoForm);

            objetivoEstrategicoModel = objetivoEstrategicoRepository.save(objetivoEstrategicoModel);
            return convertToDto(objetivoEstrategicoModel);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Aluno não foi(foram) preenchido(s).");
        }


    }

    public ObjetivoEstrategicoDto findById(long Id) {
        try {
            ObjetivoEstrategicoModel objetivoEstrategicoModel = objetivoEstrategicoRepository.findById(Id).get();
            return convertToDto(objetivoEstrategicoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + Id);
        }
    }

    public List<ObjetivoEstrategicoDto> findAll(){
        List<ObjetivoEstrategicoModel> objetivoEstrategicoModels = objetivoEstrategicoRepository.findAll();
        return convertListToDto(objetivoEstrategicoModels);
    }

    public ObjetivoEstrategicoDto update(ObjetivoEstrategicoForm objetivoEstrategicoForm, long Id) {
        try {
            Optional<ObjetivoEstrategicoModel> modalidadeAplicacaoExistente = objetivoEstrategicoRepository.findById(Id);
            if (modalidadeAplicacaoExistente.isPresent()) {
                ObjetivoEstrategicoModel objetivoEstrategicoModel = modalidadeAplicacaoExistente.get();
                objetivoEstrategicoModel.setNome(objetivoEstrategicoForm.getNome());
                objetivoEstrategicoModel.setDataAlteracao(LocalDate.now());

                objetivoEstrategicoRepository.save(objetivoEstrategicoModel);
                return convertToDto(objetivoEstrategicoModel);
            }else{
                throw new DataIntegrityException("O Id não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    public void delete(long Id) {
        try {
            if (objetivoEstrategicoRepository.existsById(Id)) {
                objetivoEstrategicoRepository.deleteById(Id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir!");
        }
    }
    private ObjetivoEstrategicoModel convertToModel(ObjetivoEstrategicoForm objetivoEstrategicoForm) {
        ObjetivoEstrategicoModel objetivoEstrategicoModel = new ObjetivoEstrategicoModel();
        objetivoEstrategicoModel.setNome(objetivoEstrategicoForm.getNome());

        return objetivoEstrategicoModel;
    }
    private ObjetivoEstrategicoDto convertToDto(ObjetivoEstrategicoModel objetivoEstrategicoModel) {
        ObjetivoEstrategicoDto objetivoEstrategicoDto = new ObjetivoEstrategicoDto();
        objetivoEstrategicoDto.setNome(objetivoEstrategicoModel.getNome());
        objetivoEstrategicoDto.setDataCadastro(objetivoEstrategicoModel.getDataCadastro());
        objetivoEstrategicoDto.setId(objetivoEstrategicoModel.getID());

        return objetivoEstrategicoDto;
    }

    private List<ObjetivoEstrategicoDto> convertListToDto(List<ObjetivoEstrategicoModel> list){
        List<ObjetivoEstrategicoDto> objetivoEstrategicoDtoList = new ArrayList<>();
        for (ObjetivoEstrategicoModel objetivoEstrategicoModel : list) {
            ObjetivoEstrategicoDto objetivoEstrategicoDto = this.convertToDto(objetivoEstrategicoModel);
            objetivoEstrategicoDtoList.add(objetivoEstrategicoDto);
        }
        return objetivoEstrategicoDtoList;
    }
}
