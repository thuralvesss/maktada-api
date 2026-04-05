package br.com.maktaba.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "tokens_recuperacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRecuperacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private LocalDateTime expiracao;

    private boolean usado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}