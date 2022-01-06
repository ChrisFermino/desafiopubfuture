package br.com.pubfuture.desafiopubfuture.repositories;

import br.com.pubfuture.desafiopubfuture.models.entities.Receitas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.Optional;

public interface ReceitasRepository extends PagingAndSortingRepository<Receitas, Integer> {

    Optional<Receitas> findByDataRecebimentoBetween(Date from, Date to);

    Optional<Receitas> findByTipoReceita(String tipoReceita);

    @Query("SELECT  SUM(r.valor) FROM Receitas r")
    Double ReceitaTotal();
}
