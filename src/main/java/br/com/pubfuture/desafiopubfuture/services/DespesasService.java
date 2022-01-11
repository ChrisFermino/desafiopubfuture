package br.com.pubfuture.desafiopubfuture.services;

import br.com.pubfuture.desafiopubfuture.core.exceptions.ObjectNotFound;
import br.com.pubfuture.desafiopubfuture.core.exceptions.WrongParameter;
import br.com.pubfuture.desafiopubfuture.models.dto.DespesaTotalDto;
import br.com.pubfuture.desafiopubfuture.models.entities.Despesas;
import br.com.pubfuture.desafiopubfuture.repositories.DespesasRepository;
import br.com.pubfuture.desafiopubfuture.utils.DateParse;
import br.com.pubfuture.desafiopubfuture.utils.enums.TipoDespesaEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.Optional;

@Service
public class DespesasService {

    @Autowired
    private DespesasRepository despesasRepository;

    public Despesas save(Despesas despesas) {
        return despesasRepository.save(despesas);
    }

    public Despesas edit(Despesas despesas, int id) {
        if (despesas.getId() != id) {
            throw new WrongParameter("o campo id não pode ser alterado!");
        }
        findById(id);
        return this.save(despesas);
    }

    public void deleteById(@PathVariable int id) {
        findById(id);
        despesasRepository.deleteById(id);
    }

    public Page<Despesas> findByTipo(TipoDespesaEnum tipoDespesa, int pageNumber, int pageSize) {
        if (pageSize > 10) pageSize = 10;
        Pageable page = PageRequest.of(pageNumber, pageSize);
        if (despesasRepository.findByTipoDespesa(page, tipoDespesa).isEmpty()) {
            throw new ObjectNotFound("Não existem despesas desse tipo");
        }
        return despesasRepository.findByTipoDespesa(page, tipoDespesa);
    }

    public Page<Despesas> findByDateBetween(String from, String to, int pageNumber, int pageSize) {
        if (pageSize > 10) pageSize = 10;
        Pageable page = PageRequest.of(pageNumber, pageSize);
        DateParse dateParse = new DateParse();
        Date dateFrom = dateParse.dateparse(from);
        Date dateTo = dateParse.dateparse(to);

        return despesasRepository.findByDataPagamentoBetween(dateFrom, dateTo, page);
    }

    public DespesaTotalDto despesaTotal() {
        return new DespesaTotalDto(despesasRepository.DespesaTotal());
    }

    public Optional<Despesas> findById(int id) {
        Optional<Despesas> despesasOptional = despesasRepository.findById(id);
        validDespesasExists(despesasOptional);
        return despesasOptional;
    }

    public void validDespesasExists(Optional<Despesas> despesasOptional) {
        if (despesasOptional.isEmpty()) {
            throw new ObjectNotFound("Despesa Inexistente!");
        }
    }
}
