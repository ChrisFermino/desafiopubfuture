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
@Table(name = "receitas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Receitas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Campo valor vazio")
    @NotBlank(message = "Campo valor em branco")
    @Column(name = "valor")
    private double valor;

    @NotNull(message = "Campo dataRecebimento vazio")
    @Column(name = "dataRecebimento")
    private Date dataRecebimento;

    @NotNull(message = "Campo dataRecebimentoEsperado vazio")
    @Column(name = "dataRecebimentoEsperado")
    private Date dataRecebimentoEsperado;

    @NotNull(message = "Campo descricao vazio")
    @NotBlank(message = "Campo descricao em branco")
    @Column(name = "descricao")
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Campo conta vazio")
    @NotBlank(message = "Campo conta em branco")
//    @Column(name = "conta")
    private Contas conta;

    @NotNull(message = "Campo tipoReceita vazio")
    @NotBlank(message = "Campo tipoReceita em branco")
    @Column(name = "tipoReceita")
    private String tipoReceita;


}
