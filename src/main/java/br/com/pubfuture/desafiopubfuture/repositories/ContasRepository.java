package br.com.pubfuture.desafiopubfuture.repositories;

import br.com.pubfuture.desafiopubfuture.models.entities.Contas;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ContasRepository  extends PagingAndSortingRepository<Contas, Integer> {

    @Query("SELECT SUM(c.saldo) FROM Contas c")
    Double saldoTotal();

    @Query("SELECT c.saldo FROM Contas c WHERE c.id = :id")
    Double getSaldo(int id);

    @Transactional
    @Modifying
    @Query("UPDATE Contas c set c.saldo = :saldo WHERE c.id = :id")
    void editsaldo(int id, Double saldo);
}
