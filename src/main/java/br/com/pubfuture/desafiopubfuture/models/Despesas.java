package br.com.pubfuture.desafiopubfuture.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "despesas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Despesas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Campo valor vazio")
    @NotBlank(message = "Campo valor em branco")
    @Column(name = "valor")
    private double valor;

    @NotNull(message = "Campo dataRecebimentoEsperado vazio")
    @Column(name = "dataPagamento")
    private Date dataPagamento;

    @NotNull(message = "Campo dataRecebimentoEsperado vazio")
    @Column(name = "dataPagamentoEsperado")
    private Date dataPagamentoEsperado;

    @NotNull(message = "Campo tipoDespesa vazio")
    @NotBlank(message = "Campo tipoDespesa em branco")
    @Column(name = "tipoDespesa")
    private String tipoDespesa;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Campo conta vazio")
    @NotBlank(message = "Campo conta em branco")
//    @Column(name = "conta")
    private Contas conta;
}
