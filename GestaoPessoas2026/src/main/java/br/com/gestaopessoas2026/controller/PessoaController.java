package br.com.gestaopessoas2026.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestaopessoas2026.model.Pessoa;
import br.com.gestaopessoas2026.services.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    
    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable String id) {
        Pessoa pessoa = pessoaService.buscarPorId(id);
        return ResponseEntity.ok(pessoa);
    }

   
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pessoa>> buscarTodas() {
        List<Pessoa> pessoas = pessoaService.buscarTodas();
        return ResponseEntity.ok(pessoas);
    }

    
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa) {
        Pessoa pessoaCriada = pessoaService.criar(pessoa);
        return ResponseEntity.status(201).body(pessoaCriada);
    }

   
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Pessoa> atualizar(@RequestBody Pessoa pessoa) {
        Pessoa pessoaAtualizada = pessoaService.atualizar(pessoa);
        return ResponseEntity.ok(pessoaAtualizada);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        pessoaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
