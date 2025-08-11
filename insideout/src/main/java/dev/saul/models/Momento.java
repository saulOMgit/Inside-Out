package dev.saul.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

//modelo tanto esto como Emocion, y testearlas
//luego crear carpeta vistas (solo menu principal por ahora HomeView)
//App llama a HomeController que llama a HomeView
public class Momento {
    private static int contador = 1;

    private final int id;
    private String titulo;
    private String descripcion;
    private final Emocion emocion;
    private final LocalDate fechaMomento;
    private final LocalDateTime fechaCreacion;

    public Momento(String titulo, String descripcion, Emocion emocion, LocalDate fechaMomento) {
        this.id = contador++;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.emocion = emocion;
        this.fechaMomento = fechaMomento;
        this.fechaCreacion = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Emocion getEmocion() {
        return emocion;
    }

    public LocalDate getFechaMomento() {
        return fechaMomento;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public String toString() {
        return id + ". Ocurrió el: " + fechaMomento +
                ". Título: " + titulo +
                ". Descripción: " + descripcion +
                ". Emoción: " + emocion.name();
    }

}
