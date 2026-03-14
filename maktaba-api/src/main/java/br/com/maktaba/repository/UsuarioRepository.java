package br.com.maktaba.repository;

import br.com.maktaba.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Deixe vazio por enquanto. O JpaRepository já te dá o "save" e o "findAll"
}
