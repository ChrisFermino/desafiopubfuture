package br.com.pubfuture.desafiopubfuture.models.entities;

import br.com.pubfuture.desafiopubfuture.utils.enums.TipoDespesaEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @Column(name = "valor")
    @Min(0)
    private double valor;

    @NotNull(message = "Campo dataPagamento vazio")
    @Column(name = "dataPagamento")
    private Date dataPagamento;

    @NotNull(message = "Campo dataRecebimentoEsperado vazio")
    @Column(name = "dataPagamentoEsperado")
    private Date dataPagamentoEsperado;

    @NotNull(message = "Campo tipoDespesa vazio")
    @Column(name = "tipoDespesa")
    private TipoDespesaEnum tipoDespesa;

    @Column(name = "idConta")
    private int idConta;

}
