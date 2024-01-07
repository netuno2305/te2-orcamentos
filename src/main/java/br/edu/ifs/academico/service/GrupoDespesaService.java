package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.FonteRecursoModel;
import br.edu.ifs.academico.model.GrupoDespesaModel;
import br.edu.ifs.academico.repository.IFonteRecursoRepository;
import br.edu.ifs.academico.repository.IGrupoDespesaRepository;
import br.edu.ifs.academico.rest.dto.FonteRecursoDto;
import br.edu.ifs.academico.rest.dto.GrupoDespesaDto;
import br.edu.ifs.academico.rest.form.FonteRecurso.FonteRecursoForm;
import br.edu.ifs.academico.rest.form.FonteRecurso.UpdateFonteRecursoForm;
import br.edu.ifs.academico.rest.form.GrupoDespesa.GrupoDespesaForm;
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
public class GrupoDespesaService {
    @Autowired
    IGrupoDespesaRepository grupoDespesaRepository;

    public GrupoDespesaDto insert(GrupoDespesaForm grupoDespesaForm) {
        try {
            GrupoDespesaModel grupoDespesaNovo = convertToModel(grupoDespesaForm);
            grupoDespesaNovo = grupoDespesaRepository.save(grupoDespesaNovo);
            return convertToDto(grupoDespesaNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Aluno não foi(foram) preenchido(s).");
        }


    }

    public GrupoDespesaDto findById(long Id) {
        try {
            GrupoDespesaModel grupoDespesaModel = grupoDespesaRepository.findById(Id).get();
            return convertToDto(grupoDespesaModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + Id);
        }
    }

    public List<GrupoDespesaDto> findAll(){
        List<GrupoDespesaModel> grupoDespesaModels = grupoDespesaRepository.findAll();
        return convertListToDto(grupoDespesaModels);
    }

    public GrupoDespesaDto update(GrupoDespesaForm grupoDespesaForm, long Id) {
        try {
            Optional<GrupoDespesaModel> grupoDespesaExistente = grupoDespesaRepository.findById(Id);
            if (grupoDespesaExistente.isPresent()) {
                GrupoDespesaModel grupoDespesaAtualizado = grupoDespesaExistente.get();
                grupoDespesaAtualizado.setNome(grupoDespesaForm.getNome());
                grupoDespesaAtualizado.setDataAlteracao(LocalDate.now());

                grupoDespesaRepository.save(grupoDespesaAtualizado);
                return convertToDto(grupoDespesaAtualizado);
            }else{
                throw new DataIntegrityException("O Id não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s).");
        }
    }

    public void delete(long Id) {
        try {
            if (grupoDespesaRepository.existsById(Id)) {
                grupoDespesaRepository.deleteById(Id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir!");
        }
    }
    private GrupoDespesaModel convertToModel(GrupoDespesaForm grupoDespesaForm) {
        GrupoDespesaModel grupoDespesaModel = new GrupoDespesaModel();
        grupoDespesaModel.setCodigo(grupoDespesaForm.getCodigo());
        grupoDespesaModel.setNome(grupoDespesaForm.getNome());

        return grupoDespesaModel;
    }
    private GrupoDespesaDto convertToDto(GrupoDespesaModel grupoDespesaModel) {
        GrupoDespesaDto grupoDespesaDto = new GrupoDespesaDto();
        grupoDespesaDto.setCodigo(grupoDespesaModel.getCodigo());
        grupoDespesaDto.setNome(grupoDespesaModel.getNome());
        grupoDespesaDto.setId(grupoDespesaModel.getID());


        return grupoDespesaDto;
    }

    private List<GrupoDespesaDto> convertListToDto(List<GrupoDespesaModel> list){
        List<GrupoDespesaDto> grupoDespesaDtoList = new ArrayList<>();
        for (GrupoDespesaModel grupoDespesaModel : list) {
            GrupoDespesaDto grupoDespesaDto = this.convertToDto(grupoDespesaModel);
            grupoDespesaDtoList.add(grupoDespesaDto);
        }
        return grupoDespesaDtoList;
    }
}
