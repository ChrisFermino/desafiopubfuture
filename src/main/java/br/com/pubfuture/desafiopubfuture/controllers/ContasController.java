package br.com.pubfuture.desafiopubfuture.controllers;

import br.com.pubfuture.desafiopubfuture.models.entities.Contas;
import br.com.pubfuture.desafiopubfuture.services.ContasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("api/contas")
public class ContasController {

    @Autowired
    private ContasService contasService;

    @PostMapping
    public ResponseEntity<Contas> saveContas(@Valid @RequestBody Contas contas){
        return ResponseEntity.ok().body(contasService.save(contas));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Contas> editContas(@PathVariable int id, @RequestBody Contas contas) {
        return ResponseEntity.ok().body(contasService.edit(contas, id));
    }

    @GetMapping(path = "/page/{pageNumber}/{pageSize}")
    public ResponseEntity<Page<Contas>> getContasPerPage(@PathVariable int pageNumber, @PathVariable int pageSize) {
        return ResponseEntity.ok().body(contasService.getContasPerPage(pageNumber, pageSize));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteContas(@PathVariable int id) {
        contasService.deleteById(id);
        return ResponseEntity.ok().body("Registro<Contas> excluído!");
    }

    @GetMapping(path = "/saldoTotal")
    public ResponseEntity<String> saldoTotal() {
        return ResponseEntity.ok().body("Saldo Total: " + contasService.saldoTotal());
    }
}
