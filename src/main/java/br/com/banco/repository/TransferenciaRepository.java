package br.com.banco.repository;

import br.com.banco.model.tranferencia.TransferenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity, Integer> {

    @Query("SELECT t FROM TransferenciaEntity t WHERE t.conta.idConta = :contaId")
    List<TransferenciaEntity> findAllByContaId(@Param("contaId") Integer contaId);

    @Query("SELECT t FROM TransferenciaEntity t WHERE t.conta.idConta = :contaId AND t.nomeOperadorTransacao = :nomeOperadorTransacao")
    List<TransferenciaEntity> findAllByContaIdAndNomeOperadorTransacao(@Param("contaId") Integer contaId, @Param("nomeOperadorTransacao") String nomeOperadorTransacao);

    @Query("SELECT t FROM TransferenciaEntity t WHERE t.conta.idConta = :contaId " +
            "AND (:operador IS NULL OR t.nomeOperadorTransacao = :operador) " +
            "AND t.dataTransferencia BETWEEN :dataInicio AND :dataFim")
    List<TransferenciaEntity> findAllByContaIdAndNomeOperadorTransacaoAndDataTransferencia(
            @Param("contaId") Integer contaId,
            @Param("operador") String operador,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim);

    @Query("SELECT t FROM TransferenciaEntity t WHERE t.conta.idConta = :contaId AND t.dataTransferencia BETWEEN :dataInicio AND :dataFim")
    List<TransferenciaEntity> findAllByContaIdAndDataTransferenciaBetween(
            @Param("contaId") Integer contaId,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim);
}
