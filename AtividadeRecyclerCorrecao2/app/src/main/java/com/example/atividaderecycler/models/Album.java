package com.example.atividaderecycler.models;

public class Album {
    private String banda;
    private String ano;
    private String fotografo;
    private int imagem;
    private String saibamais;

    public Album(String titulo, String ano, String fotografo, String sinopse, int imagem) {
        this.banda = titulo;
        this.ano = ano;
        this.fotografo = fotografo;
        this.imagem = imagem;
        this.saibamais = sinopse;
    }

    public Album() {
    }

    public String getSaibamais() {
        return saibamais;
    }

    public void setSaibamais(String saibamais) {
        this.saibamais = saibamais;
    }

    public String getBanda() {
        return banda;
    }

    public void setBanda(String banda) {
        this.banda = banda;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getFotografo() {
        return fotografo;
    }

    public void setFotografo(String fotografo) {
        this.fotografo = fotografo;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
