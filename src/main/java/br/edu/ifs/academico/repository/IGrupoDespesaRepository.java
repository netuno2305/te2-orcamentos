package br.edu.ifs.academico.repository;

import br.edu.ifs.academico.model.FonteRecursoModel;
import br.edu.ifs.academico.model.GrupoDespesaModel;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IGrupoDespesaRepository {

    void Save(GrupoDespesaModel grupoDespesaModel);
    List<GrupoDespesaModel> findAll();
    GrupoDespesaModel findById(Long id);
    GrupoDespesaModel update(GrupoDespesaModel grupoDespesaModel);
    void Delete(Long id);
}
