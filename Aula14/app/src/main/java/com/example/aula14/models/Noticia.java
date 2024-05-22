package com.example.aula14.models;

public class Noticia {
    private int id;
    private String titulo;
    private String subtitulo;

    public Noticia() {
    }

    public Noticia(int id, String titulo, String subtitulo) {
        this.id = id;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }
}
