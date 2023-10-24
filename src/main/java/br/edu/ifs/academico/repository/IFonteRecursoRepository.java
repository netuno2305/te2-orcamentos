package br.edu.ifs.academico.repository;

import br.edu.ifs.academico.model.FonteRecursoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFonteRecursoRepository {

    void Save(FonteRecursoModel fonteRecursoModel);
    List<FonteRecursoModel> findAll();
    FonteRecursoModel findById(Long id);
    FonteRecursoModel update(FonteRecursoModel fonteRecursoModel);
    void Delete(Long id);


}
