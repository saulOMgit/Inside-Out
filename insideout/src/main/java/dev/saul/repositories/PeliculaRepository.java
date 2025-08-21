package dev.saul.repositories;

import com.opencsv.CSVWriter;
import dev.saul.db.PeliculaDB;
import dev.saul.models.Pelicula;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PeliculaRepository {

    // Agregar película
    public void add(Pelicula pelicula) {
        PeliculaDB.peliculas.add(pelicula);
    }

    // Listar todas
    public List<Pelicula> getAll() {
        return PeliculaDB.peliculas;
    }

    // Filtrar por género
    public List<Pelicula> findByGenero(String genero) {
        return PeliculaDB.peliculas.stream()
                .filter(p -> p.getGeneros().stream()
                        .map(String::toLowerCase)
                        .collect(Collectors.toList())
                        .contains(genero.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Eliminar por IMDb ID
    public boolean deleteById(String imdbId) {
        return PeliculaDB.peliculas.removeIf(p -> p.getImdbId().equals(imdbId));
    }

    // Exportar a CSV
    public void exportarACSV(String rutaArchivo) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(rutaArchivo))) {
            // Cabecera
            String[] cabecera = {"ImdbId", "Título", "Géneros", "Emoción", "Año estreno", "Fecha creación"};
            writer.writeNext(cabecera);

            for (Pelicula p : PeliculaDB.peliculas) {
                String generos = String.join(",", p.getGeneros());
                String[] fila = {
                        p.getImdbId(),
                        p.getTitulo(),
                        generos,
                        p.getEmocion().name(),
                        String.valueOf(p.getFechaEstreno()),
                        p.getFechaCreacion().toString()
                };
                writer.writeNext(fila);
            }
        }
    }
}
