package com.DigitalHouse.blog.Controller;

import com.DigitalHouse.blog.Model.Postagem;
import com.DigitalHouse.blog.Repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {

    @Autowired
    private final PostagemRepository repository;

    public PostagemController(PostagemRepository repository) throws Exception {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Postagem>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> getById(@PathVariable Long id) {
        return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{titulo}")

    public ResponseEntity<List<Postagem>> getByTitutlo(@PathVariable String titulo) {
        return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));

    }


    @GetMapping("/texto/{texto}")
    public ResponseEntity<List<Postagem>> getByTexto(@PathVariable String texto) {
        return ResponseEntity.ok(repository.findAllByTextoContainingIgnoreCase(texto));
    }


//    @GetMapping("/titulo/{titulo}")  //este mapping é similar ao anterior mas incluímos a /titulo
//
//    //lembrando, podemos ter 1 postagem ou mais de uma postagem com o titulo
//    //por isso nesse caso lembrando o primeiro mapping nós iremos fazer uma
//    //List do tipo postagem para este retorno
//
//    public ResponseEntity <List<Postagem>> GetByTitulo(@PathVariable String titulo){
//        //atenção no PathVariable, não pode esquece
//
//        return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
//    }


    @PostMapping  //vamos usar esta annotation para identificar que usaremos o verbo post
    public ResponseEntity<Postagem> post (@RequestBody Postagem postagem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
    }

    @PutMapping
    public ResponseEntity<Postagem> put ( @RequestBody Postagem postagem) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }



}
