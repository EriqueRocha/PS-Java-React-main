package br.com.banco.model.tranferencia;

import br.com.banco.enums.Tipo;
import br.com.banco.model.conta.ContaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
public class TransferenciaDTO {

    @JsonIgnore
    private LocalDate dataTransferencia = LocalDate.now();

    private Float valor;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private String nomeOperadorTransacao;

    private ContaEntity conta;

}
