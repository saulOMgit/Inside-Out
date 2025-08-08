package dev.saul;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Momento {
    private static int contador = 1;

    private int id;
    private String titulo;
    private String descripcion;
    private Emocion emocion;
    private LocalDate fechaMomento;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;

    public Momento(String titulo, String descripcion, Emocion emocion, LocalDate fechaMomento) {
        this.id = contador++;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.emocion = emocion;
        this.fechaMomento = fechaMomento;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaModificacion = this.fechaCreacion;
    }

    public int getId() {
        return id;
    }

    public Emocion getEmocion() {
        return emocion;
    }

    public LocalDate getFechaMomento() {
        return fechaMomento;
    }

    public String toString() {
        return "ID: " + id + ". Ocurrió el: " + fechaMomento +
                ". Título: " + titulo +
                ". Descripción: " + descripcion +
                ". Emoción: " + emocion.name().charAt(0) + emocion.name().substring(1).toLowerCase();
    }
}
