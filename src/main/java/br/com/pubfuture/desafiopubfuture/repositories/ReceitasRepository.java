package br.com.pubfuture.desafiopubfuture.repositories;

import br.com.pubfuture.desafiopubfuture.models.entities.Receitas;
import br.com.pubfuture.desafiopubfuture.utils.enums.TipoReceitaEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface ReceitasRepository extends PagingAndSortingRepository<Receitas, Integer> {

    Page<Receitas> findByDataRecebimentoBetween(Pageable page, Date from, Date to);

    Page<Receitas> findByTipoReceita(Pageable page, TipoReceitaEnum tipoReceita);

    @Query("SELECT  SUM(r.valor) FROM Receitas r")
    Double ReceitaTotal();
}
