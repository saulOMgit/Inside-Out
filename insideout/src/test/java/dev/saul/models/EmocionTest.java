package dev.saul.models;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

//estaria bien test de .count()
public class EmocionTest {

    @Test
    public void debeContenerTodasLasEmocionesEnOrden() {
        Emocion[] emocionesEsperadas = {
            Emocion.ALEGRIA,
            Emocion.TRISTEZA,
            Emocion.IRA,
            Emocion.ASCO,
            Emocion.MIEDO,
            Emocion.ANSIEDAD,
            Emocion.ENVIDIA,
            Emocion.VERGUENZA,
            Emocion.ABURRIMIENTO,
            Emocion.NOSTALGIA
        };

        assertThat(Emocion.values(), is(emocionesEsperadas));
    }

    @Test
    public void valueOfDebeRetornarLaEmocionCorrecta() {
        assertThat(Emocion.valueOf("MIEDO"), is(Emocion.MIEDO));
        assertThat(Emocion.valueOf("VERGUENZA"), is(Emocion.VERGUENZA));
    }

    @Test
    public void valueOfConValorInvalidoDebeLanzarExcepcion() {
        try {
            Emocion.valueOf("FELICIDAD");
        } catch (IllegalArgumentException e) {
            assertThat(e, instanceOf(IllegalArgumentException.class));
            return;
        }
        throw new AssertionError("Se esperaba IllegalArgumentException para valor inválido");
    }
}
