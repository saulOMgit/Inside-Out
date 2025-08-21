package dev.saul.controllers;

import dev.saul.db.MomentDB;
import dev.saul.models.EmocionEnum;
import dev.saul.models.Momento;
import dev.saul.models.PositividadEnum;
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

    @Test
    public void listarMomentos_debeDevolverTodosLosAgregados() {
        Momento m1 = new Momento("Viaje", "Playa", EmocionEnum.ALEGRIA, LocalDate.now());
        Momento m2 = new Momento("Trabajo", "Oficina", EmocionEnum.TRISTEZA, LocalDate.now());
        diario.agregarMomento(m1);
        diario.agregarMomento(m2);

        List<Momento> momentos = diario.listarMomentos();

        assertThat(momentos, hasSize(2));
        assertThat(momentos, contains(m1, m2));
    }

    //Sprint 2
    @Test
public void listarMomentosBuenos_debeDevolverSoloLosBuenos() {
    Momento bueno = new Momento("Fiesta", "Con amigos", EmocionEnum.ALEGRIA, LocalDate.now());
    bueno.setPositividad(PositividadEnum.BUENO);

    Momento malo = new Momento("Examen", "Suspendido", EmocionEnum.TRISTEZA, LocalDate.now());
    malo.setPositividad(PositividadEnum.MALO);

    diario.agregarMomento(bueno);
    diario.agregarMomento(malo);

    List<Momento> buenos = diario.listarMomentosBuenos();

    assertThat(buenos, hasSize(1));
    assertThat(buenos.get(0).getPositividad(), is(PositividadEnum.BUENO));
}

@Test
public void listarMomentosMalos_debeDevolverSoloLosMalos() {
    Momento bueno = new Momento("Fiesta", "Con amigos", EmocionEnum.ALEGRIA, LocalDate.now());
    bueno.setPositividad(PositividadEnum.BUENO);

    Momento malo = new Momento("Examen", "Suspendido", EmocionEnum.TRISTEZA, LocalDate.now());
    malo.setPositividad(PositividadEnum.MALO);

    diario.agregarMomento(bueno);
    diario.agregarMomento(malo);

    List<Momento> malos = diario.listarMomentosMalos();

    assertThat(malos, hasSize(1));
    assertThat(malos.get(0).getPositividad(), is(PositividadEnum.MALO));
}

}
