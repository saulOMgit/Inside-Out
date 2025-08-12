package dev.saul.controllers;

import java.util.LinkedList;

import dev.saul.models.Emocion;
import dev.saul.models.Momento;
import dev.saul.repositories.MomentRepository;

import java.util.Iterator;
import java.time.LocalDate;

//controlador
public class Diario {
    private MomentRepository repository;

    public Diario() {

        // tiene que estar en su propia clase (por single responsability)
        // sera carpeta db de ahi ira a repositorio y de ahi a un controlador
        this.repository = new MomentRepository();
    }

    public void agregarMomento(Momento momento) {
        repository.add(momento);
        // no imprimir desde el controlador
        System.out.println("Momento vivido añadido correctamente.");
    }

    public void listarMomentos() {
        LinkedList<Momento> momentos = repository.getAll();
        if (momentos.isEmpty()) {
            System.out.println("No hay momentos registrados.");
        } else {
            for (Momento m : momentos) {
                System.out.println(m);
            }
        }
    }

    public boolean eliminarMomento(int id) {
        return repository.deleteById(id);
    }

    public void filtrarPorEmocion(Emocion emocion) {
        LinkedList<Momento> filtrados = repository.findByEmocion(emocion);
        for (Momento m : filtrados) {
            System.out.println(m);
        }
    }

    public void filtrarPorFecha(LocalDate fecha) {
        LinkedList<Momento> filtrados = repository.findByFecha(fecha);
        for (Momento m : filtrados) {
            System.out.println(m);
        }
    }
}
