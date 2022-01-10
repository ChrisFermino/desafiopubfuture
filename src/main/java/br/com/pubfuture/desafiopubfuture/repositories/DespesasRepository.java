package br.com.pubfuture.desafiopubfuture.repositories;

import br.com.pubfuture.desafiopubfuture.models.dto.DespesaTotalDto;
import br.com.pubfuture.desafiopubfuture.models.entities.Despesas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface DespesasRepository extends PagingAndSortingRepository<Despesas, Integer> {

    Page<Despesas> findByDataPagamentoBetween(Date from, Date to, Pageable page);

    Page<Despesas> findByTipoDespesa(Pageable page, String tipoDespesa);

    @Query("SELECT SUM (d.valor) FROM Despesas d")
    DespesaTotalDto DespesaTotal();
}
