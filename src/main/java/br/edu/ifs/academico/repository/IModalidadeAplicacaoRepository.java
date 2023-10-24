package br.edu.ifs.academico.repository;

import br.edu.ifs.academico.model.FonteRecursoModel;
import br.edu.ifs.academico.model.ModalidadeAplicacaoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IModalidadeAplicacaoRepository {

    void Save(ModalidadeAplicacaoModel modalidadeAplicacaoModel);
    List<ModalidadeAplicacaoModel> findAll();
    ModalidadeAplicacaoModel findById(Long id);
    ModalidadeAplicacaoModel update(ModalidadeAplicacaoModel modalidadeAplicacaoModel);
    void Delete(Long id);
}
