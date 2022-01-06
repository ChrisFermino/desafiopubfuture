package br.com.pubfuture.desafiopubfuture.controllers;


import br.com.pubfuture.desafiopubfuture.models.entities.Despesas;
import br.com.pubfuture.desafiopubfuture.services.DespesasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("api/despesas")
public class DespesasController {

    @Autowired
    private DespesasService despesasService;

    @PostMapping
    public ResponseEntity<Despesas> saveDespesas(@Valid @RequestBody Despesas despesas) {
        return ResponseEntity.ok().body(despesasService.save(despesas));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Despesas> editDespesas(@PathVariable int id, @RequestBody Despesas despesas) {
        return ResponseEntity.ok().body(despesasService.edit(despesas, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteDespesas(@PathVariable int id) {
        despesasService.deleteById(id);
        return ResponseEntity.ok().body("Registro<Despesas> excluído!");
    }

    @GetMapping(path = "/{dateFrom}/{dateTo}")
    public ResponseEntity<Optional<Despesas>> findByDateBetween(@PathVariable Date dateFrom, @PathVariable Date dateTo) {
        return ResponseEntity.ok().body(despesasService.findByDateBetween(dateFrom, dateTo));
    }

    @GetMapping(path = "/{tipoDespesa}")
    public ResponseEntity<Optional<Despesas>> findByTipo(@PathVariable String tipoDespesa) {
        return ResponseEntity.ok().body(despesasService.findByTipo(tipoDespesa));
    }

    @GetMapping(path = "/despesaTotal")
    public ResponseEntity<Double> getDespesaTotal() {
        return ResponseEntity.ok().body(despesasService.despesaTotal());
    }
}
