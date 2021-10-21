package com.DigitalHouse.blog.Controller;

import com.DigitalHouse.blog.Model.UserLogin;
import com.DigitalHouse.blog.Model.Usuario;
import com.DigitalHouse.blog.Repository.UsuarioRepository;
import com.DigitalHouse.blog.Service.UsuarioService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*
* Model: gerenciar as entidades
Repository: gerenciar o BD
Controller: gerenciar os EndPoints (verbos HTTP)
Security: gerenciar a segurança (Spring Security)
View: Front End

* */

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/logar")
    public ResponseEntity<UserLogin> autentication(@RequestBody Optional<UserLogin> user) {
        return usuarioService.logar(user).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> post(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.cadastrarUsuario(usuario));
    }


}
