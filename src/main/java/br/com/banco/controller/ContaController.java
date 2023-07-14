package br.com.banco.controller;

import br.com.banco.infra.handler.Response;
import br.com.banco.infra.handler.ResponseFactory;
import br.com.banco.model.conta.ContaEntity;
import br.com.banco.model.conta.ContaRequest;
import br.com.banco.repository.ContaRepository;
import br.com.banco.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/conta")
@CrossOrigin(origins = "http://localhost:3000")
public class ContaController {

    @Autowired
    private ContaService service;

    @Autowired
    private ContaRepository repository;

    @PostMapping("/cadastro")
    public Response save(@RequestBody ContaRequest request){
        return ResponseFactory.create(service.save(request), "Conta cadastrada com sucesso","Agora esta conta pode ser gerenciada pelo sistema");
    }

    @PutMapping("/editar{id}")
    public Response update(@RequestBody ContaRequest request, Integer id){
        return ResponseFactory.ok(service.update(request, id),"Conta alterada com sucesso","Você já pode ver as alterações");
    }

    @DeleteMapping("/deletar/{id}")
    public Response delete(Integer id){
        return ResponseFactory.delete(service.delete(id),"esta conta não se encontra mais na base de dados");
    }

    @GetMapping("/list")
    public Response<ContaEntity> getList(){
        return ResponseFactory.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public Response getOne(Integer id){
        return ResponseFactory.ok(service.getOne(id));
    }

}
