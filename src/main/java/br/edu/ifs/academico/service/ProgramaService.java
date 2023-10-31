package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.ProgramaModel;
import br.edu.ifs.academico.repository.IProgramaRepository;
import br.edu.ifs.academico.rest.dto.ProgramaDto;
import br.edu.ifs.academico.rest.form.Programa.ProgramaForm;
import br.edu.ifs.academico.rest.form.Programa.ProgramaUpdateForm;
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
public class ProgramaService {

    @Autowired
    IProgramaRepository programaRepository;

    public ProgramaDto findById(long idPrograma) {
        try {
            ProgramaModel programaModel = programaRepository.findById(idPrograma).get();
            return convertProgramaModelToProgramaDto(programaModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Código: " + idPrograma + ", Tipo: " + ProgramaModel.class.getName());
        }
    }

    public List<ProgramaDto> findAll(){
        List<ProgramaModel> programaList = programaRepository.findAll();
        return convertListToDto(programaList);
    }

    public ProgramaDto insert(ProgramaForm programaForm) {
        try {
            ProgramaModel programaNovo = convertProgramaFormToProgramaModel(programaForm);
            programaNovo = programaRepository.save(programaNovo);
            return convertProgramaModelToProgramaDto(programaNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s)  não foi(foram) preenchido(s).");
        }
    }

    public ProgramaDto update(ProgramaUpdateForm programaUpdateForm, long idPrograma) {
        try {
            Optional<ProgramaModel> programaExistente = programaRepository.findById(idPrograma);
            if (programaExistente.isPresent()) {
                ProgramaModel programaAtualizado = programaExistente.get();
                programaAtualizado.setNome(programaUpdateForm.getNome());
                programaAtualizado.setCodigo(programaUpdateForm.getCodigo());
                programaRepository.save(programaAtualizado);
                return convertProgramaModelToProgramaDto(programaAtualizado);
            }else{
                throw new DataIntegrityException("O ID  não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s)  não foi(foram) preenchido(s).");
        }
    }

    public void delete(long idPrograma) {
        try {
            if (programaRepository.existsById(idPrograma)) {
                programaRepository.deleteById(idPrograma);
            }
        } catch (
                DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir");
        }
    }

    private ProgramaModel convertProgramaFormToProgramaModel(ProgramaForm programaForm) {
        ProgramaModel programaModel = new ProgramaModel();
        programaModel.setCodigo(programaForm.getCodigo());
        programaModel.setNome(programaForm.getNome());
        return programaModel;
    }

    private ProgramaDto convertProgramaModelToProgramaDto(ProgramaModel programaModel) {
        ProgramaDto programaDto = new ProgramaDto();
        programaDto.setCodigo(programaModel.getCodigo());
        programaDto.setNome(programaModel.getNome());
        programaDto.setId(programaModel.getID());
        return programaDto;
    }

    private List<ProgramaDto> convertListToDto(List<ProgramaModel> list){
        List<ProgramaDto> programaDtoList = new ArrayList<>();
        for (ProgramaModel programaModel : list) {
            ProgramaDto programaDto = this.convertProgramaModelToProgramaDto(programaModel);
            programaDtoList.add(programaDto);
        }
        return programaDtoList;
    }

    //Poderíamos usar esse método no DELETE... apenas adicionei como exemplo de validação de negócio.
    /* public boolean possoRemover(long idPrograma) {
        if (programaRepository.findCountProgramaUso(idPrograma) > 0) {
            return false;
        } else {
            return true;
        }
    }  */
}