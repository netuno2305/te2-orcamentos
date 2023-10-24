package br.edu.ifs.academico.repository;

import br.edu.ifs.academico.model.FonteRecursoModel;
import br.edu.ifs.academico.model.SolicitanteModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISolicitanteRepository {
    void Save(SolicitanteModel solicitanteModel);
    List<SolicitanteModel> findAll();
    SolicitanteModel findById(Long id);
    SolicitanteModel update(SolicitanteModel solicitanteModel);
    void Delete(Long id);
}
