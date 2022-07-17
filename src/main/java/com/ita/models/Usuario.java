package com.ita.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Usuario {
    private String nomeUsuario;
    private Map<EnumTipoPonto, Pontuacao> listaPontos = new HashMap<>();

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Map<EnumTipoPonto, Pontuacao> getListaPontos() {
        return listaPontos;
    }

    public void setListaPontos(Map<EnumTipoPonto, Pontuacao> listaPontos) {
        this.listaPontos = listaPontos;
    }

    public void add(EnumTipoPonto tipo, int quantidade) {
        if (listaPontos.containsKey(tipo)) {
            listaPontos.put(tipo, listaPontos.get(tipo).add(quantidade));
        } else {
            listaPontos.put(tipo, new Pontuacao(quantidade, tipo));
        }
    }

/*     public void add(String tipo, String quantidade) {
        if (listaPontos.containsKey(tipo) && EnumTipoPonto.ESTRELA.equals(tipo) || EnumTipoPonto.MOEDA.equals(tipo)) {
            listaPontos.put(tipo ,listaPontos.get(tipo).add(quantidade));
        } else {
            listaPontos.put(tipo, new Pontuacao(quantidade, tipo));
        }
    } */

    public Usuario(String nomeUsuario, Map<EnumTipoPonto, Pontuacao> listaPontos) {
        this.nomeUsuario = nomeUsuario;
        this.listaPontos = listaPontos;
    }
}
