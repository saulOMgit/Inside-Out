package dev.saul.repositories;

import com.opencsv.CSVWriter;
import dev.saul.models.Pelicula;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PeliculaRepository {

    private LinkedList<Pelicula> peliculas = new LinkedList<>();

    // Agregar película
    public void add(Pelicula pelicula) {
        peliculas.add(pelicula);
    }

    // Listar todas
    public List<Pelicula> getAll() {
        return peliculas;
    }

    // Eliminar por IMDb ID
    public boolean deleteById(String imdbId) {
        Iterator<Pelicula> iterator = peliculas.iterator();
        while (iterator.hasNext()) {
            Pelicula p = iterator.next();
            if (p.getImdbId().equalsIgnoreCase(imdbId)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    // Filtrar por género (si contiene el género ingresado)
    public List<Pelicula> findByGenero(String genero) {
        List<Pelicula> filtradas = new ArrayList<>();
        for (Pelicula p : peliculas) {
            for (String g : p.getGeneros()) {
                if (g.equalsIgnoreCase(genero)) {
                    filtradas.add(p);
                    break;
                }
            }
        }
        return filtradas;
    }

    // Exportar a CSV
    public void exportToCSV(String rutaArchivo) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(rutaArchivo))) {
            // Cabecera
            String[] cabecera = {"IMDb ID", "Título", "Géneros", "Emoción", "Fecha Estreno", "Fecha Creación"};
            writer.writeNext(cabecera);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            for (Pelicula p : peliculas) {
                String generos = String.join(",", p.getGeneros());
                String[] linea = {
                        p.getImdbId(),
                        p.getTitulo(),
                        generos,
                        p.getEmocion().name(),
                        String.valueOf(p.getFechaEstreno()),
                        p.getFechaCreacion().format(formatter)
                };
                writer.writeNext(linea);
            }
        }
    }
}
