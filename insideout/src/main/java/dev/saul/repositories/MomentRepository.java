package dev.saul.repositories;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;

import dev.saul.db.MomentDB;
import dev.saul.models.Emocion;
import dev.saul.models.Momento;


//DUDAS
//TIENE QUE TENER TANTA LOGICA EL REPOSITORIO O SOLO ACCESO
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

    public LinkedList<Momento> findByEmocion(Emocion emocion) {
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
}
