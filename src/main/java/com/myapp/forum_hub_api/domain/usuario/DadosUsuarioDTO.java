package com.myapp.forum_hub_api.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosUsuarioDTO(@NotBlank String login,
                              @NotBlank String senha) {

}
