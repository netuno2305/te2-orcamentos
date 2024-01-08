package br.edu.ifs.academico.model;

import java.util.Date;

public interface ConsultaLancamento {
    //Interface criada para receber consulta dos lançamentos
    int getId();
    Boolean getLancamentoInvalido();
    int getNumeroLancamento();
    String getDescricao();
    String getDataLancamento();
    Integer getIdLancamentoPai();
    float getValor();
    String getIdTipoLancamento();
    String getIdUnidade();
    String getIdUnidadeOrcamentaria();
    String getIdPrograma();
    String getIdAcao();
    String getIdFonteRecurso();
    String getIdGrupoDespesa();
    String getIdModalidadeAplicacao();
    String getIdElementoDespesa();
    String getIdSolicitante();
    String getIdObjetivoEstrategico();
    String getIdTipoTransacao();
    String getGED();
    String getContratado();
    short getAnoOrcamento();
    Date getDataCadastro();
}
