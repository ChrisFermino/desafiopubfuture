package br.com.pubfuture.desafiopubfuture.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DespesaTotalDto {

    private double valor;

    public DespesaTotalDto(double valor) {
        this.valor = valor;
    }
}
