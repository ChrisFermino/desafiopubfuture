package br.com.pubfuture.desafiopubfuture.services;

import br.com.pubfuture.desafiopubfuture.core.exceptions.ObjectNotFound;
import br.com.pubfuture.desafiopubfuture.core.exceptions.WrongParameter;
import br.com.pubfuture.desafiopubfuture.models.dto.ReceitaTotalDto;
import br.com.pubfuture.desafiopubfuture.models.entities.Receitas;
import br.com.pubfuture.desafiopubfuture.repositories.ReceitasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class ReceitasService {

    @Autowired
    private ReceitasRepository receitasRepository;

    public Receitas save(Receitas receitas) {
        switch (receitas.getTipoReceita()) {
            case "salario":
            case "outros":
            case "premio":
            case "presente":
                return receitasRepository.save(receitas);
        }
        throw new WrongParameter("o campo tipoReceita está incorreto");
    }

    public Receitas edit(Receitas receitas, int id) {
        if(receitas.getId() != id) {
            throw new WrongParameter("O campo id não pode ser alterado!");
        }
        findById(id);
        switch (receitas.getTipoReceita()) {
            case "salario":
            case "outros":
            case "premio":
            case "presente":
                return receitasRepository.save(receitas);
        }
        throw new WrongParameter("o campo tipoReceita está incorreto");
    }

    public void deleteById(@PathVariable int id) {
        findById(id);
        receitasRepository.deleteById(id);
    }

    public Optional<Receitas> findById(int id) {
        Optional<Receitas> receitasOptional = receitasRepository.findById(id);
        validReceitasExists(receitasOptional);
        return receitasOptional;
    }

    private void validReceitasExists(Optional<Receitas> receitasOptional) {
        if(receitasOptional.isEmpty()) {
            throw new ObjectNotFound("Receita Inexistente!");
        }
    }

    public Page<Receitas> findByTipo(String tipoReceita, int pageNumber, int pageSize) {
        if (pageSize > 10) pageSize = 10;
        Pageable page = PageRequest.of(pageNumber, pageSize);
        switch (tipoReceita) {
            case "salario":
            case "outros":
            case "premio":
            case "presente":
                if(receitasRepository.findByTipoReceita(page, tipoReceita).isEmpty()) {
                    throw new ObjectNotFound("Não existem receitas com esse tipo!");
                }
                return receitasRepository.findByTipoReceita(page, tipoReceita);
        }
        throw new ObjectNotFound("Tipo de Receita Inexistente!");
    }

    //não está funcionando
    public Page<Receitas> findByDateBetween(String from, String to, int pageNumber, int pageSize) {
        if (pageSize > 10) pageSize = 10;
        Pageable page = PageRequest.of(pageNumber, pageSize);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom = null;
        Date dateTo = null;
        try {
            dateFrom = format.parse(from);
            dateTo = format.parse(to);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return receitasRepository.findByDataRecebimentoBetween(page, dateFrom, dateTo);
    }

    public ReceitaTotalDto receitaTotal(){
        return receitasRepository.ReceitaTotal();
    }
}
