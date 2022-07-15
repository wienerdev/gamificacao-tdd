package com.ita.models;

public class Pontuacao {

    private int pontosTipoEstrela;
    private int pontosTipoMoeda;

    public int getPontosTipoMoeda() {
        return pontosTipoMoeda;
    }

    public void setPontosTipoMoeda(int pontosTipoMoeda) {
        this.pontosTipoMoeda += pontosTipoMoeda;
    }

    public int getPontosTipoEstrela() {
        return pontosTipoEstrela;
    }

    public void setPontosTipoEstrela(int pontosTipoEstrela) {
        this.pontosTipoEstrela += pontosTipoEstrela;
    }

    
    
}
