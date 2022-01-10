package br.com.pubfuture.desafiopubfuture.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DateSearcherDto {

    private Date fromDate;

    private Date toDate;


}
