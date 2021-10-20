package com.DigitalHouse.blog.Repository;

import com.DigitalHouse.blog.Model.Postagem;
import com.DigitalHouse.blog.Model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemaRepository extends JpaRepository <Tema, Long>{
    public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);
}
