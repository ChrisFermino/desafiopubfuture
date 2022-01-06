package br.com.pubfuture.desafiopubfuture.repositories;

import br.com.pubfuture.desafiopubfuture.models.entities.Contas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContasRepository  extends PagingAndSortingRepository<Contas, Integer> {

    @Query("SELECT SUM(c.saldo) FROM Contas c")
    Double saldoTotal();
}
