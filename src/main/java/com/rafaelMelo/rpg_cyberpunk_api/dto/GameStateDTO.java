package com.rafaelMelo.rpg_cyberpunk_api.dto;

public class GameStateDTO {
    private String nomeJogador;
    private int hpJogador;
    private int epJogador; 
    private int stimsJogador; 

    private String nomeInimigo;
    private int hpInimigo;

    private String mensagem;

    // GETTERS E SETTERS
    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public int getHpJogador() {
        return hpJogador;
    }

    public void setHpJogador(int hpJogador) {
        this.hpJogador = hpJogador;
    }

    public int getEpJogador() {
        return epJogador;
    }

    public void setEpJogador(int epJogador) {
        this.epJogador = epJogador;
    }

    public int getStimsJogador() {
        return stimsJogador;
    }

    public void setStimsJogador(int stimsJogador) {
        this.stimsJogador = stimsJogador;
    }

    public String getNomeInimigo() {
        return nomeInimigo;
    }

    public void setNomeInimigo(String nomeInimigo) {
        this.nomeInimigo = nomeInimigo;
    }

    public int getHpInimigo() {
        return hpInimigo;
    }

    public void setHpInimigo(int hpInimigo) {
        this.hpInimigo = hpInimigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}