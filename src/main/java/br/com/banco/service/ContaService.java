package br.com.banco.service;

import br.com.banco.infra.handler.exception.RecordNotFoundException;
import br.com.banco.infra.handler.exception.RequiredFieldException;
import br.com.banco.model.conta.ContaEntity;
import br.com.banco.model.conta.ContaRequest;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    public Object save(ContaRequest request) {
        return this.persist(null, request);
    }

    public Object update(ContaRequest request, Integer id) {
        return this.persist(id, request);
    }

    public Object delete(Integer id) {
        ContaEntity entity = findById(id);
        if (id.intValue() == entity.getIdConta()){
            transferenciaRepository.deleteAll(transferenciaRepository.findAllByContaId(id));
            repository.deleteById(id);
        }else throw new RecordNotFoundException("Conta", "id");

        return entity;
    }

    public Object getOne(Integer id) {
        Optional<ContaEntity> contaOptional = repository.findById(id);

        if (contaOptional.isPresent()) {
            return contaOptional.get();
        } else {
            String error = "Não existe uma conta com o id informado";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    private ContaEntity findById(Integer id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Usuário", "ID"));
    }

    @Transactional
    public ContaEntity persist(Integer id, ContaRequest request){

        if(request.getNomeResponsavel()==null || request.getNomeResponsavel().isEmpty())
            throw new RequiredFieldException("Nome");

        ContaEntity entity = null;
        if(id!=null){
            entity = this.findById(id);
        }else
            entity = new ContaEntity();

        BeanUtils.copyProperties(request,entity);
        repository.save(entity);

        return entity;
    }



}
