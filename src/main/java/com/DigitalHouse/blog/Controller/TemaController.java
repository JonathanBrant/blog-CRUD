package com.DigitalHouse.blog.Controller;

import com.DigitalHouse.blog.Model.Tema;
import com.DigitalHouse.blog.Repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/temas")
@CrossOrigin("*")
public class TemaController {
    @Autowired //o Spring faz a injeção de dependencias pois aqui é uma interface
    private final TemaRepository repository;

    public TemaController(TemaRepository repository)throws Exception {
        this.repository = repository;
    }

    //Realizar o Get em todos os temas que ja foram adicionados
    //ResponseEntity nos retorna uma entidade mapeada
    @GetMapping
    public ResponseEntity<List<Tema>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    //Realizar o Get em um único tema de acordo com o ID
    @GetMapping("/{id}")
    public ResponseEntity<Tema> getById(@PathVariable Long id) {
        return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    //Realizar o Get em uma lista de temas de acordo com uma parte da descrição digitada, retornará
    // todos os temas que possuírem o a descricção digitada
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<Tema>> getByDescricao(@PathVariable String descricao) {
        return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
    }

    //Realiza o metodo post em "/temas", sendo necessário passar um body/raw/json contendo
    // {"descricao": "conteudo"} Não é necessário passar um ID pois o mesmo é auto-incremento
    @PostMapping
    public ResponseEntity<Tema> post (@RequestBody Tema tema) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
    }

    @PutMapping
    public ResponseEntity<Tema> put (@RequestBody Tema tema) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(tema));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
