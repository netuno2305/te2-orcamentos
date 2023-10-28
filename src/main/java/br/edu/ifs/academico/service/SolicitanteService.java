package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.SolicitanteModel;
import br.edu.ifs.academico.repository.ISolicitanteRepository;
import br.edu.ifs.academico.rest.dto.SolicitanteDto;
import br.edu.ifs.academico.rest.form.Solicitante.SolicitanteForm;
import br.edu.ifs.academico.service.exceptions.DataIntegrityException;
import br.edu.ifs.academico.service.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SolicitanteService {

    ISolicitanteRepository solicitanteRepository;
    public SolicitanteDto insert(SolicitanteForm solicitanteForm) {
        try {
            SolicitanteModel solicitanteModel = convertToModel(solicitanteForm);
            Optional<SolicitanteModel> byId = solicitanteRepository.findById(solicitanteModel.getID());

            if (byId.isPresent()) {
                throw new IllegalStateException("Id já registrado.");
            }

            solicitanteModel = solicitanteRepository.save(solicitanteModel);
            return convertToDto(solicitanteModel);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Aluno não foi(foram) preenchido(s).");
        }


    }

    public SolicitanteDto findById(long Id) {
        try {
            SolicitanteModel solicitanteModel = solicitanteRepository.findById(Id).get();
            return convertToDto(solicitanteModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + Id);
        }
    }

    public List<SolicitanteDto> findAll(){
        List<SolicitanteModel> solicitanteModels = solicitanteRepository.findAll();
        return convertListToDto(solicitanteModels);
    }

    public SolicitanteDto update(SolicitanteForm solicitanteForm, long Id) {
        try {
            Optional<SolicitanteModel> modalidadeAplicacaoExistente = solicitanteRepository.findById(Id);
            if (modalidadeAplicacaoExistente.isPresent()) {
                SolicitanteModel solicitanteModel = modalidadeAplicacaoExistente.get();
                solicitanteModel.setNome(solicitanteForm.getNome());
                solicitanteModel.setDataAlteracao(LocalDate.now());

                solicitanteRepository.save(solicitanteModel);
                return convertToDto(solicitanteModel);
            }else{
                throw new DataIntegrityException("O Id não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    public void delete(long Id) {
        try {
            if (solicitanteRepository.existsById(Id)) {
                solicitanteRepository.deleteById(Id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir!");
        }
    }
    private SolicitanteModel convertToModel(SolicitanteForm solicitanteForm) {
        SolicitanteModel solicitanteModel = new SolicitanteModel();
        solicitanteModel.setNome(solicitanteForm.getNome());
        solicitanteModel.setDataCadastro(solicitanteForm.getDataCadastro());

        return solicitanteModel;
    }
    private SolicitanteDto convertToDto(SolicitanteModel solicitanteModel) {
        SolicitanteDto solicitanteDto = new SolicitanteDto();
        solicitanteDto.setNome(solicitanteModel.getNome());
        solicitanteDto.setDataCadastro(solicitanteModel.getDataCadastro());


        return solicitanteDto;
    }

    private List<SolicitanteDto> convertListToDto(List<SolicitanteModel> list){
        List<SolicitanteDto> solicitanteDtoList = new ArrayList<>();
        for (SolicitanteModel solicitanteModel : list) {
            SolicitanteDto solicitanteDto = this.convertToDto(solicitanteModel);
            solicitanteDtoList.add(solicitanteDto);
        }
        return solicitanteDtoList;
    }
}
