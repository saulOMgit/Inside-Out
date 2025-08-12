package dev.saul.controllers;

import java.util.LinkedList;
import java.util.List;

import dev.saul.models.Emocion;
import dev.saul.models.Momento;
import dev.saul.repositories.MomentRepository;
import dev.saul.views.DiarioView;

import java.time.LocalDate;

//controlador
public class DiarioController {

    

    private final MomentRepository repository;
    private final DiarioView view;

    public DiarioController(MomentRepository repository, DiarioView view) {
        // tiene que estar en su propia clase (por single responsability)
        // sera carpeta db de ahi ira a repositorio y de ahi a un controlador
        this.repository = repository;
        this.view = view;
    }

    public void agregarMomento(Momento momento) {
        repository.add(momento);
        view.mostrarMensaje("Momento agregado con éxito.");
    }

    public void listarMomentos() {
        List<Momento> momentos = repository.getAll();
        view.mostrarMomentos(momentos);
    }

    public void listarMomentosPorEmocion(Emocion emocion) {
        List<Momento> momentos = repository.findByEmocion(emocion);
        view.mostrarMomentos(momentos);
    }

    public void listarMomentosPorFecha(LocalDate fecha) {
        List<Momento> momentos = repository.findByFecha(fecha);
        view.mostrarMomentos(momentos);
    }

    public void eliminarMomento(int id) {
        boolean eliminado = repository.deleteById(id);
        if (eliminado) {
            view.mostrarMensaje("Momento eliminado.");
        } else {
            view.mostrarMensaje("No se encontró un momento con ese ID.");
        }
    }
}