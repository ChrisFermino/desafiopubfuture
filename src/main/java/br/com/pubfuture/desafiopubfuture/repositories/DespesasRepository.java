package br.com.pubfuture.desafiopubfuture.repositories;

import br.com.pubfuture.desafiopubfuture.models.entities.Despesas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.Optional;

public interface DespesasRepository extends PagingAndSortingRepository<Despesas, Integer> {

    Optional<Despesas> findByDataPagamentoBetween(Date from, Date to);

    Optional<Despesas> findByTipoDespesa(String tipoDespesa);

    @Query("SELECT SUM (d.valor) FROM Despesas d")
    Double DespesaTotal();
}
