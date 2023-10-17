package br.edu.ifs.academico.repository;

import br.edu.ifs.academico.model.UnidadeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUnidadeRepository extends JpaRepository<UnidadeModel, Long> {

    /***************************************************
     Outras formas de obtenção de dados "SELECT"
     ***************************************************/

}
