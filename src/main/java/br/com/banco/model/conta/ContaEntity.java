package br.com.banco.model.conta;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "conta")
public class ContaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Integer idConta;

    private String nomeResponsavel;

}
