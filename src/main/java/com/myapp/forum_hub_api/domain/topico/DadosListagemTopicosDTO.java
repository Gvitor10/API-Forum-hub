package com.myapp.forum_hub_api.domain.topico;

import java.time.LocalDateTime;

public record DadosListagemTopicosDTO(Long id,
                                      String titulo,
                                      String mensagem,
                                      LocalDateTime dataDeCriacao,
                                      EstadoDoTopico estadoDoTopico,
                                      String autor,
                                      String curso) {

    public DadosListagemTopicosDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataDeCriacao(),
                topico.getEstadoDoTopico(), topico.getAutor(), topico.getCurso());
    }
}
