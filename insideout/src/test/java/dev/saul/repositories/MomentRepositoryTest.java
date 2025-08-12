package dev.saul.repositories;

import dev.saul.db.MomentDB;
import dev.saul.models.Emocion;
import dev.saul.models.Momento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MomentRepositoryTest {

    private MomentRepository repository;

    @BeforeEach
    public void setUp() {
        // limpiar DB en memoria antes de cada test
        MomentDB.momentos.clear();
        repository = new MomentRepository();
    }

    @Test
    public void agregarYObtenerMomentos_debeDevolverLosAgregados() {
        Momento m1 = new Momento("T1", "D1", Emocion.ALEGRIA, LocalDate.now());
        repository.add(m1);

        List<Momento> all = repository.getAll();

        assertThat(all, hasSize(1));
        assertThat(all, contains(m1));
    }

    @Test
    public void deleteById_eliminaElementoYDevuelveTrue() {
        Momento m = new Momento("T", "D", Emocion.MIEDO, LocalDate.now());
        repository.add(m);

        boolean deleted = repository.deleteById(m.getId());

        assertThat(deleted, is(true));
        assertThat(repository.getAll(), is(empty()));
    }

    @Test
    public void findByEmocion_devuelveSoloLosCoincidentes() {
        Momento a = new Momento("A", "D", Emocion.ALEGRIA, LocalDate.now());
        Momento b = new Momento("B", "D", Emocion.TRISTEZA, LocalDate.now());
        repository.add(a);
        repository.add(b);

        List<Momento> filtrados = repository.findByEmocion(Emocion.ALEGRIA);

        assertThat(filtrados, hasSize(1));
        assertThat(filtrados, contains(a));
    }

    @Test
    public void findByFecha_devuelveSoloLosDelDiaSolicitud() {
        LocalDate fecha = LocalDate.of(2025, 8, 11);
        Momento a = new Momento("A", "D", Emocion.ALEGRIA, fecha);
        Momento b = new Momento("B", "D", Emocion.TRISTEZA, LocalDate.now());
        repository.add(a);
        repository.add(b);

        List<Momento> filtrados = repository.findByFecha(fecha);

        assertThat(filtrados, hasSize(1));
        assertThat(filtrados, contains(a));
    }
}
