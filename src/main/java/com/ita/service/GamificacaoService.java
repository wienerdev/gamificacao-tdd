package com.ita.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ita.models.EnumTipoPonto;
import com.ita.models.Usuario;
import com.ita.validate.GamificacaoValidate;

public class GamificacaoService {

    GamificacaoValidate validate = new GamificacaoValidate();

    List<Usuario> listaDeUsuarios;

    public List<Usuario> getListaDeUsuarios() {
        return listaDeUsuarios;
    }

    public void setListaDeUsuarios(List<Usuario> listaDeUsuarios) {
        this.listaDeUsuarios = listaDeUsuarios;
    }

    public GamificacaoService(List<Usuario> listaDeUsuarios) {
        this.listaDeUsuarios = listaDeUsuarios;
    }

    public String receberPontos(String nomeUsuario, Integer quantidadePontos, String tipoPontos) {
        validate.validar(nomeUsuario, listaDeUsuarios, tipoPontos);

        Optional<Usuario> usuario = retornarUsuario(nomeUsuario);
        usuario.get().getListaPontos().forEach(pontos -> {
            if (EnumTipoPonto.ESTRELA.name().equalsIgnoreCase(tipoPontos)) {
                pontos.setQuantidade(quantidadePontos);
                /* pontos.setPontosTipoEstrela(quantidadePontos); */
            }
            if (EnumTipoPonto.MOEDA.name().equalsIgnoreCase(tipoPontos)) {
                /* pontos.setPontosTipoMoeda(quantidadePontos); */
            }
        });

        return "O usuário " + nomeUsuario + " recebeu " + quantidadePontos + " pontos do tipo " + tipoPontos;
    }

    public String recuperarPontos(String nomeUsuario, String tipoPontos) {
        validate.validar(nomeUsuario, listaDeUsuarios, tipoPontos);


        Optional<Usuario> usuario = retornarUsuario(nomeUsuario);
        if (tipoPontos == "estrela") {
            return usuario.get().getListaPontos().stream()
                    .map(pontos -> "Pontos do tipo estrela: " +
                            pontos.getPontosTipoEstrela())
                    .collect(Collectors.joining());
        }

        if (tipoPontos == "moeda") {
            return usuario.get().getListaPontos().stream()
                    .map(pontos -> "Pontos do tipo moeda: " +
                            pontos.getPontosTipoMoeda())
                    .collect(Collectors.joining());
        }

        return "Houve um problema com a recuperação de pontos, tente novamente!";
    }

    public void printarTodosUsuariosQuePossuemPontos() {

        System.out.println("Os usuários que possuem algum tipo de ponto são:");

        getListaDeUsuarios().forEach(usuario -> {
            if (isUsuarioPossuiPontos(usuario)) {
                System.out.println("- " + usuario.getNomeUsuario());
            }
        });
    }

    public String recuperarTodosPontosRegistradosPorUsuario(String nomeUsuario) {
        validate.validarUsuario(nomeUsuario, listaDeUsuarios);

        Optional<Usuario> usuario = retornarUsuario(nomeUsuario);

        if (usuario.get().getListaPontos().stream().anyMatch(pontos -> pontos.getPontosTipoEstrela() > 0 && pontos.getPontosTipoMoeda() > 0)) {
            return "O usuário "+nomeUsuario+" possui pontos do tipo estrela e moeda";
        }
        if (usuario.get().getListaPontos().stream().anyMatch(pontos -> pontos.getPontosTipoEstrela() > 0)) {
            return "O usuário "+nomeUsuario+" possui pontos do tipo estrela";
        }
        if (usuario.get().getListaPontos().stream().anyMatch(pontos -> pontos.getPontosTipoMoeda() > 0)) {
            return "O usuário "+nomeUsuario+" possui pontos do tipo moeda";
        }

        return "Houve um problema com a recuperação dos tipos de pontos, tente novamente!";
    }

    public String recuperarRankingTipoPontos(String tipoPontos) {
        validate.validarTipoPontos(tipoPontos);

        List<String> listaUsuariosPontoEstrela = new ArrayList<>();
        List<String> listaUsuariosPontoMoeda = new ArrayList<>();

        getListaDeUsuarios().forEach(usuario -> {
            usuario.getListaPontos().forEach(pontos -> {
                if (tipoPontos == "moeda" && pontos.getPontosTipoMoeda() > 0) {
                    listaUsuariosPontoMoeda.add(usuario.getNomeUsuario() + ": " + pontos.getPontosTipoMoeda());
                }
                if (tipoPontos == "estrela" && pontos.getPontosTipoEstrela() > 0) {
                    listaUsuariosPontoEstrela.add(usuario.getNomeUsuario() + ": " + pontos.getPontosTipoEstrela());
                }
            });
        });
        
        if (tipoPontos == "moeda") {
            return listaUsuariosPontoMoeda.stream().map(item -> item.toString()+"\n").collect(Collectors.joining());
        }
        if (tipoPontos == "estrela") {
            return listaUsuariosPontoEstrela.stream().map(item -> item.toString()+"\n").collect(Collectors.joining());
        }

        return "Houve um problema com a recuperação do ranking, tente novamente!";
    }

    public boolean isUsuarioPossuiPontos(Usuario usuario) {
        return usuario.getListaPontos().stream()
                .anyMatch(pontos -> pontos.getPontosTipoEstrela() > 0 || pontos.getPontosTipoMoeda() > 0);
    }

    private Optional<Usuario> retornarUsuario(String nomeUsuario) {
        return listaDeUsuarios.stream().filter(usuario -> usuario.getNomeUsuario().equals(nomeUsuario)).findFirst();
    }
}
