package com.rafaelMelo.rpg_cyberpunk_api.dto;

public class IniciarJogoRequestDTO {
    private String nomeJogador;
    private int escolhaClasse;
    private int dificuldade;

    // Getters e Setters
    public String getNomeJogador() {
        return nomeJogador;
    }
    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }
    public int getEscolhaClasse() {
        return escolhaClasse;
    }
    public void setEscolhaClasse(int escolhaClasse) {
        this.escolhaClasse = escolhaClasse;
    }
    public int getDificuldade() {
        return dificuldade;
    }
    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }
}