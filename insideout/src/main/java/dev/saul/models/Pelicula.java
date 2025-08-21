package dev.saul.models;

import java.time.LocalDateTime;
import java.util.List;

public class Pelicula {
    private String imdbId;
    private String titulo;
    private List<String> generos;
    private EmocionEnum emocion;
    private int fechaEstreno;
    private LocalDateTime fechaCreacion;

    public Pelicula(String imdbId, String titulo, List<String> generos, EmocionEnum emocion, int fechaEstreno) {
        this.imdbId = imdbId;
        this.titulo = titulo;
        this.generos = generos;
        this.emocion = emocion;
        this.fechaEstreno = fechaEstreno;
        this.fechaCreacion = LocalDateTime.now();
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public EmocionEnum getEmocion() {
        return emocion;
    }

    public int getFechaEstreno() {
        return fechaEstreno;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public String toString() {
        return "Pelicula [imdbId=" + imdbId + ", titulo=" + titulo + ", generos=" + generos
                + ", emocion=" + emocion + ", fechaEstreno=" + fechaEstreno
                + ", fechaCreacion=" + fechaCreacion + "]";
    }
}
