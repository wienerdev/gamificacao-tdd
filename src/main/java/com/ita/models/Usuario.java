package com.ita.models;

import java.util.List;

public class Usuario {
    private String nomeUsuario;
    private List<Pontuacao> listaPontos;

    public Usuario(String nomeUsuario, List<Pontuacao> listaPontos) {
        this.nomeUsuario = nomeUsuario;
        this.listaPontos = listaPontos;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public List<Pontuacao> getListaPontos() {
        return listaPontos;
    }

    public void setListaPontos(List<Pontuacao> listaPontos) {
        this.listaPontos = listaPontos;
    }
    
}
