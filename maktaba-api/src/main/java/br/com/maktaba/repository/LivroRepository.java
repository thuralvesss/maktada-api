package br.com.maktaba.repository;

import br.com.maktaba.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByDisponivelTrue();
    List<Livro> findByGenero(String genero);
}