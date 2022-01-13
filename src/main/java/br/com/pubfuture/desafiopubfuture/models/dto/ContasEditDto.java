package br.com.pubfuture.desafiopubfuture.models.dto;

import br.com.pubfuture.desafiopubfuture.utils.enums.TipoContaEnum;
import lombok.Getter;

@Getter
public class ContasEditDto {

    private int id;
    private Double saldo;
    private TipoContaEnum tipoConta;
    private String instituicaoFinanceira;
}
