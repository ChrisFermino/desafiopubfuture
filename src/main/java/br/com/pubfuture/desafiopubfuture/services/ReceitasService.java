package br.com.pubfuture.desafiopubfuture.services;

import br.com.pubfuture.desafiopubfuture.core.exceptions.ObjectNotFound;
import br.com.pubfuture.desafiopubfuture.core.exceptions.WrongParameter;
import br.com.pubfuture.desafiopubfuture.models.dto.ReceitaTotalDto;
import br.com.pubfuture.desafiopubfuture.models.entities.Receitas;
import br.com.pubfuture.desafiopubfuture.repositories.ReceitasRepository;
import br.com.pubfuture.desafiopubfuture.utils.DateParse;
import br.com.pubfuture.desafiopubfuture.utils.enums.TipoReceitaEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.Optional;

@Service
public class ReceitasService {

    @Autowired
    private ReceitasRepository receitasRepository;

    public Receitas save(Receitas receitas) {
        return receitasRepository.save(receitas);
    }

    public Receitas edit(Receitas receitas, int id) {
        if (receitas.getId() != id) {
            throw new WrongParameter("O campo id não pode ser alterado!");
        }
        findById(id);
        return receitasRepository.save(receitas);
    }

    public void deleteById(@PathVariable int id) {
        findById(id);
        receitasRepository.deleteById(id);
    }

    public Page<Receitas> findByTipo(TipoReceitaEnum tipoReceita, int pageNumber, int pageSize) {
        if (pageSize > 10) pageSize = 10;
        Pageable page = PageRequest.of(pageNumber, pageSize);
        if (receitasRepository.findByTipoReceita(page, tipoReceita).isEmpty()) {
            throw new ObjectNotFound("Não existem receitas com esse tipo!");
        }
        return receitasRepository.findByTipoReceita(page, tipoReceita);
    }

    public Page<Receitas> findByDateBetween(String from, String to, int pageNumber, int pageSize) {
        if (pageSize > 10) pageSize = 10;
        Pageable page = PageRequest.of(pageNumber, pageSize);
        DateParse dateParse = new DateParse();
        Date dateFrom = dateParse.dateparse(from);
        Date dateTo = dateParse.dateparse(to);

        return receitasRepository.findByDataRecebimentoBetween(page, dateFrom, dateTo);
    }

    public ReceitaTotalDto receitaTotal() {
        return new ReceitaTotalDto(receitasRepository.ReceitaTotal());
    }

    public Optional<Receitas> findById(int id) {
        Optional<Receitas> receitasOptional = receitasRepository.findById(id);
        validReceitasExists(receitasOptional);
        return receitasOptional;
    }

    private void validReceitasExists(Optional<Receitas> receitasOptional) {
        if (receitasOptional.isEmpty()) {
            throw new ObjectNotFound("Receita Inexistente!");
        }
    }
}
