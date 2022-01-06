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
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @Column(name = "saldo")
    private double saldo;

    @NotNull(message = "Campo tipoConta vazio")
    @NotBlank(message = "Campo tipoConta em branco")
    @Column(name = "tipoconta")
    private String tipoConta;

    @NotNull(message = "Campo instituicaoFinanceira vazio")
    @NotBlank(message = "Campo instituicaoFinanceira em branco")
    @Column(name = "instituicaofinanceira")
    private String instituicaoFinanceira;

    @OneToMany
    @JoinColumn(name = "despesas_id")
    private List<Despesas> despesas;

    @OneToMany
    @JoinColumn(name = "receitas_id")
    private List<Receitas> receitas;
}
