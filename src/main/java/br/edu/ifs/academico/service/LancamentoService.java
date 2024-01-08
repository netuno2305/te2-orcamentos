package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.ConsultaLancamento;
import br.edu.ifs.academico.model.LancamentoModel;
import br.edu.ifs.academico.repository.ILancamentoRepository;
import br.edu.ifs.academico.rest.dto.LancamentoDto;
import br.edu.ifs.academico.rest.form.Lancamento.LancamentoForm;
import br.edu.ifs.academico.rest.form.Lancamento.LancamentoUpdateForm;
import br.edu.ifs.academico.service.exceptions.DataIntegrityException;
import br.edu.ifs.academico.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LancamentoService {

    @Autowired
    ILancamentoRepository lancamentoRepository;

    @Autowired
    ModelMapper modelMapper;

    public LancamentoDto findById(long idLancamento) {
        try {
            LancamentoModel lancamentoModel = lancamentoRepository.findById(idLancamento).get();
            return convertLancamentoModelToLancamentoDto(lancamentoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Código: " + idLancamento + ", Tipo: " + LancamentoModel.class.getName());
        }
    }

    public List<LancamentoDto> findAll() {
        List<ConsultaLancamento> consultaLancamentoList = lancamentoRepository.findLancamentos();

        return consultaLancamentoList.stream()
                .map(lancamento -> modelMapper.map(lancamento, LancamentoDto.class))
                .collect(Collectors.toList());
    }

    public LancamentoDto insert(LancamentoForm lancamentoForm) {
        try {
            LancamentoModel lancamentoNovo = convertLancamentoFormToLancamentoModel(lancamentoForm);
            lancamentoNovo = lancamentoRepository.save(lancamentoNovo);
            return convertLancamentoModelToLancamentoDto(lancamentoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Lancamento  não foi(foram) preenchido(s).");
        }
    }

    public LancamentoDto update(LancamentoUpdateForm lancamentoUpdateForm, long idLancamento) {
        try {
            Optional<LancamentoModel> lancamentoExistente = lancamentoRepository.findById(idLancamento);
            if (lancamentoExistente.isPresent()) {
                LancamentoModel lancamentoAtualizado = lancamentoExistente.get();
                lancamentoAtualizado.setDescricao(lancamentoUpdateForm.getDescricao());
                lancamentoAtualizado.setLancamentoInvalido(lancamentoUpdateForm.getLancamentoInvalido());
                lancamentoAtualizado.setGED(lancamentoUpdateForm.getGED());
                lancamentoAtualizado.setContratado(lancamentoUpdateForm.getDescricao());
                lancamentoRepository.save(lancamentoAtualizado);
                return convertLancamentoModelToLancamentoDto(lancamentoAtualizado);
            } else {
                throw new DataIntegrityException("O ID da Lancamento  não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Lancamento  não foi(foram) preenchido(s).");
        }
    }

    public void delete(long idLancamento) {
        try {
            if (lancamentoRepository.existsById(idLancamento)) {
                lancamentoRepository.deleteById(idLancamento);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir essa Lancamento ");
        }
    }

    private LancamentoModel convertLancamentoFormToLancamentoModel(LancamentoForm lancamentoForm) {
        LancamentoModel lancamentoModel = new LancamentoModel();
        lancamentoModel.setLancamentoInvalido(lancamentoForm.getLancamentoInvalido());
        lancamentoModel.setNumeroLancamento(lancamentoForm.getNumeroLancamento());
        lancamentoModel.setIDTipoLancamento(lancamentoForm.getIdTipoLancamento());
        lancamentoModel.setIDUnidade(lancamentoForm.getIdUnidade());
        lancamentoModel.setIDUnidadeOrcamentaria(lancamentoForm.getIdUnidadeOrcamentaria());
        lancamentoModel.setIDElementoDespesa(lancamentoForm.getIdElementoDespesa());
        lancamentoModel.setIDAcao(lancamentoForm.getIdAcao());
        lancamentoModel.setIDPrograma(lancamentoForm.getIdPrograma());
        lancamentoModel.setIDLancamentoPai(lancamentoForm.getIdLancamentoPai());
        lancamentoModel.setIDFonteRecurso(lancamentoForm.getIdFonteRecurso());
        lancamentoModel.setIDSolicitante(lancamentoForm.getIdSolicitante());
        lancamentoModel.setIDObjetivoEstrategico(lancamentoForm.getIdObjetivoEstrategico());
        lancamentoModel.setIDGrupoDespesa(lancamentoForm.getIdGrupoDespesa());
        lancamentoModel.setIDModalidadeAplicacao(lancamentoForm.getIdModalidadeAplicacao());
        lancamentoModel.setIDTipoTransacao(lancamentoForm.getIdTipoTransacao());
        lancamentoModel.setAnoOrcamento(lancamentoForm.getAnoOrcamento());
        lancamentoModel.setDescricao(lancamentoForm.getDescricao());
        lancamentoModel.setGED(lancamentoForm.getGED());
        lancamentoModel.setContratado(lancamentoForm.getContratado());
        lancamentoModel.setValor(lancamentoForm.getValor());
        lancamentoModel.setDataLancamento(lancamentoForm.getDataLancamento());

        return lancamentoModel;
    }

    private LancamentoDto convertLancamentoModelToLancamentoDto(LancamentoModel lancamentoModel) {
        LancamentoDto lancamentoDto = new LancamentoDto();
        lancamentoDto.setDescricao(lancamentoModel.getDescricao());
        lancamentoDto.setValor(lancamentoModel.getValor());
        lancamentoDto.setDataLancamento(lancamentoModel.getDataLancamento());
        lancamentoDto.setDataCadastro(lancamentoModel.getDataCadastro());
        lancamentoDto.setDataAlteracao(lancamentoModel.getDataAlteracao());
        lancamentoDto.setId(lancamentoModel.getID());
        return lancamentoDto;
    }

    private List<LancamentoDto> convertListToDto(List<LancamentoModel> list) {
        List<LancamentoDto> lancamentoDtoList = new ArrayList<>();
        for (LancamentoModel lancamentoModel : list) {
            LancamentoDto lancamentoDto = this.convertLancamentoModelToLancamentoDto(lancamentoModel);
            lancamentoDtoList.add(lancamentoDto);
        }
        return lancamentoDtoList;
    }

}