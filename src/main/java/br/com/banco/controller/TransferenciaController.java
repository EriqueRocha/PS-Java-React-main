package br.com.banco.controller;

import br.com.banco.infra.handler.Response;
import br.com.banco.infra.handler.ResponseFactory;
import br.com.banco.model.tranferencia.TransferenciaEntity;
import br.com.banco.model.tranferencia.TransferenciaRequest;
import br.com.banco.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController("/transferencia")
@CrossOrigin(origins = "http://localhost:3000")
public class TransferenciaController {

    @Autowired
    private TransferenciaService service;

    @PostMapping("/cadastrar")
    public Response save(@RequestBody TransferenciaRequest request, Integer id){
        return ResponseFactory.ok(service.save(request, id));
    }

    @PutMapping("/editar/{id}")
    public Response update(@RequestBody TransferenciaRequest request, Integer id){
        return ResponseFactory.ok(service.update(request, id),"Alteração salva com sucesso","Você já pode ver as alterações");
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(Integer id){
        return ResponseFactory.delete(service.delete(id),"está transferência não se encontra mais na base de dados");
    }

    @GetMapping
    public Response list(){
        return ResponseFactory.ok(service.list());
    }

    @GetMapping("/escolher/{id}")
    public Response getOne(Integer id){
        return ResponseFactory.ok(service.getOne(id));
    }

    @GetMapping("/userId/{id}")
    public Response<List<TransferenciaEntity>> getTransferenciasPorIdConta(@PathVariable Integer id) {
        return ResponseFactory.ok(service.getTransferenciasPorIdConta(id));
    }

    @GetMapping("/userId/{id}/operador/{operador}")
    public Response<List<TransferenciaEntity>> getTransferenciasByUserIdAndOperador(
            @PathVariable("id") Integer id,
            @PathVariable("operador") String operador) {
        List<TransferenciaEntity> transferencias = service.getTransferenciasByContaIdAndNomeOperadorTransacao(id, operador);
        return ResponseFactory.ok(transferencias);
    }

    @GetMapping("/transferencias/{contaId}/{operador}")
    public Response<TransferenciaEntity> getTransferencias(
            @PathVariable("contaId") Integer contaId,
            @PathVariable("operador") String operador,
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
            return ResponseFactory.ok(service.getTransferenciasByContaIdAndNomeOperadorTransacaoAndDataTransferencia(
                    contaId, operador, dataInicio, dataFim));
    }

    @GetMapping("/transferencias/conta/{contaId}")
    public Response<List<TransferenciaEntity>> getTransferenciasByContaIdAndDataTransferencia(
            @PathVariable("contaId") Integer contaId,
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        if (contaId == null || dataInicio == null || dataFim == null) {
            throw new IllegalArgumentException("Os parâmetros não podem ser nulos");
        }
        return ResponseFactory.ok(service.getTransferenciasByContaIdAndDataTransferencia(contaId, dataInicio, dataFim));
    }
}
