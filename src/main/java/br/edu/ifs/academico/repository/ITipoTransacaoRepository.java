package br.edu.ifs.academico.repository;


import br.edu.ifs.academico.model.FonteRecursoModel;
import br.edu.ifs.academico.model.TipoTransacaoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITipoTransacaoRepository {
    void Save(TipoTransacaoModel tipoTransacaoModel);
    List<TipoTransacaoModel> findAll();
    TipoTransacaoModel findById(Long id);
    TipoTransacaoModel update(TipoTransacaoModel tipoTransacaoModel);
    void Delete(Long id);
}
