package br.com.pubfuture.desafiopubfuture.models.entities;

import br.com.pubfuture.desafiopubfuture.models.dto.ContasDto;
import br.com.pubfuture.desafiopubfuture.models.dto.ContasEditDto;
import br.com.pubfuture.desafiopubfuture.utils.enums.TipoContaEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
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
    private Double saldo;

    @NotNull(message = "Campo tipoConta vazio")
    @Column(name = "tipoconta")
    private TipoContaEnum tipoConta;

    @NotNull(message = "Campo instituicaoFinanceira vazio")
    @NotBlank(message = "Campo instituicaoFinanceira em branco")
    @Column(name = "instituicaofinanceira")
    private String instituicaoFinanceira;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "idConta")
    private List<Despesas> despesas;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "idConta")
    private List<Receitas> receitas;

    public Contas(ContasDto contasDto) {
        this.saldo = contasDto.getSaldo();
        this.tipoConta = contasDto.getTipoConta();
        this.instituicaoFinanceira = contasDto.getInstituicaoFinanceira();
    }

    public Contas(ContasEditDto contasEditDto) {
        this.id = contasEditDto.getId();
        this.saldo = contasEditDto.getSaldo();
        this.tipoConta = contasEditDto.getTipoConta();
        this.instituicaoFinanceira = contasEditDto.getInstituicaoFinanceira();
    }
}
