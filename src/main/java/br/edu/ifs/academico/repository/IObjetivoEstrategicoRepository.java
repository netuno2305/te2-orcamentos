package br.edu.ifs.academico.repository;

import br.edu.ifs.academico.model.FonteRecursoModel;
import br.edu.ifs.academico.model.ObjetivoEstrategicoModel;

import java.util.List;

public interface IObjetivoEstrategicoRepository {
    void Save(ObjetivoEstrategicoModel objetivoEstrategicoModel);
    List<ObjetivoEstrategicoModel> findAll();
    ObjetivoEstrategicoModel findById(Long id);
    ObjetivoEstrategicoModel update(ObjetivoEstrategicoModel objetivoEstrategicoModel);
    void Delete(Long id);
}
