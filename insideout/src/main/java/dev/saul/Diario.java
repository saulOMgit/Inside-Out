package dev.saul;

import java.util.LinkedList;

import dev.saul.models.Emocion;
import dev.saul.models.Momento;

import java.util.Iterator;
import java.time.LocalDate;
//controlador
public class Diario {
    private LinkedList<Momento> momentos;

    public Diario() {

        //tiene que estar en su propia clase (por single responsability)
        //sera carpeta db de ahi ira a repositorio y de ahi a un controlador
        momentos = new LinkedList<>();
    }



    public void agregarMomento(Momento momento) {
        momentos.add(momento);
        //no imprimir desde el controlador
        System.out.println("Momento vivido añadido correctamente.");
    }

    public void listarMomentos() {
        if (momentos.isEmpty()) {
            System.out.println("No hay momentos registrados.");
        } else {
            for (Momento m : momentos) {
                System.out.println(m);
            }
        }
    }

    public boolean eliminarMomento(int id) {
        Iterator<Momento> iterator = momentos.iterator();
        while (iterator.hasNext()) {
            Momento m = iterator.next();
            if (m.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public void filtrarPorEmocion(Emocion emocion) {
        for (Momento m : momentos) {
            if (m.getEmocion() == emocion) {
                System.out.println(m);
            }
        }
    }

    public void filtrarPorFecha(LocalDate fecha) {
        for (Momento m : momentos) {
            if (m.getFechaMomento().equals(fecha)) {
                System.out.println(m);
            }
        }
    }
}
