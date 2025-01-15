package com.myapp.forum_hub_api.controller;

import com.myapp.forum_hub_api.domain.usuario.DadosUsuarioDTO;
import com.myapp.forum_hub_api.domain.usuario.Usuario;
import com.myapp.forum_hub_api.domain.usuario.UsuarioRepository;
import com.myapp.forum_hub_api.infrastructure.security.DadosTokenJwtDTO;
import com.myapp.forum_hub_api.infrastructure.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosUsuarioDTO dados)  {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());


        return ResponseEntity.ok(new DadosTokenJwtDTO(tokenJWT));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid DadosUsuarioDTO dados) {

        if (usuarioRepository.existsByLoginContaining(dados.login())) {
            return ResponseEntity.internalServerError().body("ERRO: Usuário com login informado já cadastrado");
        }
        else {
            var novoUsuario = new Usuario(dados);
            usuarioRepository.save(novoUsuario);

            return ResponseEntity.ok("Usuário cadastrado com sucesso");
        }
    }
}
