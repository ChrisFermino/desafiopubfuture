package br.com.pubfuture.desafiopubfuture.services;

import br.com.pubfuture.desafiopubfuture.core.exceptions.ObjectNotFound;
import br.com.pubfuture.desafiopubfuture.core.exceptions.WrongParameter;
import br.com.pubfuture.desafiopubfuture.models.entities.Contas;
import br.com.pubfuture.desafiopubfuture.repositories.ContasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class ContasService {

    @Autowired
    private ContasRepository contasRepository;

    public Contas save(Contas contas) {
        // checar o tipo de conta!!
        return contasRepository.save(contas);
    }

    public Contas edit(Contas contas, int id) {
        if(contas.getId() != id) {
            throw new WrongParameter("O campo id não pode ser alterado!");
        }
        findById(id);
        return contasRepository.save(contas);
    }

    public Page<Contas> getContasPerPage(int pageNumber, int pageSize) {
        if (pageSize > 5) pageSize = 5;
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return contasRepository.findAll(page);
    }

    public void deleteById(@PathVariable int id) {
        findById(id);
        contasRepository.deleteById(id);
    }

    public Optional<Contas> findById(int id) {
        Optional<Contas> contasOptional = contasRepository.findById(id);
        validContasOptional(contasOptional);
        return contasOptional;
    }

    public Double saldoTotal(){
        return contasRepository.saldoTotal();
    }

    private void validContasOptional(Optional<Contas> contasOptional) {
        if (contasOptional.isEmpty()) {
            throw new ObjectNotFound("Conta Inexistente!");
        }
    }
}
