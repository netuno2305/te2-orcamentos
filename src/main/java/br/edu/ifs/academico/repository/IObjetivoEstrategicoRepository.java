package br.edu.ifs.academico.repository;

import br.edu.ifs.academico.model.FonteRecursoModel;
import br.edu.ifs.academico.model.ObjetivoEstrategicoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IObjetivoEstrategicoRepository extends JpaRepository<ObjetivoEstrategicoModel, Long> {

}
