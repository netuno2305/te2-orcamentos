package br.edu.ifs.academico.repository;

import br.edu.ifs.academico.model.UnidadeModel;
import br.edu.ifs.academico.model.GeneroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UnidadeRepository extends JpaRepository<UnidadeModel, Long> {

    /***************************************************
     Outras formas de obtenção de dados "SELECT"
     ***************************************************/

}
