package com.hotelapi.hotelapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "quarto")
@Data
@ToString(exclude = "reservas")
@EntityListeners(AuditingEntityListener.class)
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tipo")
    private String tipoQuarto;

    @Column(name = "preco_diaria")
    private BigDecimal precoDiaria;

    @Column(name = "capacidade_maxima")
    private Integer capacidadeMaxima;

    @Column(name = "vagas_disponiveis")
    private Integer vagasDisponiveis;

    @Column(name = "tamanho_m²")
    private String tamanho;

    @JoinColumn(name = "id_hotel")
    @ManyToOne
    private Hotel hotel;

    @CreatedDate
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "quarto", fetch = FetchType.LAZY)
    private List<Reserva> reservas;
}
