package br.com.pubfuture.desafiopubfuture.services;

import br.com.pubfuture.desafiopubfuture.core.exceptions.ObjectNotFound;
import br.com.pubfuture.desafiopubfuture.core.exceptions.WrongParameter;
import br.com.pubfuture.desafiopubfuture.models.entities.Despesas;
import br.com.pubfuture.desafiopubfuture.repositories.DespesasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.Optional;

@Service
public class DespesasService {

    @Autowired
    private DespesasRepository despesasRepository;

    public Despesas save(Despesas despesas) {
        switch (despesas.getTipoDespesa()) {
            case "alimentacao":
            case "educacao":
            case "lazer":
            case "moradia":
            case "roupa":
            case "saude":
            case "transporte":
            case "outros":
                return despesasRepository.save(despesas);
        }
        throw new WrongParameter("o campo tipoDespesa está incorreto!");
    }

    public Despesas edit(Despesas despesas, int id) {
        //arrumar o tipo de despesa
        if(despesas.getId() != id) {
            throw new WrongParameter("o campo id não pode ser alterado!");
        }
        findById(id);
        return despesasRepository.save(despesas);
    }

    public void deleteById(@PathVariable int id) {
        findById(id);
        despesasRepository.deleteById(id);
    }

    public Optional<Despesas> findById(int id) {
        Optional<Despesas> despesasOptional = despesasRepository.findById(id);
        validDespesasExists(despesasOptional);
        return despesasOptional;
    }

    public void validDespesasExists(Optional<Despesas> despesasOptional) {
        if(despesasOptional.isEmpty()) {
            throw new ObjectNotFound("Despesa Inexistente!");
        }
    }

    public Optional<Despesas> findByTipo(String tipoDespesa) {
        //fazer paginacao
        switch (tipoDespesa) {
            case "alimentacao":
            case "educacao":
            case "lazer":
            case "moradia":
            case "roupa":
            case "saude":
            case "transporte":
            case "outros":
                if (despesasRepository.findByTipoDespesa(tipoDespesa).isEmpty()) {
                    throw new ObjectNotFound("Não existem despesas desse tipo");
                }
                return despesasRepository.findByTipoDespesa(tipoDespesa);
        }
        throw  new ObjectNotFound("tipo de Despesa Inexistente!");
    }

    //não está funcionando //fazer paginacao
    public Optional<Despesas> findByDateBetween (Date dateFrom, Date dateTo) {
        return despesasRepository.findByDataPagamentoBetween(dateFrom, dateTo);
    }

    public Double despesaTotal() {
        return despesasRepository.DespesaTotal();
    }
}
