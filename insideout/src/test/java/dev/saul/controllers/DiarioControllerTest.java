package dev.saul.controllers;

import dev.saul.db.MomentDB;
import dev.saul.models.EmocionEnum;
import dev.saul.models.Momento;
import dev.saul.repositories.MomentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DiarioControllerTest {

    private DiarioController diario;
    private MomentRepository repository;

    @BeforeEach
    public void setUp() {
        // Limpiar la base de datos simulada antes de cada test
        MomentDB.momentos.clear();
        diario = new DiarioController();
        repository = new MomentRepository();
    }

    @Test
    public void agregarMomento_debeInsertarEnRepositorio() {
        Momento m = new Momento("Viaje", "Fui a la playa", EmocionEnum.ALEGRIA, LocalDate.now());

        diario.agregarMomento(m);

        List<Momento> all = repository.getAll();
        assertThat(all, hasSize(1));
        assertThat(all, contains(m));
    }

    @Test
    public void eliminarMomento_debeBorrarYDevolverTrue() {
        Momento m = new Momento("A", "D", EmocionEnum.MIEDO, LocalDate.now());
        diario.agregarMomento(m);

        boolean eliminado = diario.eliminarMomento(m.getId());

        assertThat(eliminado, is(true));
        assertThat(repository.getAll(), is(empty()));
    }
}
