package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.FonteRecursoModel;
import br.edu.ifs.academico.repository.IFonteRecursoRepository;
import br.edu.ifs.academico.rest.dto.FonteRecursoDto;
import br.edu.ifs.academico.rest.form.FonteRecurso.FonteRecursoForm;
import br.edu.ifs.academico.rest.form.FonteRecurso.UpdateFonteRecursoForm;
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
public class FonteRecursoService {
    IFonteRecursoRepository fonteRecursoRepository;

    public FonteRecursoDto insert(FonteRecursoForm fonteRecursoForm) {
        try {
            FonteRecursoModel fonteRecursoModelnovo = convertToModel(fonteRecursoForm);
            Optional<FonteRecursoModel> byId = fonteRecursoRepository.findById(fonteRecursoModelnovo.getID());

            if (byId.isPresent()) {
                throw new IllegalStateException("Id já registrado.");
            }

            fonteRecursoModelnovo = fonteRecursoRepository.save(fonteRecursoModelnovo);
            return convertToDto(fonteRecursoModelnovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Aluno não foi(foram) preenchido(s).");
        }


    }

    public FonteRecursoDto findById(long Id) {
        try {
            FonteRecursoModel fonteRecursoModel = fonteRecursoRepository.findById(Id).get();
            return convertToDto(fonteRecursoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + Id);
        }
    }

    public List<FonteRecursoDto> findAll(){
        List<FonteRecursoModel> fonteRecursoModelList = fonteRecursoRepository.findAll();
        return convertListToDto(fonteRecursoModelList);
    }

    public FonteRecursoDto update(UpdateFonteRecursoForm updateFonteRecursoForm, long Id) {
        try {
            Optional<FonteRecursoModel> fonteRecursoModelExistente = fonteRecursoRepository.findById(Id);
            if (fonteRecursoModelExistente.isPresent()) {
                FonteRecursoModel fonteRecursoAtualizado = fonteRecursoModelExistente.get();
                fonteRecursoAtualizado.setNome(updateFonteRecursoForm.getNome());
                fonteRecursoAtualizado.setCodigo(updateFonteRecursoForm.getCodigo());
                fonteRecursoAtualizado.setDataAlteracao(LocalDate.now());

                fonteRecursoRepository.save(fonteRecursoAtualizado);
                return convertToDto(fonteRecursoAtualizado);
            }else{
                throw new DataIntegrityException("O Id não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Fonte de Recursos não foi(foram) preenchido(s).");
        }
    }

    public void delete(long Id) {
        try {
            if (fonteRecursoRepository.existsById(Id)) {
                fonteRecursoRepository.deleteById(Id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Fonte de Recurso!");
        }
    }
    private FonteRecursoModel convertToModel(FonteRecursoForm fonteRecursoForm) {
        FonteRecursoModel fonteRecursoModel = new FonteRecursoModel();
        fonteRecursoModel.setCodigo(fonteRecursoForm.getCodigo());
        fonteRecursoModel.setNome(fonteRecursoForm.getNome());
        fonteRecursoModel.setDataCadastro(fonteRecursoForm.getDataCadastro());

        return fonteRecursoModel;
    }
    private FonteRecursoDto convertToDto(FonteRecursoModel fonteRecursoModel) {
        FonteRecursoDto fonteRecursoDto = new FonteRecursoDto();
        fonteRecursoDto.setCodigo(fonteRecursoModel.getCodigo());
        fonteRecursoDto.setNome(fonteRecursoModel.getNome());
        fonteRecursoDto.setDataCadastro(fonteRecursoModel.getDataCadastro());


        return fonteRecursoDto;
    }

    private List<FonteRecursoDto> convertListToDto(List<FonteRecursoModel> list){
        List<FonteRecursoDto> fonteRecursoDtoList = new ArrayList<>();
        for (FonteRecursoModel fonteRecursoModel : list) {
            FonteRecursoDto fonteRecursoDto = this.convertToDto(fonteRecursoModel);
            fonteRecursoDtoList.add(fonteRecursoDto);
        }
        return fonteRecursoDtoList;
    }
}
