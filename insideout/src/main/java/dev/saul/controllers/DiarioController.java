package dev.saul.controllers;

import dev.saul.models.EmocionEnum;
import dev.saul.models.Momento;
import dev.saul.models.PositividadEnum;
import dev.saul.repositories.MomentRepository;

import java.io.IOException;
import java.util.List;

public class DiarioController {

    private MomentRepository repository;

    public DiarioController() {
        this.repository = new MomentRepository();
    }

    public void agregarMomento(Momento momento) {
        repository.add(momento);
    }

    public List<Momento> listarMomentos() {
        return repository.getAll();
    }

    public boolean eliminarMomento(int id) {
        return repository.deleteById(id);
    }

    public List<Momento> filtrarPorEmocion(EmocionEnum emocion) {
        return repository.findByEmocion(emocion);
    }

    public List<Momento> filtrarPorMesAno(int mes, int ano) {
        return repository.findByMesYAnio(mes, ano);
    }

    // Sprint 2
    public List<Momento> listarMomentosBuenos() {
        return repository.findByPositividad(PositividadEnum.BUENO);
    }

    public List<Momento> listarMomentosMalos() {
        return repository.findByPositividad(PositividadEnum.MALO);
    }

    //csv
    public void exportarMomentosACSV(String rutaArchivo) throws IOException {
    repository.exportarAMomentoCSV(rutaArchivo);
}


}
