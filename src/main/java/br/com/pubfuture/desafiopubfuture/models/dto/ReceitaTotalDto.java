package br.com.pubfuture.desafiopubfuture.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceitaTotalDto {

    private double valor;

    public ReceitaTotalDto(double valor) {
        this.valor = valor;
    }
}
