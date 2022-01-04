package br.com.pubfuture.desafiopubfuture.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Campo saldo vazio")
    @NotBlank(message = "Campo saldo em branco")
    @Column(name = "saldo")
    private double saldo;

    @NotNull(message = "Campo tipoConta vazio")
    @NotBlank(message = "Campo tipoConta em branco")
    @Column(name = "tipoConta")
    private String tipoConta;

    @NotNull(message = "Campo instituicaoFinanceira vazio")
    @NotBlank(message = "Campo instituicaoFinanceira em branco")
    @Column(name = "instituicaoFinanceira")
    private String instituicaoFinanceira;
}
