package com.hotelapi.hotelapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reserva")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "check_in")
    private  LocalDate checkIn;

    @Column(name = "check_out")
    private LocalDate checkOut;

    @Column(name = "nome_hospede")
    private String nomeHospede;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "quantidade_adultos")
    private int quantidadeAdultos;

    @Column(name = "quantidade_criancas")
    private int quantidadeCriancas;

    @Column(name = "total_hospedes")
    private int totalHospedes;

    @Column(name = "cod_confirmacao")
    private String codigoConfirmacao;

    @JoinColumn(name = "id_quarto", nullable = false)
    @ManyToOne
    private Quarto quarto;

    @CreatedDate
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    public void calcularTotalDeHospedes() {
        this.totalHospedes = this.quantidadeAdultos + this.quantidadeCriancas;
    }

    public void setQuantidadeAdultos(Integer quantidadeAdultos) {
        this.quantidadeAdultos = quantidadeAdultos;
        calcularTotalDeHospedes();
    }

    public void setQuantidadeCriancas(Integer quantidadeCriancas) {
        this.quantidadeCriancas = quantidadeCriancas;
        calcularTotalDeHospedes();
    }
}
