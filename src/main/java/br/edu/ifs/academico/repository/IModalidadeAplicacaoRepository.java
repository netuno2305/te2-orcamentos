package br.edu.ifs.academico.repository;

import br.edu.ifs.academico.model.FonteRecursoModel;
import br.edu.ifs.academico.model.ModalidadeAplicacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IModalidadeAplicacaoRepository extends JpaRepository<ModalidadeAplicacaoModel, Long> {


}
