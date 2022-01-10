package br.com.pubfuture.desafiopubfuture.models.dto;

import br.com.pubfuture.desafiopubfuture.models.entities.Contas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContasDto {

    private double saldo;
    private String tipoConta;
    private String instituicaoFinanceira;

    public ContasDto(Contas contas) {
        this.saldo = contas.getSaldo();
        this.tipoConta = contas.getTipoConta();
        this.instituicaoFinanceira = contas.getInstituicaoFinanceira();
    }
}
