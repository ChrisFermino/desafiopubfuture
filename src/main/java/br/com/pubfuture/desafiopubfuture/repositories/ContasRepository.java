package br.com.pubfuture.desafiopubfuture.repositories;

import br.com.pubfuture.desafiopubfuture.models.Contas;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContasRepository  extends PagingAndSortingRepository<Contas, Integer> {
}
