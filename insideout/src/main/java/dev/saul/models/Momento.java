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
    private final EmocionEnum emocion;
    private final LocalDate fechaMomento;
    private final LocalDateTime fechaCreacion;

    private PositividadEnum positividad;

    public Momento(String titulo, String descripcion, EmocionEnum emocion, LocalDate fechaMomento) {
        this.id = contador++;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.emocion = emocion;
        this.fechaMomento = fechaMomento;
        this.fechaCreacion = LocalDateTime.now();
    }

    public Momento(int id, String titulo, String descripcion, EmocionEnum emocion, LocalDate fechaMomento,
            LocalDateTime fechaCreacion, PositividadEnum positividad) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.emocion = emocion;
        this.fechaMomento = fechaMomento;
        this.fechaCreacion = fechaCreacion;
        this.positividad = positividad;
    }


    public Momento(String titulo, LocalDate fecha, String descripcion, EmocionEnum emocion,
            PositividadEnum positividad) {    
        this.id = contador++;
        this.titulo = titulo;
        this.fechaMomento = fecha;
        this.descripcion = descripcion;
        this.emocion = emocion;
        this.positividad = positividad;
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

    public EmocionEnum getEmocion() {
        return emocion;
    }

    public LocalDate getFechaMomento() {
        return fechaMomento;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    

    public PositividadEnum getPositividad() {
        return positividad;
    }

    public void setPositividad(PositividadEnum positividad) {
        this.positividad = positividad;
    }

    @Override
    public String toString() {
        return "Momento [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", emocion=" + emocion
                + ", fechaMomento=" + fechaMomento + ", fechaCreacion=" + fechaCreacion + ", positividad=" + positividad
                + "]";
    }

    

}
