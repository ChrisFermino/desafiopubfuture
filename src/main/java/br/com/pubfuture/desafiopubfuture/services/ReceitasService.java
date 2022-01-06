package br.com.pubfuture.desafiopubfuture.services;

import br.com.pubfuture.desafiopubfuture.core.exceptions.ObjectNotFound;
import br.com.pubfuture.desafiopubfuture.core.exceptions.WrongParameter;
import br.com.pubfuture.desafiopubfuture.models.entities.Receitas;
import br.com.pubfuture.desafiopubfuture.repositories.ReceitasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.Optional;

@Service
public class ReceitasService {

    @Autowired
    private ReceitasRepository receitasRepository;

    public Receitas save(Receitas receitas) {
        switch (receitas.getTipoReceita()) {
            case "salário":
            case "outros":
            case "prêmio":
            case "presente":
                return receitasRepository.save(receitas);
        }
        throw new WrongParameter("o campo tipoReceita está incorreto");
    }

    public Receitas edit(Receitas receitas, int id) {
        //arrumar o tipo de despesa
        if(receitas.getId() != id) {
            throw new WrongParameter("O campo id não pode ser alterado!");
        }
        findById(id);
        return receitasRepository.save(receitas);
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

    public Optional<Receitas> findByTipo(String tipoReceita) {
        //fazer paginacao
        switch (tipoReceita) {
            case "salário":
            case "outros":
            case "prêmio":
            case "presente":
                if(receitasRepository.findByTipoReceita(tipoReceita).isEmpty()) {
                    throw new ObjectNotFound("Não existem receitas com esse tipo!");
                }
                return receitasRepository.findByTipoReceita(tipoReceita);
        }
        throw new ObjectNotFound("Tipo de Receita Inexistente!");
    }

    //não está funcionando //fazer paginacao
    public Optional<Receitas> findByDateBetween(Date dateFrom, Date dateTo) {
        return receitasRepository.findByDataRecebimentoBetween(dateFrom, dateTo);
    }

    public Double receitaTotal(){
        return receitasRepository.ReceitaTotal();
    }
}
