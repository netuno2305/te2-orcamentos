package br.edu.ifs.academico.repository;


import br.edu.ifs.academico.model.ConsultaLancamento;
import br.edu.ifs.academico.model.FonteRecursoModel;
import br.edu.ifs.academico.model.LancamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILancamentoRepository extends JpaRepository<LancamentoModel, Long> {
    

    @Query(value = """
        SELECT
        L.id,
        L.lancamento_invalido,
        L.numero_Lancamento,
        L.descricao,
        L.data_Lancamento,
        L.idLancamento_Pai,
        L.valor,
        TL.nome as idTipoLancamento,
        U.nome AS idUnidade,
        UO.nome AS idUnidadeOrcamentaria,
        P.nome AS idPrograma,
        A.nome AS idAcao,
        FR.nome AS idFonteRecurso,
        GD.nome AS idGrupoDespesa,
        MA.nome AS idModalidadeAplicacao,
        ED.nome AS idElementoDespesa,
        CASE WHEN S.nome IS NULL THEN 'Não Informado' ELSE S.nome END AS idSolicitante,
        CASE WHEN OE.nome IS NULL THEN 'Não Informado' ELSE OE.nome END AS idObjetivoEstrategico,
        TT.nome AS idTipoTransacao,
        L.GED,
        L.contratado,
        L.ano_Orcamento,
        L.data_cadastro
    FROM lancamento L
    JOIN TIPO_LANCAMENTO TL ON L.idTipo_Lancamento = TL.id
    JOIN UNIDADE U ON L.idUnidade = U.id
    JOIN UNIDADE_ORCAMENTARIA UO ON L.idUnidade_Orcamentaria = UO.id
    JOIN ELEMENTO_DESPESA ED ON L.idElemento_Despesa = ED.id
    JOIN ACAO A ON L.idAcao = A.id
    JOIN PROGRAMA P ON L.idPrograma = P.id
    LEFT JOIN SOLICITANTE S ON L.idSolicitante = S.id
    LEFT JOIN OBJETIVO_ESTRATEGICO OE ON L.idObjetivo_Estrategico = OE.id
    JOIN GRUPO_DESPESA GD ON L.idGrupo_Despesa = GD.id
    JOIN MODALIDADE_APLICACAO MA ON L.idModalidade_Aplicacao = MA.id
    JOIN TIPO_TRANSACAO TT ON L.idTipo_Transacao = TT.id
    JOIN FONTE_RECURSO FR ON L.idFonte_Recurso = FR.id
            """, nativeQuery = true)
    List<ConsultaLancamento> findLancamentos();
}
