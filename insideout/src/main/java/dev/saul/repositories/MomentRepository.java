package dev.saul.repositories;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;

import com.opencsv.CSVWriter;

import dev.saul.db.MomentDB;
import dev.saul.models.EmocionEnum;
import dev.saul.models.Momento;
import dev.saul.models.PositividadEnum;

//Repositorio hara la vez de crud
public class MomentRepository {

    public void add(Momento momento) {
        MomentDB.momentos.add(momento);
    }

    public LinkedList<Momento> getAll() {
        return MomentDB.momentos;
    }

    public boolean deleteById(int id) {
        Iterator<Momento> iterator = MomentDB.momentos.iterator();
        while (iterator.hasNext()) {
            Momento m = iterator.next();
            if (m.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public LinkedList<Momento> findByEmocion(EmocionEnum emocion) {
        LinkedList<Momento> filtrados = new LinkedList<>();
        for (Momento m : MomentDB.momentos) {
            if (m.getEmocion() == emocion) {
                filtrados.add(m);
            }
        }
        return filtrados;
    }

    public LinkedList<Momento> findByFecha(LocalDate fecha) {
        LinkedList<Momento> filtrados = new LinkedList<>();
        for (Momento m : MomentDB.momentos) {
            if (m.getFechaMomento().equals(fecha)) {
                filtrados.add(m);
            }
        }
        return filtrados;
    }

    public LinkedList<Momento> findByMesYAnio(int mes, int anio) {
        LinkedList<Momento> filtrados = new LinkedList<>();
        for (Momento m : MomentDB.momentos) {
            LocalDate fecha = m.getFechaMomento();
            if (fecha.getMonthValue() == mes && fecha.getYear() == anio) {
                filtrados.add(m);
            }
        }
        return filtrados;
    }

    // Sprint 2
    public LinkedList<Momento> findByPositividad(PositividadEnum positividad) {
        LinkedList<Momento> filtrados = new LinkedList<>();
        for (Momento m : MomentDB.momentos) {
            if (m.getPositividad() == positividad) {
                filtrados.add(m);
            }
        }
        return filtrados;
    }
        //csv
         public void exportarAMomentoCSV(String rutaArchivo) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(rutaArchivo))) {
            // Escribir encabezados
            writer.writeNext(new String[]{"ID", "Título", "Descripción", "Emoción", "Positividad", "Fecha Momento", "Fecha Creación"});

            // Escribir datos de los momentos
            for (Momento momento : MomentDB.momentos) {
                writer.writeNext(new String[]{
                        String.valueOf(momento.getId()),
                        momento.getTitulo(),
                        momento.getDescripcion(),
                        momento.getEmocion().name(),
                        momento.getPositividad().name(),
                        momento.getFechaMomento().toString(),
                        momento.getFechaCreacion().toString()
                });
            }
        }
    }
}
