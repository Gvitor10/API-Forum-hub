package com.myapp.forum_hub_api.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Boolean existsByTituloContainingOrMensagemContaining(String titulo, String mensagem);
}
