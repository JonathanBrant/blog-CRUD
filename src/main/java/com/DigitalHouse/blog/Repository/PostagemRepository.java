package com.DigitalHouse.blog.Repository;

import com.DigitalHouse.blog.Model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostagemRepository extends JpaRepository <Postagem, Long> {

    //consultar pelo titulo
    public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);

    //consultar por texto
    public List<Postagem> findAllByTextoContainingIgnoreCase(String texto);

}
