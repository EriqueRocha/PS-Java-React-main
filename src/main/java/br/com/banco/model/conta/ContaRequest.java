package br.com.banco.model.conta;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ContaRequest {

    @Schema(description = "Nome do usuário", example = "João da Silva")
    private String nomeResponsavel;

}
