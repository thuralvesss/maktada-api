package br.com.maktaba.repository;

import br.com.maktaba.model.TokenRecuperacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TokenRecuperacaoRepository extends JpaRepository<TokenRecuperacao, Long> {
    Optional<TokenRecuperacao> findByToken(String token);
}