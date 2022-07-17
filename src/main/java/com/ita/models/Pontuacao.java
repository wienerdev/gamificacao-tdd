package com.ita.models;
public class Pontuacao {

    private int quantidade;
    private EnumTipoPonto tipo;

    public Pontuacao(int quantidade, EnumTipoPonto tipo) {
        this.quantidade = quantidade;
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public EnumTipoPonto getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipoPonto tipo) {
        this.tipo = tipo;
    }
    
    public Pontuacao add(int quantidade) {
        this.quantidade += quantidade;
        return this;
    }
}
