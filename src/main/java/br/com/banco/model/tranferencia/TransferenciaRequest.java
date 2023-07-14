package br.com.banco.model.tranferencia;

import br.com.banco.enums.Tipo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;


@Data
public class TransferenciaRequest {

    @JsonIgnore
    @Schema(description="Data")
    private LocalDate dataTransferencia = LocalDate.now();

    @Schema(description="Data", example = "50,00")
    private Float valor;

    @Schema(description="Tipo", example = "DEPOSITO")
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Schema(description= "nome do operador da transação", example = "Marcos")
    private String nomeOperadorTransacao;

}
