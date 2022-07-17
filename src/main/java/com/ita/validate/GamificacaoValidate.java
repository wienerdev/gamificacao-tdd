package com.ita.validate;

import java.util.List;

import com.ita.models.Usuario;

public class GamificacaoValidate {

    public GamificacaoValidate validar(String nomeUsuario, List<Usuario> listaUsuario, String tipoPontos) {
        return this.validarUsuario(nomeUsuario, listaUsuario).validarTipoPontos(tipoPontos);
    }

    public GamificacaoValidate validarTipoPontos(String tipoPontos) {
        if (isTipoPontoValido(tipoPontos)) {
            throw new RuntimeException("Tipo de ponto inválido!");//exception simples do q runtime
        }
        return this;
    }

    public GamificacaoValidate validarUsuario(String nomeUsuario, List<Usuario> listaUsuario) {
        if (!isUsuarioExistente(listaUsuario, nomeUsuario)) {
            throw new RuntimeException("Usuário não encontrado!");//exception simples do q runtime
        }
        return this;
    }

    public boolean isTipoPontoValido(String tipoPontos) {
        return tipoPontos != "moeda" && tipoPontos != "estrela";
    }

    public boolean isUsuarioExistente(List<Usuario> listaUsuario, String nomeUsuario) {
        return listaUsuario.stream().filter(usuario -> usuario.getNomeUsuario().equals(nomeUsuario)).findFirst()
                .isPresent();
    }
    
}
