package br.com.pubfuture.desafiopubfuture.repositories;

import br.com.pubfuture.desafiopubfuture.models.dto.ReceitaTotalDto;
import br.com.pubfuture.desafiopubfuture.models.entities.Receitas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.Optional;

public interface ReceitasRepository extends PagingAndSortingRepository<Receitas, Integer> {

    Page<Receitas> findByDataRecebimentoBetween(Pageable page, Date from, Date to);

    Page<Receitas> findByTipoReceita(Pageable page, String tipoReceita);

    @Query("SELECT  SUM(r.valor) FROM Receitas r")
    ReceitaTotalDto ReceitaTotal();
}
