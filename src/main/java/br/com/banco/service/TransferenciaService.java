package br.com.banco.service;

import br.com.banco.infra.handler.exception.RecordNotFoundException;
import br.com.banco.infra.handler.exception.RequiredFieldException;
import br.com.banco.model.tranferencia.TransferenciaEntity;
import br.com.banco.model.tranferencia.TransferenciaRequest;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository repository;

    @Autowired
    private ContaRepository contaRepository;

    public Object save(TransferenciaRequest request, Integer uId) {
        return this.persist(null, request, uId);
    }

    public Object update(TransferenciaRequest request, Integer id){
        return this.persist(id, request, null);
    }

    public Object delete(Integer id) {
        TransferenciaEntity entity = findById(id);
        if (id.intValue() == entity.getId()){
            repository.deleteById(id);
            return entity;
        }else throw new RecordNotFoundException("transferência","id");
    }

    public List<TransferenciaEntity> list() {
        return repository.findAll();
    }

    public Object getOne(Integer id) {
        if (repository.existsById(id)){
            TransferenciaEntity entity = findById(id);
            return entity;
        }else throw new RecordNotFoundException("transferência","id");
    }
    private TransferenciaEntity findById(Integer id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Usuário", "ID"));
    }

    @Transactional
    public TransferenciaEntity persist(Integer id, TransferenciaRequest request, Integer uId){

        TransferenciaEntity entity = null;

        if(id!=null){entity = this.findById(id);

            if(request.getValor()==null || request.getValor().isNaN())
                throw new RequiredFieldException("Valor");
            if(request.getTipo()==null || request.getTipo().toString().isEmpty())
                throw new RequiredFieldException("Tipo");
        }else{
            if (request.getValor()==null || request.getValor().isNaN()
                    ||request.getTipo()==null
                    || request.getTipo().toString().isEmpty()
                    ||request.getNomeOperadorTransacao()==null
                    ||request.getValor()==null || request.getValor().isNaN()){
                throw new RequiredFieldException();
            }else {entity = new TransferenciaEntity();
                if (contaRepository.existsById(uId)){
                    entity.setConta(contaRepository.findAllByIdConta(uId));
                }else throw new RecordNotFoundException("Conta","id");
            }
        }
        BeanUtils.copyProperties(request,entity);
        repository.save(entity);

        return entity;
    }

    public List<TransferenciaEntity> getTransferenciasPorIdConta(Integer idConta) {
        if (contaRepository.existsById(idConta)){
            return repository.findAllByContaId(idConta);
        }else throw new RecordNotFoundException("Conta","id");

    }

    public List<TransferenciaEntity> getTransferenciasByContaIdAndNomeOperadorTransacao(Integer contaId, String nomeOperadorTransacao) {
        return repository.findAllByContaIdAndNomeOperadorTransacao(contaId, nomeOperadorTransacao);
    }

    public List<TransferenciaEntity> getTransferenciasByContaIdAndNomeOperadorTransacaoAndDataTransferencia(
            Integer contaId, String nomeOperadorTransacao, LocalDate dataInicio, LocalDate dataFim) {
        return repository.findAllByContaIdAndNomeOperadorTransacaoAndDataTransferencia(
                contaId, nomeOperadorTransacao, dataInicio, dataFim);
    }

    public List<TransferenciaEntity> getTransferenciasByContaIdAndDataTransferencia(
            Integer contaId, LocalDate dataInicio, LocalDate dataFim) {
        if (contaId == null || dataInicio == null || dataFim == null) {
            throw new IllegalArgumentException("Os parâmetros não podem ser nulos");
        }
        return repository.findAllByContaIdAndDataTransferenciaBetween(contaId, dataInicio, dataFim);
    }
}
