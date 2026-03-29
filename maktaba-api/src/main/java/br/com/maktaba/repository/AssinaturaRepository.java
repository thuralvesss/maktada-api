package br.com.maktaba.repository;

import br.com.maktaba.model.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
    Optional<Assinatura> findByUsuarioId(Long usuarioId);
}