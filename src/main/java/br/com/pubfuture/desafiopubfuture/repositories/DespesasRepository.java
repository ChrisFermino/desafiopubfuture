package br.com.pubfuture.desafiopubfuture.repositories;

import br.com.pubfuture.desafiopubfuture.models.entities.Despesas;
import br.com.pubfuture.desafiopubfuture.utils.enums.TipoDespesaEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface DespesasRepository extends PagingAndSortingRepository<Despesas, Integer> {

    Page<Despesas> findByDataPagamentoBetween(Date from, Date to, Pageable page);

    Page<Despesas> findByTipoDespesa(Pageable page, TipoDespesaEnum tipoDespesa);

    @Query("SELECT SUM (d.valor) FROM Despesas d")
    Double DespesaTotal();
}
