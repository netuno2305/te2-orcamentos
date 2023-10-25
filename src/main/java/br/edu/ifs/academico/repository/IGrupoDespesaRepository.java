package br.edu.ifs.academico.repository;

import br.edu.ifs.academico.model.FonteRecursoModel;
import br.edu.ifs.academico.model.GrupoDespesaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IGrupoDespesaRepository extends JpaRepository<GrupoDespesaModel, Long> {


}
