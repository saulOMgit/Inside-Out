package dev.saul.controllers;

import dev.saul.models.EmocionEnum;
import dev.saul.models.Pelicula;
import dev.saul.repositories.PeliculaRepository;

import java.io.IOException;
import java.util.List;

public class PeliculaController {

    private PeliculaRepository repository;

    public PeliculaController() {
        this.repository = new PeliculaRepository();
    }

    // Agregar película manualmente
    public void agregarPelicula(String imdbId, String titulo, List<String> generos,
                                EmocionEnum emocion, int fechaEstreno) {
        Pelicula pelicula = new Pelicula(imdbId, titulo, generos, emocion, fechaEstreno);
        repository.add(pelicula);
    }

    // Listar todas las películas
    public List<Pelicula> listarPeliculas() {
        return repository.getAll();
    }

    // Filtrar por género (si contiene el género ingresado)
    public List<Pelicula> filtrarPorGenero(String genero) {
        return repository.findByGenero(genero);
    }

    // Exportar películas a CSV
    public void exportarPeliculasACSV(String rutaArchivo) throws IOException {
        repository.exportToCSV(rutaArchivo);
    }

    // Eliminar película por IMDb ID
    public boolean eliminarPelicula(String imdbId) {
        return repository.deleteById(imdbId);
    }
}
