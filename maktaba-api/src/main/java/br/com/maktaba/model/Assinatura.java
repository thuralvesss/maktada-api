package br.com.maktaba.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "assinaturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plano; // BASICO, STANDARD, PREMIUM

    private String status; // ATIVA, PAUSADA, CANCELADA

    private LocalDate dataInicio;

    private LocalDate dataRenovacao;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}