package br.com.pubfuture.desafiopubfuture.controllers;

import br.com.pubfuture.desafiopubfuture.models.entities.Receitas;
import br.com.pubfuture.desafiopubfuture.services.ReceitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("api/receitas")
public class ReceitasController {

    @Autowired
    private ReceitasService receitasService;

    @PostMapping
    public ResponseEntity<Receitas> saveReceitas(@Valid @RequestBody Receitas receitas) {
        return ResponseEntity.ok().body(receitasService.save(receitas));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Receitas> editReceitas(@PathVariable int id, @RequestBody Receitas receitas) {
        return ResponseEntity.ok().body(receitasService.edit(receitas, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable int id) {
        receitasService.deleteById(id);
        return ResponseEntity.ok().body("Registro<Receitas> excluído!");
    }

    @GetMapping(path = "/{dateFrom}/{dateTo}")
    public ResponseEntity<Optional<Receitas>> findByDateBetween(@PathVariable Date dateFrom, @PathVariable Date dateTo) {
        return ResponseEntity.ok().body(receitasService.findByDateBetween(dateFrom, dateTo));
    }

    @GetMapping(path = "/{tipoReceita}")
    public ResponseEntity<Optional<Receitas>> findByTipo(@PathVariable String tipoReceita) {
        return ResponseEntity.ok().body(receitasService.findByTipo(tipoReceita));
    }

    @GetMapping(path = "/receitaTotal")
    public ResponseEntity<Double> getReceitaTotal() {
        return ResponseEntity.ok().body(receitasService.receitaTotal());
    }
}
