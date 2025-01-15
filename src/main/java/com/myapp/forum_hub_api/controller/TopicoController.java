package com.myapp.forum_hub_api.controller;

import com.myapp.forum_hub_api.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarNovoTopico(@RequestBody @Valid DadosNovoTopicoDTO dados) {

        if (topicoRepository.existsByTituloContainingOrMensagemContaining(dados.titulo(), dados.mensagem())) {
            return ResponseEntity.internalServerError().body("ERRO: Existe Titulo ou Mensagem com essas informaçoes cadastradas.");
        }
        else {
            var novoTopico = new Topico(dados);
            topicoRepository.save(novoTopico);

            return ResponseEntity.ok().build();
        }
    }

    @GetMapping
    ResponseEntity<Page<DadosListagemTopicosDTO>> listarTodosOsTopicos(@PageableDefault(size = 10) Pageable paginacao) {
        var pagina = topicoRepository.findAll(paginacao).map(DadosListagemTopicosDTO::new);

        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharTopico(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return  ResponseEntity.ok(new DadosListagemTopicosDTO(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarTopico(@PathVariable Long id, @RequestBody DadosAtualizacaoTopicoDTO dados) {
        Optional<Topico> topico = topicoRepository.findById(id);

        if (topico.isPresent()) {
            var topicoAtualizado = topico.get();
            topicoAtualizado.atulizarTopico(dados);

            topicoRepository.save(topicoAtualizado);

            return ResponseEntity.ok("Tópico atulizado com Sucesso");
        }
        else {
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);

        if (topico.isPresent()) {
            topicoRepository.deleteById(id);

            return ResponseEntity.noContent().build();
        }
        else {
            return  ResponseEntity.notFound().build();
        }
    }
}
