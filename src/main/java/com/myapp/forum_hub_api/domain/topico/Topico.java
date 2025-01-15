package com.myapp.forum_hub_api.domain.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "topicos")
@Entity(name = "Topico")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataDeCriacao;

    @Enumerated(EnumType.STRING)
    private EstadoDoTopico estadoDoTopico;
    private String autor;
    private String curso;

    public Topico(){}

    public Topico(@Valid DadosNovoTopicoDTO dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.dataDeCriacao = LocalDateTime.now();
        this.estadoDoTopico = EstadoDoTopico.NÃO_RESPONDIDO;
        this.autor = dados.autor();
        this.curso = dados.curso();
    }

    public void atulizarTopico(DadosAtualizacaoTopicoDTO dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }

        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }

        if (dados.curso() != null) {
            this.curso = dados.curso();
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public EstadoDoTopico getEstadoDoTopico() {
        return estadoDoTopico;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }
}
