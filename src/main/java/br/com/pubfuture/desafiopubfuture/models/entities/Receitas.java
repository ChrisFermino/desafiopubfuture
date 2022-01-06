package br.com.pubfuture.desafiopubfuture.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @Column(name = "valor")
    @Min(0)
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

    @NotNull(message = "Campo tipoReceita vazio")
    @NotBlank(message = "Campo tipoReceita em branco")
    @Column(name = "tipoReceita")
    private String tipoReceita;


}
