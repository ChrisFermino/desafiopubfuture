package br.com.pubfuture.desafiopubfuture.controllers;

import br.com.pubfuture.desafiopubfuture.models.dto.ReceitaTotalDto;
import br.com.pubfuture.desafiopubfuture.models.entities.Receitas;
import br.com.pubfuture.desafiopubfuture.services.ReceitasService;
import br.com.pubfuture.desafiopubfuture.utils.enums.TipoReceitaEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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

    @GetMapping(path = "/{dateFrom}/{dateTo}/{pageNumber}/{pageSize}")
    public ResponseEntity<Page<Receitas>> findByDateBetween(@PathVariable String dateFrom, @PathVariable String dateTo, @PathVariable int pageNumber, @PathVariable int pageSize) {
        return ResponseEntity.ok().body(receitasService.findByDateBetween(dateFrom, dateTo, pageNumber, pageSize));
    }

    @GetMapping(path = "/{tipoReceita}/{pageNumber}/{pageSize}")
    public ResponseEntity<Page<Receitas>> findByTipo(@PathVariable TipoReceitaEnum tipoReceita, @PathVariable int pageNumber, @PathVariable int pageSize) {
        return ResponseEntity.ok().body(receitasService.findByTipo(tipoReceita, pageNumber, pageSize));
    }

    @GetMapping(path = "/receitaTotal")
    public ResponseEntity<ReceitaTotalDto> getReceitaTotal() {
        return ResponseEntity.ok().body(receitasService.receitaTotal());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteReceitas(@PathVariable int id) {
        receitasService.deleteById(id);
        return ResponseEntity.ok().body("Registro<Receitas> excluído!");
    }
}
